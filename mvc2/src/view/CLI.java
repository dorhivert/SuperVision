package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI extends Thread
{

	BufferedReader in;
	PrintWriter out;
	HashMap<String,Command> commandMap;

	public CLI (BufferedReader _in, PrintWriter _out, HashMap<String,Command> _cm)
	{
		this.in = _in;
		this.out = _out;
		this.commandMap = _cm;
	}

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
