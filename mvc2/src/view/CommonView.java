package view;

import controller.Controller;

public abstract class CommonView implements View
{
	Controller controller;
	
	public CommonView(Controller controller)
	{
		this.setController(controller);
	}
	
	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	
	public Controller getController()
	{
		return this.controller;
	}
}
