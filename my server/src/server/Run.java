package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Run
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket server = new ServerSocket(5400);
		Socket someClient = server.accept();
//		someClient.getInputStream()
		
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(someClient.getInputStream()));
		PrintWriter outToClient = new PrintWriter(someClient.getOutputStream());
		
		
		String line;
		while ( !(line = inFromClient.readLine()).equals("exit"))
		{
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
