package boot;

import model.Model;
import model.MyModel;
import view.MyView;
import view.View;
import controller.Controller;
import controller.MyController;

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
		Model model = new MyModel(controller);
		View view = new MyView(controller);

		controller.setModel(model);
		controller.setView(view);

		view.start();
	}
}
