package view;

import java.io.Closeable;
import java.io.IOException;

import mazeGenerators.Maze3d;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import solution.Solution;

public class MyGUIView extends BasicWindow implements Closeable
{
	private String propertiesFilePath;
	private Text mazeInformation;
	private Text zLevel;
	private MessageBox msgs;
	private CommonMazeDisplayWidget mazeDisplay;
	private Maze3d myViewMaze;
	private String mazeName;
	private String floorLevelZed;

	public MyGUIView(String title, int width, int height)
	{
		super(title, width, height);
		this.mazeName = new String(" No Maze");
		this.floorLevelZed = new String(" ?");
	}

	@Override
	public void start()
	{
		run();
	}

	@Override
	public void writeToConsole(String userInput){} 
	@Override
	public void displayDirectory(String[] path){}
	@Override
	public void displayCommandMenu(){}


	@Override
	public void displayCrossSection(int[][] crossSection)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMazeSize(String name, double mazeSize)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void displayFileSize(String name, double fileSize) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMaze(Maze3d maze)
	{
		displayMazeGUI(maze);
	}
	
	public void displayMazeGUI(Maze3d maze)
	{
		if (maze != null) 
		{
			this.myViewMaze = maze;
			mazeDisplay.setMyMaze(myViewMaze);
			mazeDisplay.redraw();
			mazeDisplay.forceFocus();
			mazeInformation.setText("Maze Name: "+this.mazeName);
			getZedLevel();
			zLevel.setText("Floor level: "+this.floorLevelZed);
		}
	}

	@Override
	public void displaySolution(Solution s)
	{
		// TODO Auto-generated method stub

	}

	@Override
	void InitWidgets()
	{
		shell.setLayout(new GridLayout(2, false));
		shell.addListener(SWT.Close, new Listener() 
		{
			@Override
			public void handleEvent(Event arg0)
			{
				int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
				changeAndNotify("exit");
				msgs = new MessageBox(shell, style);
				msgs.setText("Information");
				msgs.setMessage("Close the shell?");
				arg0.doit = msgs.open() == SWT.YES;
			}
		});
		
		Button generate = new Button(shell, SWT.PUSH);
		generate.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		generate.setText("New Game");
		generate.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0){}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				MazeSettingsDialog fd=new MazeSettingsDialog(shell);
				fd.setText("open");
				String newValue = new String(fd.open());

