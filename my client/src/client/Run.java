package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Run 
{
	public static void main (String[] args) throws Exception
	{
//		InetAddress.getByName(host)
		System.out.println("CLIENT SIDE");
		Socket theServer = new Socket("127.0.0.1", 5400);
		System.out.println("Connected to server");
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
		PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		do 
		{
			line = inFromUser.readLine();
			outToServer.println(line);
			outToServer.flush();
			
			System.out.println(inFromServer.readLine());
		}
		while (!line.equals("exit"));
		
		inFromServer.readLine(); //bye
		
		inFromServer.close();
		outToServer.close();
		theServer.close();
		{
			
		}
	}
}
