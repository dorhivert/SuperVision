package model;

import java.util.List;

import controller.Controller;

/**
 * The Interface Model.
 */
public interface Model {

	/**
	 * Sets the controller.
	 *
	 * @param c the new controller
	 */
	public void setController(Controller c);
	

	/**
	 * Stop.
	 */
	public void stop();
}
