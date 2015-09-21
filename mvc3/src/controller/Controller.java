package controller;

import java.util.HashMap;

import model.Model;
import view.View;

/**
 * The Interface Controller.
 */
public interface Controller 
{
	
	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	public void setView (View view);
	
	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel (Model model);
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public HashMap<String,Command> getMap();
	
	/**
	 * Gets the files in directory.
	 *
	 * @param path the path
	 * @return the files in directory
	 */
	public void getFilesInDirectory(String path);
	
	/**
	 * Display directory.
	 *
	 * @param path the path
	 */
	public void displayDirectory(String[] path);
	
	/**
	 * Generate3d maze.
	 *
	 * @param name the name
	 * @param size the size
	 */
	public void generate3dMaze (String name, int size);
	
	/**
	 * Display maze.
	 *
	 * @param name the name
	 */
	public void displayMaze (String name);
	
	/**
	 * Gets the cross section.
	 *
	 * @param name the name
	 * @param index the index
	 * @param xyz the xyz
	 * @return the cross section
	 */
	public void getCrossSection (String name, int index, char xyz);
	
	/**
	 * Display maze size.
	 *
	 * @param name the name
	 */
	public void displayMazeSize(String name);
	
	/**
	 * Display file size.
	 *
	 * @param name the name
	 */
	public void displayFileSize(String name);

	public void displaySolution(String name);
	
}
