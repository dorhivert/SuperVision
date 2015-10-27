package presenter;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientSettings implements Serializable
{	


	private String gameInterface;
	private int port;
	private String address;
	
	
	public ClientSettings()
	{
		this.port = 5400;
		this.address = "localhost";
		this.gameInterface = "gui";
	}
	
	public ClientSettings(int port, String address, String _interface)
	{
		this.port = port;
		this.address = address;
		this.gameInterface = new String(_interface);
	}
	
	public ClientSettings(String port,String address, String _interface)
	{
		this.port = Integer.parseInt(port);
		this.address = address;
		this.gameInterface = new String(_interface);
	}
	

	public String getGameInterface() 
	{
		return gameInterface;
	}
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public void setGameInterface(String gameInterface)
	{
		this.gameInterface = gameInterface;
	}
}
