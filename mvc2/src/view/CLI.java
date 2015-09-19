package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

// TODO: Auto-generated Javadoc
/**
 * The Class CLI.
 */
public class CLI extends Thread
{

	/** The in. */
	BufferedReader in;
	
	/** The out. */
	PrintWriter out;
	
	/** The command map. */
	HashMap<String,Command> commandMap;

	/**
	 * Instantiates a new cli.
	 *
	 * @param _in the _in
	 * @param _out the _out
	 * @param _cm the _cm
	 */
	public CLI (BufferedReader _in, PrintWriter _out, HashMap<String,Command> _cm)
	{
		this.in = _in;
		this.out = _out;
		this.commandMap = _cm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#start()
	 */
	public void start()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run() 
			{
				String line;
				Command specificCommand;
				
				try 
				{
					while (!(line = in.readLine()).equals("exit")) 
					{
						if (commandMap.containsKey(line)) 
						{
							System.out.println("Command" + line + "is OK");
							out.println("Command" + line + "is OK");
							out.flush();
							specificCommand = commandMap.get(line);
							specificCommand.doCommand();
						}
						else
						{
							System.out.println("You have givven me a bad command: "+line);
							out.println("You have givven me a bad command: "+line);
							out.flush();
						}
					}
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}).start();
	}
}
