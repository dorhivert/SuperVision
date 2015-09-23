package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import presenter.Command;
import model.Model;
import view.View;

public class Presenter implements Observer{

	Model model;
	
	View view;
	
	HashMap<String,Command> commandMap;
	
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
					if(model.getSolutionCollection().get(args[2])!=null)
					{
						view.displaySolution(model.getSolutionCollection().get(args[2]));
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
				model.saveMaze(args[2], args[3]);
				notifyView("Maze "+args[2]+" has been saved under the path:");
				notifyView(args[3]);
			}
		});
		map.put("load", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				model.getMazeCollection().put(args[3], model.loadMaze(args[3], args[2]));
				notifyView("Maze "+args[2]+" has been loaded");
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
				commandManu();
			}
		});
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
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
		this.view.displayCrossSection(this.model.getCrossSection(xyz, index, name));
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayMazeSize(java.lang.String)
	 */
	public void displayMazeSize(String name) 
	{
		this.view.displayMazeSize(name, this.model.calcMazeSize(name));
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayFileSize(java.lang.String)
	 */
	public void displayFileSize(String name)
	{
		this.view.displayFileSize(name, this.model.calcFileSize(name));
	}

	/* (non-Javadoc)
	 * @see controller.Controller#getFilesInDirectory(java.lang.String)
	 */
	public void getFilesInDirectory(String path) 
	{
		this.view.displayDirectory(this.model.getFilesInDirectory(path));
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
		if (model.getSolutionCollection().containsKey(name))
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
	public void commandManu() 
	{
		view.displayCommandManu();

	}

}
