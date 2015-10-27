package model;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The Class TCPIPServer.
 */
public class TCPIPServer{

	/** The port. */
	private int port;
	
	/** The boolean stopped, informs across threads if the server has been stopped. */
	private volatile boolean stopped;
	
	/** The ClientHandler object. */
	private ClientHandler ch;
	
	/**
	 * Instantiates a new TCPIP server.
	 *
	 * @param port the port to listen to
	 * @param ch the Client Handler object
	 */
	public TCPIPServer(int port, ClientHandler ch) {
		this.port = port;
		this.ch = ch;
		

	}

	/**
	 * Start the server.
	 *
	 * Searches for clients every 500 ms, while the server has not been stopped.
	 *
	 * @param numOfClients the number of clients in the thread pool
	 */
	public void startServer(int numOfClients){
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(500); 
		} catch (IOException e1) {e1.printStackTrace();}
		ExecutorService threadPool = Executors.newFixedThreadPool(numOfClients);
		while (!stopped) {
			try {
				final Socket aClient = server.accept();
				
				threadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());

							aClient.getOutputStream().close();
							aClient.getInputStream().close();
							aClient.close();
						} catch (IOException e) {}
					}
				});
			} catch (IOException e1) {}
		}
		try {
			threadPool.shutdown();
			try {
				if(threadPool.awaitTermination(50, TimeUnit.MILLISECONDS));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ch.stop();
			server.close();
		} catch (IOException e) {e.printStackTrace();}
	}

	/**
	 * Stop server.
	 */
	public void stopServer() {
		stopped = true;
	}
	
}
