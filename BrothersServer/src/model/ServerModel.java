package model;

import java.beans.XMLDecoder;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import presenter.ServerPropManager;
import presenter.ServerProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerModel.
 */
public class ServerModel extends CommonServerModel
{

	/** The num of clients. */
	public int numOfClients; 

	/** The port. */
	public int port;

	/** The stop. */
	public static boolean stop;

	/** The thread pool. */
	private ExecutorService threadPool;

	/** The server. */
	private ServerSocket server;

	/** The main server thread. */
	private Thread mainServerThread;

	/** The client handler. */
	private MazeClientHandler clientHandler;

	/** The decoder. */
	public XMLDecoder decoder;

	/** The clients handled. */
	private int numberOfClients;

	/** The server is close. */
	private boolean isServerClosed;

	/** The cp. */
	private ServerProperties cp;

	/**
	 * Instantiates a new server model.
	 */
	public ServerModel() 
	{
		setServerProperties();
		this.numberOfClients = 0;
		this.clientHandler = new MazeClientHandler();
		threadPool=Executors.newFixedThreadPool(cp.getNumOfClients());
		ServerModel.stop = false;
		this.isServerClosed = true;
	}


	/**
	 * Initialize server configuration from the XML.
	 */
	public void setServerProperties()
	{
		ServerProperties prop = new ServerPropManager().loadProp();
		this.cp = prop;
		this.port = cp.getPort();
		this.numOfClients = cp.getNumOfClients();
	}

	/* (non-Javadoc)
	 * @see model.CommonModel#open()
	 */
	@Override
	public void open()
	{
		try
		{
			server=new ServerSocket(this.cp.getPort());
			server.setSoTimeout(7*1000);
			this.isServerClosed = false;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

		mainServerThread=new Thread(new Runnable()
		{			
			@Override
			public void run()
			{
				while(!ServerModel.stop)
				{
					try 
					{
						final Socket someClient=server.accept();
						if(someClient!=null)
						{
							threadPool.execute(new Runnable()
							{									
								@Override
								public void run() 
								{
									try
									{										
										numberOfClients++;
										setChangedAndNotify("Now handling client number: "+numberOfClients);
										clientHandler.handleClient(someClient.getInputStream(), someClient.getOutputStream());
										setChangedAndNotify("Finished handling client "+numberOfClients);
										someClient.close();
									}
									catch(IOException e)
									{
										setChanged();
										notifyObservers(e);
									}									
								}
							});								
						}
					}
					catch (SocketTimeoutException e)
					{
						setChangedAndNotify("No client/s connected...");
					} 
					catch (IOException e)
					{
						setChanged();
						notifyObservers(e);
					}
				}
				setChangedAndNotify("No more new clients");
			} 
		});
		mainServerThread.start();
	}

	/* (non-Javadoc)
	 * @see model.CommonModel#close()
	 */
	@Override
	public void close()
	{
		if(this.isServerClosed == false)
		{
			this.isServerClosed = true;
			ServerModel.stop=true;	
			try 
			{
				this.clientHandler.saveToZip();
				setChangedAndNotify("Prepering for shut down");
				threadPool.shutdown();
				while(!(threadPool.awaitTermination(10, TimeUnit.SECONDS)));
				setChangedAndNotify("Tasks are done");
				mainServerThread.join();		
				server.close();
				threadPool.shutdownNow();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets the changed and notify.
	 *
	 * @param msg the new changed and notify
	 */
	private void setChangedAndNotify(String msg)
	{
		setChanged();
		notifyObservers(msg);
	}
}
