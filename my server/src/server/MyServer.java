package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer
{
	int port;
	ServerSocket server;
	volatile boolean stop;
	Thread theMainServerThread;

	public MyServer(int port)
	{
		this.port = port;
		stop = false;
	}

	public void start() throws Exception
	{
		server = new ServerSocket(port);
		while (!stop)
		{
			try 
			{
				ServerSocket server = new ServerSocket(5400);
				Socket someClient = server.accept();
				//		someClient.getInputStream()
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(someClient.getInputStream()));
				PrintWriter outToClient = new PrintWriter(
						someClient.getOutputStream());
				String line;
				while (!(line = inFromClient.readLine()).equals("exit")) {
					StringBuilder sb = new StringBuilder(line);
					outToClient.println(sb.reverse().toString());
					outToClient.flush();
				}
				outToClient.println("bye");
				outToClient.flush();
				inFromClient.close();
				outToClient.close();
				someClient.close();

			} catch(IOException e) 
			{
			}
		}
	}
	public void close() throws Exception
	{
		stop = true;
		server.close();
	}

	public static void main (String[] args) throws Exception
	{
		System.out.println("SERVER SIDE");
		System.out.println("type \"close the server\" to close" );
		MyServer server = new MyServer(5400);
		server.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while (! (in.readLine()).equals("close the server"));
		{
			server.close();
		}
	}
}
