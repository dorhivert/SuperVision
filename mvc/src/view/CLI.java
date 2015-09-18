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

					while((line = in.readLine())!=("exit"))
					{
						if(map.containsKey(line))
						{
							System.out.println("calling command:" + line);
							map.get(line).doCommand();
						}
						else
						{
							System.out.println("Incorrect command:" + line);
							out.println("Bad command!, try again or enter exit to exit");
							out.flush();
						}
					}
				}catch (IOException e) {
					e.printStackTrace();

				}

			}
		}).start();
	}

}
