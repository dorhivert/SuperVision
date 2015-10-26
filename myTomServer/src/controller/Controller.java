package controller;

import java.util.HashMap;

import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
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
	 * Generate3d maze.
	 *
	 * @param name the name
	 * @param size the size
	 */
	public void generate3dMaze (String name, int size);
	
	
	/**
	 * Command manu.
	 */
	public void commandManu();

	public void notifyView(String msg);
	
}
