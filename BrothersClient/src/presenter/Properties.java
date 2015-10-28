package presenter;

import java.io.Serializable;


/**
 * The Class Properties.
 */
@SuppressWarnings("serial")
public class Properties implements Serializable
{	


	/** The num of threads. */
	private int numOfThreads;
	
	/** The generation algo. */
	private String generationAlgo;
	
	/** The solve algo. */
	private String solveAlgo;
	
	/** The game interface. */
	private String gameInterface;
	
	/** The port. */
	private int port;
	
	/** The ip. */
	private String ip;
	
	
	/**
	 * Instantiates a new properties.
	 */
	public Properties()
	{
		this.numOfThreads = 10;
		this.generationAlgo = "my";
		this.solveAlgo = "astarair";
		this.gameInterface = "gui";
		this.port = 5400 ;
		this.ip = "localhost" ;
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
	public void setPort(int port) 
	{
		this.port = port;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Instantiates a new properties.
	 *
	 * @param _num the _num
	 * @param _gen the _gen
	 * @param _solve the _solve
	 * @param _interface the _interface
	 * @param _port the _port
	 * @param _ip the _ip
	 */
	public Properties(int _num, String _interface, String _gen, String _solve, int _port, String _ip )
	{
		this.numOfThreads = _num;
		this.gameInterface = new String(_interface);
		this.generationAlgo = new String(_gen);
		this.solveAlgo = new String(_solve);
	
		this.port = _port;
		this.ip = new String(_ip);
	}
	
	/**
	 * Instantiates a new properties.
	 *
	 * @param _num the _num
	 * @param _gen the _gen
	 * @param _solve the _solve
	 * @param _interface the _interface
	 * @param _port the _port
	 * @param _ip the _ip
	 */
	public Properties(String _num, String _interface, String _gen, String _solve, String _port, String _ip)
	{
		this.numOfThreads = Integer.parseInt(_num);
		this.generationAlgo = new String(_gen);
		this.solveAlgo = new String(_solve);
		this.gameInterface = new String(_interface);
		this.ip = new String(_ip);
		this.port = Integer.parseInt(_port);
	}
	
	/**
	 * Gets the num of threads.
	 *
	 * @return the num of threads
	 */
	public int getNumOfThreads()
	{
		return numOfThreads;
	}
	
	/**
	 * Sets the num of threads.
	 *
	 * @param numOfThreads the new num of threads
	 */
	public void setNumOfThreads(int numOfThreads)
	{
		this.numOfThreads = numOfThreads;
	}
	
	/**
	 * Gets the generation algo.
	 *
	 * @return the generation algo
	 */
	public String getGenerationAlgo()
	{
		return generationAlgo;
	}
	
	/**
	 * Sets the generation algo.
	 *
	 * @param generationAlgo the new generation algo
	 */
	public void setGenerationAlgo(String generationAlgo) 
	{
		this.generationAlgo = generationAlgo;
	}
	
	/**
	 * Gets the solve algo.
	 *
	 * @return the solve algo
	 */
	public String getSolveAlgo()
	{
		return solveAlgo;
	}
	
	/**
	 * Sets the solve algo.
	 *
	 * @param solveAlgo the new solve algo
	 */
	public void setSolveAlgo(String solveAlgo) 
	{
		this.solveAlgo = solveAlgo;
	}
	
	/**
	 * Gets the game interface.
	 *
	 * @return the game interface
	 */
	public String getGameInterface() 
	{
		return gameInterface;
	}
	
	/**
	 * Sets the game interface.
	 *
	 * @param gameInterface the new game interface
	 */
	public void setGameInterface(String gameInterface)
	{
		this.gameInterface = gameInterface;
	}
}
