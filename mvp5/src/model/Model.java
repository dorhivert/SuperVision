package model;

import java.util.HashMap;

import mazeGenerators.Maze3d;
import solution.Solution;

// TODO: Auto-generated Javadoc
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
	 * @return the maze3d
	 */
	public void loadMaze (String mazeName, String fileName);
	
	/**
	 * Calc maze size.
	 *
	 * @param name the name
	 * @return the double
	 */
	public void calcMazeSize(String name);
	
	/**
	 * Calc file size.
	 *
	 * @param name the name
	 * @return the double
	 */
	public void calcFileSize(String name);
	
	/**
	 * Solve.
	 *
	 * @param name the name
	 * @param algo the algorithm
	 */
	public void solve(String name);
	
	public HashMap<String,Maze3d> getMazeCollection();
	public HashMap<Maze3d,Solution> getSolutionCollection();
	public HashMap<String,Object> getCommandData();

	public void officialExit();
}
