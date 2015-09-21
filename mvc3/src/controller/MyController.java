package controller;

import java.util.HashMap;

import mazeGenerators.Maze3d;
import solution.Solution;

/**
 * The Class MyController.
 */
public class MyController extends CommonController
{

	/** The maze collection. */
	private HashMap<String, Maze3d> mazeCollection = new HashMap<String, Maze3d>();

	/** The solution collection. */
	private HashMap<String, Solution> solutionCollection = new HashMap<String, Solution>();


	/**
	 * Gets the maze collection.
	 *
	 * @return the maze collection
	 */
	public HashMap<String, Maze3d> getMazeCollection() 
	{
		return mazeCollection;
	}

	/**
	 * Gets the solution collection.
	 *
	 * @return the solution collection
	 */
	public HashMap<String, Solution> getSolutionCollection() 
	{
		return solutionCollection;
	}


	/* (non-Javadoc)
	 * @see controller.CommonController#initCommands(java.util.HashMap)
	 */
	@Override
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
					char xyz = args[3].charAt(0);
					int index = Integer.parseInt(args[4]);
					getCrossSection(args[5], index, xyz);
				}
				break;
				case "solution":
				{
					if(solutionCollection.get(args[2])!=null)
					{
						view.displaySolution(solutionCollection.get(args[2]));
					}
					else
					{
						notifyView("No solution exists for this maze, please create one.");
					}
				}
				default:
					if(mazeCollection.get(args[1])!=null)
					{
						view.displayMaze(mazeCollection.get(args[1]));
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
				mazeCollection.put(args[3], model.loadMaze(args[3], args[2]));
				notifyView("Maze "+args[2]+" has been loaded");
			}
		});
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayDirectory(java.lang.String[])
	 */
	@Override
	public void displayDirectory(String[] path) 
	{
		view.displayDirectory(path);
	}

	/* (non-Javadoc)
	 * @see controller.Controller#generate3dMaze(java.lang.String, int)
	 */
	@Override
	public void generate3dMaze(String name, int size)
	{
		this.model.generate3dMaze(name, size);
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayMaze(java.lang.String)
	 */
	@Override
	public void displayMaze(String name)
	{
		if ((this.getMazeCollection().get(name)) !=null)
		{
			this.view.displayMaze(this.getMazeCollection().get(name));
		}
	}

	/* (non-Javadoc)
	 * @see controller.Controller#getCrossSection(java.lang.String, int, char)
	 */
	@Override
	public void getCrossSection(String name, int index, char xyz)
	{
		this.view.displayCrossSection(this.model.getCrossSection(xyz, index, name));
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayMazeSize(java.lang.String)
	 */
	@Override
	public void displayMazeSize(String name) 
	{
		this.view.displayMazeSize(name, this.model.calcMazeSize(name));
	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayFileSize(java.lang.String)
	 */
	@Override
	public void displayFileSize(String name)
	{
		this.view.displayFileSize(name, this.model.calcFileSize(name));
	}

	/* (non-Javadoc)
	 * @see controller.Controller#getFilesInDirectory(java.lang.String)
	 */
	@Override
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
		this.view.writeToConsole(msg);
	}

	@Override
	public void displaySolution(String name)
	{
		if (this.getSolutionCollection().containsKey(name))
		{
			view.displaySolution(this.solutionCollection.get(name));
		} 
		else 
		{
			this.notifyView("No Solution for this maze (c.displaySolution)");
		}
	}

}
