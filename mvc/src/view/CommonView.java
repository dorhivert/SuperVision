package view;

import controller.Controller;
import mazeGenerators.Maze3d;

public abstract class CommonView implements View{

	Controller controller;
	
	public CommonView(Controller controller,CLI cli)
	{
		this.controller = controller;
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
