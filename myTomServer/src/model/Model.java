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
	 * Generate3d maze.
	 *
	 * @param name the name
	 * @param size the size
	 */
	public void generate3dMaze(String name, int size);
	

	/**
	 * Solve.
	 *
	 * @param name the name
	 * @param algo the algo
	 */
	public void solve(String name);


	void officialExit();


	public HashMap<String, Maze3d> getMazeCollection();


	public HashMap<Maze3d, Solution> getSolutionCollection();


	public HashMap<String, Object> getCommandData();
}
