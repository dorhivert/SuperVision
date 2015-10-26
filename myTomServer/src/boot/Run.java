package boot;

import controller.Controller;
import controller.MyController;
import controller.Properties;
import model.Model;
import model.MyModel;
import model.PropManager;
import view.MyView;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class Run.
 */
public class Run 
{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		Controller controller = new MyController();
		PropManager pm = new PropManager();
		Properties prop = pm.loadProp();
		Model model = new MyModel(prop, controller);
		View view = new MyView(controller);

		controller.setModel(model);
		controller.setView(view);

		view.start();
	}
}
