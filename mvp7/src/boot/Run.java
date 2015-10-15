package boot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import model.PropManager;
import presenter.Presenter;
import presenter.Properties;
import view.CLI;
import view.MyView;


public class Run 
{
	public static void main(String[] args) 
	{
		Properties prop;
		try 
		{
			prop = new PropManager().readProp("properties.xml");
		} 
		catch (FileNotFoundException e) 
		{
			prop = new Properties();
		}
		
		MyModel model = new MyModel(prop);
		MyView view = new MyView();

		Presenter presenter = new Presenter(model, view);
		
		view.setCli(new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), presenter.getCommandMap()));
		
		view.addObserver(presenter);
		model.addObserver(presenter);
		
		view.start();
	}
}
