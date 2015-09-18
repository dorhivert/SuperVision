package view;

import controller.Controller;
import mazeGenerators.Maze3d;

public abstract class CommonView implements View{

	Controller controller;
	CLI cli;
	
	public CommonView(Controller controller,CLI cli)
	{
		this.cli = cli;
		this.controller = controller;
	}
	
	
	public void setCLI(CLI cli)
	{
		this.cli = cli;

	}
	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	public Controller getController()
	{
		return this.controller;
	}
	public CLI getCLI()
	{
		return cli;
	}
	
	@Override
	public void start() {
		cli.start();
	}

	@Override
	public void writeToConsole(String s) {
		cli.out.println(s);
		System.out.println(s);
	}



}