				String line = new String("generate 3d maze");
				line = line+" "+newValue;
				String [] splittedLine = line.split(" ");
				int num = splittedLine.length;
				boolean flag = true;
				if (num == 5) 
				{
					if (!splittedLine[3].equals("unNamed Maze")) 
					{
						if (splittedLine[3] == null)
						{
							flag = false;
						}
						if (Integer.parseInt(splittedLine[4]) < 1) 
						{
							flag = false;
						}
						if (flag == true)
						{
							changeAndNotify(line);
						}
					}
				}
			}
		});
		
		mazeInformation = new Text(shell, SWT.BORDER);
		mazeInformation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		mazeInformation.append("Maze Name: "+this.mazeName);

		Button loadMaze = new Button(shell, SWT.PUSH);
		loadMaze.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		loadMaze.setText("Load Game");
		loadMaze.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				Dialog loadMaze = new GameSaveMazeDialog(shell);
				String line = ((GameSaveMazeDialog) loadMaze).open();
				if(line != null)
				{
					String newLine = "display "+line;
					changeAndNotify(newLine);
				}
			}
		});

		zLevel = new Text(shell, SWT.BORDER);
		zLevel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		zLevel.append("Floor level: "+this.floorLevelZed);

		Button saveMaze = new Button(shell, SWT.PUSH);
		saveMaze.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		saveMaze.setText("Save Game");
		saveMaze.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				FileDialog fd=new FileDialog(shell,SWT.SAVE);
				fd.setText("Save");
				fd.setFilterPath("");
				
				String[] filterExt = { "*.maz", "*.MAZ", "*.*" };
				fd.setFilterExtensions(filterExt);
				propertiesFilePath = fd.open();
				if (propertiesFilePath != null)
				{
					String line = new String("save maze "+mazeName);
					line = line+" "+propertiesFilePath;
					changeAndNotify(line);
				}
			}
		});

		mazeDisplay = new MyMazeDisplayWidget(shell, SWT.BORDER);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 10));
		mazeDisplay.setMyMaze(myViewMaze);
		mazeDisplay.redraw();
		mazeDisplay.forceFocus();
		mazeDisplay.addKeyListener(new KeyListener()
		{

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				if (e.keyCode == SWT.ARROW_UP) 
				{
					mazeDisplay.moveDown();
				}
				else if (e.keyCode == SWT.ARROW_DOWN) 
				{
					mazeDisplay.moveUp();
				}
				else if (e.keyCode == SWT.ARROW_RIGHT)
				{
					mazeDisplay.moveRight();
				}
				else if (e.keyCode == SWT.ARROW_LEFT) 
				{
					mazeDisplay.moveLeft();
				} 
				else if (e.keyCode == SWT.PAGE_UP)
				{
					mazeDisplay.moveIn();
					getZedLevel();
					zLevel.setText("Floor level: "+floorLevelZed);
				}
				else if (e.keyCode == SWT.PAGE_DOWN) 
				{
					mazeDisplay.moveOut();
					getZedLevel();
					zLevel.setText("Floor level: "+floorLevelZed);
				}
			}
		});
		
		Button mazeProps = new Button(shell, SWT.PUSH);
		mazeProps.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		mazeProps.setText("Application Properties");
		mazeProps.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0){}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				GamePropertiesDialog gpd = new GamePropertiesDialog(shell);
				gpd.setText("open");

				String newValue = new String(gpd.open());

				String line = new String("changeProperties ");
				line = line+newValue;
				if (line.split(" ").length == 5)
				{
					changeAndNotify(line);
					msgs = new MessageBox(shell);
					msgs.setText("NOTICE");
					msgs.setMessage("You must restart the apllication in order to apply new properties!");
					msgs.open();
				}
			}
		});

		
		Button loadMazeFile = new Button(shell, SWT.PUSH);
		loadMazeFile.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		loadMazeFile.setText("Load maze from file");
		loadMazeFile.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				
				fd.setText("open");
				fd.setFilterPath("");
				String[] filterExt = { "*.maz", "*.MAZ", "*.*" };
				fd.setFilterExtensions(filterExt);
				propertiesFilePath = fd.open();
				if (propertiesFilePath != null)
				{
					String line = new String("load maze");
					line = line+" "+propertiesFilePath;
					if (mazeName.equals(" No Maze")) 
					{
						mazeName = new String("tempMazeName");
					}
					line = line+" "+mazeName;
					changeAndNotify(line);
				}
			}
		});
		
		Button open = new Button(shell, SWT.PUSH);
		open.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		open.setText("Load XML properties file");
		open.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				fd.setText("open");
				fd.setFilterPath("");
				String[] filterExt = { "*.xml", "*.XML", "*.*" };
				fd.setFilterExtensions(filterExt);
				propertiesFilePath = fd.open();
				if (propertiesFilePath != null)
				{
					String line = new String("openNewXML");
					line = line+" "+propertiesFilePath;
					changeAndNotify(line);
					msgs = new MessageBox(shell);
					msgs.setText("NOTICE");
					msgs.setMessage("You must restart the apllication in order to apply new properties!");
					msgs.open();
				}
			}
		});
		
		
		Button hint = new Button(shell, SWT.PUSH);
		hint.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		hint.setText("HINT");
		hint.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				fd.setText("open");
				fd.setFilterPath("");
				String[] filterExt = { "*.xml", "*.XML", "*.*" };
				fd.setFilterExtensions(filterExt);
				propertiesFilePath = fd.open();
				if (propertiesFilePath != null)
				{
					String line = new String("openNewXML");
					line = line+" "+propertiesFilePath;
					changeAndNotify(line);
					msgs = new MessageBox(shell);
					msgs.setText("NOTICE");
					msgs.setMessage("You must restart the apllication in order to apply new properties!");
					msgs.open();
				}
			}
		});
		
		Button solve = new Button(shell, SWT.PUSH);
		solve.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		solve.setText("SOLVE");
		solve.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				fd.setText("open");
				fd.setFilterPath("");
				String[] filterExt = { "*.xml", "*.XML", "*.*" };
				fd.setFilterExtensions(filterExt);
				propertiesFilePath = fd.open();
				if (propertiesFilePath != null)
				{
					String line = new String("openNewXML");
					line = line+" "+propertiesFilePath;
					changeAndNotify(line);
					msgs = new MessageBox(shell);
					msgs.setText("NOTICE");
					msgs.setMessage("You must restart the apllication in order to apply new properties!");
					msgs.open();
				}
			}
		});




		Button egzit = new Button(shell, SWT.PUSH);
		egzit.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
		egzit.setText("EXIT");
		egzit.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0){}
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				try 
				{
					close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public void close() throws IOException
	{
		changeAndNotify("exit");
		if (display!=null&&(!display.isDisposed()))
		{
			display.dispose();
		}

		if(shell!=null&&(!shell.isDisposed()))
		{
			shell.dispose();
		}
	}

	private void changeAndNotify(String command)
	{
		String [] splittedLine = command.split(" ");
		setChanged();
		notifyObservers(splittedLine);
	}

	@Override
	public void setMazeName(String _name)
	{
		this.mazeName = new String (_name);
	}
	
	private void getZedLevel()
	{
		this.floorLevelZed = new String((Integer.toString(myViewMaze.getCorrentPosition().getZ()+1)));
	}
}