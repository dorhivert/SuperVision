package view;

import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonView.
 */
public abstract class CommonView implements View
{
	
	/** The controller. */
	Controller controller;
	
	/**
	 * Instantiates a new common view.
	 *
	 * @param controller the controller
	 */
	public CommonView(Controller controller)
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
