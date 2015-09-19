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
			public void doCommand() 
			{
				// TODO Auto-generated method stub

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
	public void generate3dMaze(String name, int size) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayMaze(java.lang.String)
	 */
	@Override
	public void displayMaze(String name) {
		// TODO Auto-generated method stub

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
	public void displayMazeSize(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see controller.Controller#displayFileSize(java.lang.String)
	 */
	@Override
	public void displayFileSize(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see controller.Controller#getFilesInDirectory(java.lang.String)
	 */
	@Override
	public void getFilesInDirectory(String path) {
		// TODO Auto-generated method stub

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
