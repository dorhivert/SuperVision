package model;

import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonModel.
 */
public abstract class CommonModel implements Model 
{
	
	/** The controller. */
	Controller controller;
	
	/**
	 * Instantiates a new common model.
	 *
	 * @param controller the controller
	 */
	public CommonModel(Controller controller)
	{
		this.setController(controller);
	}
	
	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	
	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public Controller getController()
	{
		return this.controller;
	}
}
