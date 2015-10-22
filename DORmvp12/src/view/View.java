package view;

import mazeGenerators.Maze3d;
import solution.Solution;

/**
 * The Interface View.
 */
public interface View 
{
	/**
	 * Start.
	 */
	public void start();
	
	/**
	 * Write to console.
	 *
	 * @param userInput the user input
	 */
	public void writeToConsole(String userInput);
	
	/**
	 * Display cross section.
	 *
	 * @param crossed the crossed
	 */
	public void displayCrossSection (int[][] crossSection);
	
	/**
	 * Display maze size.
	 *
	 * @param name the name
	 * @param mazeSize the maze size
	 */
	public void displayMazeSize(String name, double mazeSize);
	
	/**
	 * Display file size.
	 *
	 * @param name the name
	 * @param fileSize the file size
	 */
	public void displayFileSize(String name, double fileSize);
	
	/**
	 * Display maze.
	 *
	 * @param maze the maze
	 */
	public void displayMaze(Maze3d maze);
	
	/**
	 * Display solution.
	 *
	 * @param s the s
	 */
	public void displaySolution (Solution s);
	
	/**
	 * Display directory.
	 *
	 * @param path the path
	 */
	public void displayDirectory(String[] path);

	/**
	 * Display command menu.
	 */
	public void displayCommandMenu();

	public void displayMazeGUI(Maze3d maze3d);

	public void getMazeName(String _name);
}
