package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI extends Thread{

	BufferedReader in;
	PrintWriter out;
	HashMap<String,Command> map;

	public CLI(BufferedReader in,PrintWriter out,HashMap<String,Command> map)
	{
		this.in = in;
		this.out = out;
		this.map = map;
	}

	public void start()
	{
		new Thread(new Runnable(){

			public void run(){
				try {
					String line;

					while(!(line = in.readLine()).equals("exit"))
					{
						String[] splittedLine = line.split(" ");
						if(map.containsKey(splittedLine[0]))
						{
							System.out.println("calling command:" + line);
							map.get(splittedLine[0]).doCommand(splittedLine);
						}
						else
						{
							System.out.println("Incorrect command:" + line);
							out.println("Bad command!, try again or enter exit to exit");
							out.flush();
						}
					}
				}catch (IOException e)
				{
					e.printStackTrace();

				}
				out.println("Goodbye");
				out.close();

			}
		}).start();
	}

}
