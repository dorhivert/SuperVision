package boot;

import model.MyModel;
import model.PropManager;
import presenter.Presenter;
import presenter.Properties;
import view.MyGUIView;


public class Run 
{
	public static void main(String[] args) 
	{
		Properties prop = new PropManager().loadProp();
		
		MyModel model = new MyModel(prop);
		
		//MyView view = new MyView();
		MyGUIView view = new MyGUIView("test", 500, 500);
		

		Presenter presenter = new Presenter(model, view);
		
		//view.setCli(new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), presenter.getCommandMap()));
		
		view.addObserver(presenter);
		model.addObserver(presenter);
		
		view.start();
	}
}
