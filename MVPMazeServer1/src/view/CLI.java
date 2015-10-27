package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class CLI.
 */
public class CLI extends CommonView 
{

	/** The in. */
	private BufferedReader in;

	/** The out. */
	private PrintWriter out;

	/**
	 * Instantiates a new cli.
	 *
	 * @param in the in
	 * @param out the out
	 */
	public CLI(BufferedReader in,PrintWriter out)
	{
		this.in = in;
		this.out = out;
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() 
	{
		new Thread(new Runnable() 
		{
			@Override
			public void run() {
				String input;
				out.println("----- Server - Command Line -----");
				out.println("Hello and welcome to the server side. \ntype \"start\" to open it\ntype \"close\" to stop it");
				out.flush();
				try 
				{

					input = in.readLine();

					do 
					{
						setChanged();
						notifyObservers(input);
						out.flush();
						input = in.readLine();
					}
					while (!input.equals("close"));
					setChanged();
					notifyObservers(input);
					out.println("--- EXIT ---");
				} 
				catch (IOException e)
				{

					setChanged();
					notifyObservers("Invalid command");
				}
				out.close();
				try 
				{
					in.close();
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	/* (non-Javadoc)
	 * @see view.View#displayMessage(java.lang.String)
	 */
	@Override
	public void displayMessage(String msg)
	{
		out.println(msg);
		out.flush();
	}

	/* (non-Javadoc)
	 * @see view.View#displayException(java.lang.Exception)
	 */
	@Override
	public void displayException(Exception e) 
	{
		e.printStackTrace();
	}

	/* (non-Javadoc)
	 * @see view.View#writeToConsole(java.lang.String)
	 */
	@Override
	public void writeToConsole(String string) 
	{
		out.println(string);
		out.flush();
	}
}
