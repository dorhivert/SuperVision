package controller;

import java.util.HashMap;

import mazeGenerators.Maze3d;
import solution.Solution;

// TODO: Auto-generated Javadoc
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

		map.put("generate", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				int size = Integer.parseInt(args[4]);
				generate3dMaze(args[3], size);
			}
		});

		map.put("solve", new Command() 
		{
			@Override
			public void doCommand(String [] args) 
			{
				model.solve(args[1]);
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


	/* (non-Javadoc)
	 * @see controller.Controller#generate3dMaze(java.lang.String, int)
	 */
	@Override
	public void generate3dMaze(String name, int size)
	{
		this.model.generate3dMaze(name, size);
	}

	/**
	 * Notify view.
	 *
	 * @param msg the msg
	 */
	@Override
	public void notifyView(String msg) 
	{
		this.view.writeToConsole("*** CPU Notification: "+msg);
	}

	/* (non-Javadoc)
	 * @see controller.Controller#commandManu()
	 */
	@Override
	public void commandManu() 
	{
		view.displayCommandManu();

	}

}
