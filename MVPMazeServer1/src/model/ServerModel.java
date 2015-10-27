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
public class ServerModel extends CommonModel
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
	private MyClientHandler clientHandler;
	
	/** The decoder. */
	public XMLDecoder decoder;
	
	/** The clients handled. */
	private int clientsHandled;
	
	/** The server is close. */
	private boolean serverIsClose;
	
	/** The cp. */
	private ServerProperties cp;

	/**
	 * Instantiates a new server model.
	 */
	public ServerModel() 
	{
		setXML();
		this.clientsHandled = 0;
		this.clientHandler = new MyClientHandler();
		threadPool=Executors.newFixedThreadPool(cp.getNumOfClients());
		ServerModel.stop = false;
		this.serverIsClose = true;
	}


	/**
	 * Initialize configuration from the XML.
	 */
	public void setXML()
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
			server.setSoTimeout(10*1000);
			this.serverIsClose = false;
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
					try {
						final Socket someClient=server.accept();
						if(someClient!=null){
							threadPool.execute(new Runnable()
							{									
								@Override
								public void run() 
								{
									try{										
										clientsHandled++;
										setChanged();
										notifyObservers("handling client "+clientsHandled);
										clientHandler.handleClient(someClient.getInputStream(), someClient.getOutputStream());
										setChanged();
										notifyObservers("done handling client "+clientsHandled);
										someClient.close();
									}catch(IOException e){
										setChanged();
										notifyObservers(e);
									}									
								}
							});								
						}
					}
					catch (SocketTimeoutException e)
					{
						setChanged();
						notifyObservers("no client connected...");
					} 
					catch (IOException e)
					{
						setChanged();
						notifyObservers(e);
					}
				}
				setChanged();
				notifyObservers("done accepting new clients.");
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
		if(this.serverIsClose == false)
		{
			this.serverIsClose = true;
			ServerModel.stop=true;	
			try {
				this.clientHandler.saveToZip();
				setChanged();
				notifyObservers("shutting down");
				threadPool.shutdown();
				while(!(threadPool.awaitTermination(10, TimeUnit.SECONDS)));
				setChanged();
				notifyObservers("all the tasks have finished");
				mainServerThread.join();		
				setChanged();
				notifyObservers("main server thread is done");
				server.close();
				setChanged();
				notifyObservers("server is safely closed");
				threadPool.shutdownNow();
			} catch (Exception e)
			{
				setChanged();
				notifyObservers(e);
			}
		}
	}


}
