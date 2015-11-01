package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.ServerModel;
import presenter.ServerPresenter;
import view.CLI;
import view.CommonView;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerRun.
 */
public class ServerRun 
{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{

		ServerModel m = new ServerModel();

		CommonView v = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out));

		ServerPresenter p = new ServerPresenter(m, v);

		v.addObserver(p);
		m.addObserver(p);

		v.start();
	
	}
}
