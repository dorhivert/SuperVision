package presenter;

import java.io.Serializable;


/**
 * The Class ServerProperties.
 */
@SuppressWarnings("serial")
public class ServerProperties implements Serializable
{	

	/** The num of clients. */
	private int numOfClients;
	
	/** The port. */
	private int port;
	
	
	/**
	 * Instantiates a new server properties.
	 */
	public ServerProperties()
	{
		this.numOfClients = 10;
		this.port = 5400;
	}
	
	/**
	 * Instantiates a new server properties.
	 *
	 * @param _num the _num
	 * @param _port the _port
	 */
	public ServerProperties(int _num, int _port)
	{
		this.numOfClients = _num;
		this.port = _port;
	}
	
	/**
	 * Instantiates a new server properties.
	 *
	 * @param _num the _num
	 * @param _port the _port
	 */
	public ServerProperties(String _num, String _port)
	{
		this.numOfClients = Integer.parseInt(_num);
		this.port = Integer.parseInt(_port);
	}

	/**
	 * Gets the num of clients.
	 *
	 * @return the num of clients
	 */
	public int getNumOfClients()
	{
		return numOfClients;
	}

	/**
	 * Sets the num of clients.
	 *
	 * @param numOfClients the new num of clients
	 */
	public void setNumOfClients(int numOfClients)
	{
		this.numOfClients = numOfClients;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}
}
