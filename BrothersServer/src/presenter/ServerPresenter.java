package presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class ServerPresenter.
 */
public class ServerPresenter implements Observer
{


	/** The view. */
	private View view;

	/** The model. */
	private Model model;

	/** The command map. */
	private HashMap<String,Command> commandMap;


	/**
	 * Instantiates a new server presenter.
	 *
	 * @param m the m
	 * @param v the v
	 */
	public ServerPresenter(Model m, View v)
	{
		this.model = m;
		this.view = v;
		this.commandMap = new HashMap<String,Command>();
		InitCommands();
	}


	/**
	 * Inits the commands.
	 */
	private void InitCommands() 
	{
		commandMap.put("close", new Command() 
		{

			@Override
			public void doCommand(String[] args) 
			{
				try 
				{
					model.close();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});

		commandMap.put("start", new Command() 
		{

			@Override
			public void doCommand(String[] args)
			{
				try 
				{
					model.open();
				} catch (IOException e)
				{
					e.printStackTrace();
				}

			}
		});
	}


	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public View getView() {
		return view;
	}


	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	public void setView(View view) {
		this.view = view;
	}


	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}


	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(Model model) {
		this.model = model;
	}


	/**
	 * Gets the command map.
	 *
	 * @return the command map
	 */
	public HashMap<String, Command> getCommandMap() {
		return commandMap;
	}


	/**
	 * Sets the command map.
	 *
	 * @param commandMap the command map
	 */
	public void setCommandMap(HashMap<String, Command> commandMap) {
		this.commandMap = commandMap;
	}


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1)
	{
		
		if (arg0 instanceof Model)
		{
			String data = ((String) arg1);
			notifyView(data);
		}
		else
		{
			
			if (arg0 instanceof View)
			{
				String[] splittedLine = ((String) arg1).split(" ");
				String[] myData = splittedLine;
				Command cData = commandMap.get(myData[0]);
				cData.doCommand(myData);
			}
			else
			{
				System.out.println("FATAL ERROR:  UPDATE FROM SPACE (Presenter.update)");
				return;
			}
		}
	}

	/**
	 * Notify view.
	 *
	 * @param msg the msg
	 */
	private void notifyView(String msg) 
	{
		this.view.writeToConsole("*** CPU Notification: "+msg);
	}



}
