package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

public class Presenter implements Observer
{

	Model model;

	View view;

	HashMap<String,Command> commandMap;

	public Presenter(Model model, View view) 
	{
		this.model = model;
		this.view = view;
		this.commandMap = new HashMap<String, Command>();
		this.initCommands(commandMap);
	}

	public void initCommands(HashMap<String, Command> map) 
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
					}
					else
					{
						notifyView("No solution exists for this maze, please create one.");
					}
				} break;
				default:
					if(model.getMazeCollection().get(args[1])!=null)
					{
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
				model.loadMaze(args[3], args[2]);
				notifyView("File "+args[2]+" has been loaded");
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
				String defaultAlg = new String("astarair");
				if (args.length >= 3)
					defaultAlg = new String(args[2]);
				model.solve(args[1], defaultAlg);
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
				notifyView("Official Exit");
				model.officialExit();
			}
		});
	}

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
			//	notifyView("Maze: "+model.getCommandData().get("loaded")+" has been loaded");
			}	break;
			case "saved":
			{
				//notifyView("Maze: "+model.getCommandData().get("saved")+" has been saved");
			}	break;
			case "solved":
			{
				notifyView("Solution for maze: "+model.getCommandData().get("solved")+" is ready");
			}	break;
			case "notify":
			{
				notifyView((String) model.getCommandData().get("notify"));
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

	/* (non-Javadoc)
	 * @see controller.Controller#displayDirectory(java.lang.String[])
	 */
	public void displayDirectory(String[] path) 
	{
		view.displayDirectory(path);
	}

	/* (non-Javadoc)
	 * @see controller.Controller#generate3dMaze(java.lang.String, int)
	 */
	public void generate3dMaze(String name, int size)
	{
		this.model.generate3dMaze(name, size);
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayMaze(java.lang.String)
	 */
	public void displayMaze(String name)
	{
		if ((model.getMazeCollection().get(name)) !=null)
		{
			this.view.displayMaze(model.getMazeCollection().get(name));
		}
	}

	/* (non-Javadoc)
	 * @see controller.Controller#getCrossSection(java.lang.String, int, char)
	 */
	public void getCrossSection(String name, int index, char xyz)
	{
		this.model.getCrossSection(xyz, index, name);
	//	this.view.displayCrossSection((int[][]) this.model.getCommandData().get(name));
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayMazeSize(java.lang.String)
	 */
	public void displayMazeSize(String name) 
	{
		this.model.calcMazeSize(name);
		this.view.displayMazeSize(name, (double) this.model.getCommandData().get(name));
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayFileSize(java.lang.String)
	 */
	public void displayFileSize(String name)
	{
		this.model.calcFileSize(name);
		this.view.displayFileSize(name, (double) this.model.getCommandData().get(name));
	}

	/* (non-Javadoc)
	 * @see controller.Controller#getFilesInDirectory(java.lang.String)
	 */
	public void getFilesInDirectory(String path) 
	{
		this.model.getFilesInDirectory(path);
		//this.view.displayDirectory((String[]) this.model.getCommandData().get(path));
	}

	/**
	 * Notify view.
	 *
	 * @param msg the msg
	 */
	public void notifyView(String msg) 
	{
		this.view.writeToConsole("*** CPU Notification: "+msg);
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displaySolution(java.lang.String)
	 */
	public void displaySolution(String name)
	{
		if (this.model.getSolutionCollection().containsKey(name))
		{
			view.displaySolution(model.getSolutionCollection().get(name));
		} 
		else 
		{
			this.notifyView("No Solution for this maze (c.displaySolution)");
		}
	}

	/* (non-Javadoc)
	 * @see controller.Controller#commandManu()
	 */
	public void commandMenu() 
	{
		view.displayCommandMenu();

	}

	public HashMap<String, Command> getCommandMap() 
	{
		return this.commandMap;
	}

}
