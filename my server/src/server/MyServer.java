package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer
{
	int port;
	ServerSocket server;
	volatile boolean stop;
	Thread theMainServerThread;
	ExecutorService threadpool;

	ClientHandler ch;

	int numOfClients;

	public MyServer(int port, ClientHandler ch, int numberOfClients)
	{
		this.port = port;
		stop = false;
		this.ch = ch;
		numOfClients = numberOfClients;
	}

	public void start() throws Exception
	{
		threadpool = Executors.newFixedThreadPool(numOfClients);

		server = new ServerSocket(port);
		server.setSoTimeout(10*1000);

		theMainServerThread = new Thread (new Runnable() 
		{
			@Override
			public void run()
			{
				while (!stop)
				{
					try
					{
						Socket someClient = server.accept();
						threadpool.execute (new Runnable()
						{
							@Override
							public void run()
							{
								try
								{
									ch.handleClient(someClient.getInputStream(), someClient.getOutputStream());
									someClient.close();
								}
								catch(IOException e) 
								{
								}
							}
						});
					}
					catch (IOException e){}
				}
			}
		});

		theMainServerThread.start();
	}

	public void close() throws Exception
	{
		stop = true;
		threadpool.shutdown();
		theMainServerThread.join();
		server.close();
	}

	public static void main (String[] args) throws Exception
	{
		System.out.println("SERVER SIDE");
		System.out.println("type \"close the server\" to close" );
		MyServer server = new MyServer(5400, new MyClientHandler(), 10);
		server.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (! (in.readLine()).equals("close the server"));
		{
			server.close();
		}
	}
}
