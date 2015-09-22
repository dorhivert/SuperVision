package model;

import controller.Controller;

public abstract class CommonModel implements Model {
	
	Controller controller;
	
	public CommonModel(Controller controller)
	{
		this.controller = controller;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	

}
