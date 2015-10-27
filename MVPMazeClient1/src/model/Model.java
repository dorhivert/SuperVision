package model;

import java.util.HashMap;

import mazeGenerators.Maze3d;
import solution.Solution;


/**
 * The Interface Model.
 */
public interface Model 
{

	/**
	 * Gets the files in directory.
	 *
	 * @param path the path
	 * @return the files in directory
	 */
	public void getFilesInDirectory(String path);
	
	/**
	 * Generate3d maze.
	 *
	 * @param name the name
	 * @param size the size
	 */
	public void generate3dMaze(String name, int size);
	
	/**
	 * Gets the cross section.
	 *
	 * @param xyz the xyz
	 * @param index the index
	 * @param name the name
	 * @return the cross section
	 */
	public void getCrossSection (char xyz, int index, String name);
	
	/**
	 * Save maze.
	 *
	 * @param mazeName the maze name
	 * @param fileName the file name
	 */
	public void saveMaze (String mazeName, String fileName);
	
	/**
	 * Load maze.
	 *
	 * @param mazeName the maze name
	 * @param fileName the file name
	 */
	public void loadMaze (String mazeName, String fileName);

	/**
	 * Calc maze size.
	 *
	 * @param name the name
	 */
	public void calcMazeSize(String name);
	
	/**
	 * Calc file size.
	 *
	 * @param name the name
	 */
	public void calcFileSize(String name);
	
	/**
	 * Solve.
	 *
	 * @param name the name
	 */
	public void solve(String name);
	
	/**
	 * Gets the maze collection.
	 *
	 * @return the maze collection
	 */
	public HashMap<String,Maze3d> getMazeCollection();
	
	/**
	 * Gets the solution collection.
	 *
	 * @return the solution collection
	 */
	public HashMap<Maze3d,Solution> getSolutionCollection();
	
	/**
	 * Gets the command data.
	 *
	 * @return the command data
	 */
	public HashMap<String,Object> getCommandData();

	/**
	 * Official exit.
	 */
	public void officialExit();

	/**
	 * Creat new properties.
	 *
	 * @param args the args
	 */
	public void creatNewProperties(String[] args);

	/**
	 * Load new properties.
	 *
	 * @param args the args
	 */
	public void loadNewProperties(String[] args);
}
