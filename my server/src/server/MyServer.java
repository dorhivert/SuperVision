package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer
{
	int port;
	ServerSocket server;
	volatile boolean stop;

	public MyServer(int port)
	{
		this.port = port;

	}

	public void start() throws Exception
	{
		server = new ServerSocket(port);
		while (!stop)
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
			server.close();
		}
	}
	public void close() throws Exception
	{
		stop = true;
		server.close();
	}
}
