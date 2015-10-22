package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import model.PropManager;
import presenter.Presenter;
import presenter.Properties;
import view.CLI;
import view.MyGUIView;
import view.MyView;
import view.View;


public class Run 
{
	public static void main(String[] args) 
	{
		boolean flag = false;
		Properties prop = new PropManager().loadProp();
		MyModel model = new MyModel(prop);
		View view = new MyGUIView("Java Maze Project", 650, 590);
		if (prop.getGameInterface().equals("cli"))
		{
			view = new MyView();
			flag = true;
		}
		Presenter presenter = new Presenter(model, view);
		if (flag) 
		{
			((MyView) view).setCli(new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), presenter.getCommandMap()));
			((MyView) view).addObserver(presenter);
		}
		else
		{
			((MyGUIView) view).addObserver(presenter);
		}
		model.addObserver(presenter);
		view.start();
	}
}
