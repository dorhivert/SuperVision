package boot;

import java.io.FileNotFoundException;

import presenter.Properties;

public class Run {

	public static void main(String[] args) {
//		Model model = new MyModel();
//		View view = new MyView();
//		
//		Presenter presenter = new Presenter(model,view);
//		
//		((MyView) view).setCli(new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), presenter.getCommandMap()));
//
//		((MyView) view).addObserver(presenter);
//		((MyModel) model).addObserver(presenter);
//		
//		view.start();
		Properties prop = new Properties();
		try {
			prop.writeProperties("properties.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prop.getGenerationAlgo());
	}

}
