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
					
				try 
				{
				String line;	
				System.out.println("You can start writing commands");
				System.out.println("Type manu to see syntax");
					while (!(line = in.readLine()).equals("exit")) 
					{
						String [] splittedLine = line.split(" ");
						if (commandMap.containsKey(splittedLine[0])) 
						{
							System.out.println("--- Command '" + line + "' is OK --- ");
							out.flush();
							commandMap.get(splittedLine[0]).doCommand(splittedLine);
							
						}
						else
						{
							System.out.println("ERROR: Wrong command: "+line);
							out.flush();
						}
					}
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				System.out.println("Good-Bye!!");
				out.flush();
				out.close();
			}
		}).start();
	}
}
