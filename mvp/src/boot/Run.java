package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.Model;
import model.MyModel;
import presenter.Presenter;
import view.CLI;
import view.MyView;
import view.View;

public class Run {

	public static void main(String[] args) {
		Model model = new MyModel();
		View view = new MyView();
		
		Presenter presenter = new Presenter(model,view);
		
		((MyView) view).setCli(new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), presenter.getCommandMap()));

		((MyView) view).addObserver(presenter);
		((MyModel) model).addObserver(presenter);
		
		view.start();
	}

}
