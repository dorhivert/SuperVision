package boot;

import model.myModel;
import view.MyAsciiArtMaker;
import controller.Controller;

public class Run {

	public static void main(String[] args)
	{
		myModel model = new myModel();
		MyAsciiArtMaker win = new MyAsciiArtMaker("ASCII maker", 500, 300);
		Controller controller = new Controller(win,model);
		model.setController(controller);
		win.setController(controller);
		win.run();

	}

}
