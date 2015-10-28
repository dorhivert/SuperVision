package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;


/**
 * The Class Presenter.
 */
public class Presenter implements Observer
{
	
	/** The model. */
	private Model model;
	
	/** The view. */
	private View view;
	
	/** The command map. */
	private HashMap<String,Command> commandMap;

	/**
	 * Instantiates a new presenter.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public Presenter(Model model, View view) 
	{
		this.model = model;
		this.view = view;
		this.commandMap = new HashMap<String, Command>();
		this.initCommands(commandMap);
	}

	/**
	 * Inits the commands.
	 *
	 * @param map the map
	 */
	private void initCommands(HashMap<String, Command> map) 
	{
		map.put("dir", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				getFilesInDirectory(args[1]);
			}
		});
		map.put("generate", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				view.setMazeName(args[3]);
				int size = Integer.parseInt(args[4]);
				generate3dMaze(args[3], size);
			}
		});
		map.put("display", new Command() 
		{

			@Override
			public void doCommand(String[] args) 
			{
				switch (args[1])
				{
				case "cross":
				{
					char xyz = args[4].charAt(0);
					int index = Integer.parseInt(args[5]);
					getCrossSection(args[7], index, xyz);
				}
				break;
				case "solution":
				{
				
					if((model.getSolutionCollection().get(model.getMazeCollection().get(args[2])))!=null)
					{
						view.displaySolution((model.getSolutionCollection().get(model.getMazeCollection().get(args[2]))));
						view.setSolution(model.getSolutionCollection().get(model.getMazeCollection().get(args[2])));
					}
					else
					{
						notifyView("No solution exists for this maze, please create one.");
						view.enableSolve();
					}
				} break;
				default:
					if(model.getMazeCollection().get(args[1])!=null)
					{
						view.setMazeName(args[1]);
						view.displayMaze(model.getMazeCollection().get(args[1]));
					}
					else
					{
						notifyView("No maze exists by this name, please create one.");
					}
					break;
				}
			}		
		});
		map.put("save", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				if(model.getMazeCollection().containsKey(args[2]))
				{
					model.saveMaze(args[2], args[3]);
					notifyView("Maze "+args[2]+" has been saved under the path:"+args[3]);
				}
				else
				{
					notifyView ("No maze exists by this name");
				}
				
			}
		});
		map.put("load", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				view.setMazeName(args[3]);
				model.loadMaze(args[3], args[2]);
				notifyView("File "+args[2]+" has been loaded");
				view.displayMazeGUI(model.getMazeCollection().get(args[3]));
			}
		});
		map.put("maze", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				displayMazeSize(args[2]);
			}
		});
		map.put("file", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				displayFileSize(args[2]);
			}
		});
		map.put("solve", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				@SuppressWarnings("unused")
				String defaultAlg = new String();
				if (args.length >= 3)
					defaultAlg = new String(args[2]);
				model.solve(args[1]);
			}
		});
		map.put("menu", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				commandMenu();
			}
		});
		map.put("exit", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				model.officialExit();
			}
		});
		map.put("changeProperties", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				
				model.creatNewProperties(args);
			}
		});
		map.put("openNewXML", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				model.loadNewProperties(args);
			}
		});
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (arg0 instanceof Model)
		{
			String data = ((String) arg1);
			switch (data)
			{
			case "dir":
			{
				view.displayDirectory((String[]) model.getCommandData().get("dir"));
			} 	break;
			case "generated":
			{
				notifyView("Maze "+model.getCommandData().get("generated")+" is ready" );
				view.displayMazeGUI(model.getMazeCollection().get(model.getCommandData().get("generated")));
	
			}	break;
			case "crossed":
			{
				view.displayCrossSection((int[][]) model.getCommandData().get("crossed"));
			}	break;
			case "calcedMazeSize":
			{
				view.displayMazeSize((String) model.getCommandData().get("maze"), (double) model.getCommandData().get("calcedMazeSize"));
			}	break;
			case "loaded":
			{
			}	break;
			case "saved":
			{
			}	break;
			case "solved":
			{
				notifyView("Solution for maze: "+model.getCommandData().get("solved")+" is ready");
				view.setSolution(model.getSolutionCollection().get(model.getMazeCollection().get(model.getCommandData().get("solved"))));
			}	break;
			case "notify":
			{
				notifyView((String) model.getCommandData().get("notify"));
			}	break;
			case "quit":
			{
				notifyView((String) model.getCommandData().get("quit"));
			}	break;
			case "saveZip":
			{
				notifyView((String) model.getCommandData().get("saveZip"));
			}	break;
			case "loadZip":
			{
				notifyView((String) model.getCommandData().get("loadZip"));
			}	break;
				

			default:
				notifyView("Model is going crazy (presenter.update.default)");
				break;
			}
		}
		else
		{
			if (arg0 instanceof View)
			{
				String[] myData = ((String[]) arg1);
				Command cData = commandMap.get(myData[0]);
				cData.doCommand(myData);
			}
			else
			{
				System.out.println("FATAL ERROR:  UPDATE FROM SPACE (Presenter.update)");
				return;
			}
		}
	}
	
	/**
	 * Gets the command map.
	 *
	 * @return the command map
	 */
	public HashMap<String, Command> getCommandMap() 
	{
		return this.commandMap;
	}

	/**
	 * Generate3d maze.
	 *
	 * @param name the name
	 * @param size the size
	 */
	private void generate3dMaze(String name, int size)
	{
		this.model.generate3dMaze(name, size);
	}

	/**
	 * Gets the cross section.
	 *
	 * @param name the name
	 * @param index the index
	 * @param xyz the xyz
	 * @return the cross section
	 */
	private void getCrossSection(String name, int index, char xyz)
	{
		this.model.getCrossSection(xyz, index, name);
	}

	/**
	 * Display maze size.
	 *
	 * @param name the name
	 */
	private void displayMazeSize(String name) 
	{
		this.model.calcMazeSize(name);
		this.view.displayMazeSize(name, (double) this.model.getCommandData().get(name));
	}

	/**
	 * Display file size.
	 *
	 * @param name the name
	 */
	private void displayFileSize(String name)
	{
		this.model.calcFileSize(name);
		this.view.displayFileSize(name, (double) this.model.getCommandData().get(name));
	}

	/**
	 * Gets the files in directory.
	 *
	 * @param path the path
	 * @return the files in directory
	 */
	private void getFilesInDirectory(String path) 
	{
		this.model.getFilesInDirectory(path);
	}

	/**
	 * Notify view.
	 *
	 * @param msg the msg
	 */
	private void notifyView(String msg) 
	{
		this.view.writeToConsole("*** CPU Notification: "+msg);
	}

	/**
	 * Command menu.
	 */
	private void commandMenu() 
	{
		view.displayCommandMenu();

	}
}
