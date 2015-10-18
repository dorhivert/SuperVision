package presenter;

import java.io.Serializable;

public class Properties implements Serializable
{	
	private static final long serialVersionUID = -2831963573720498979L;

	private int numOfThreads;
	private String generationAlgo;
	private String solveAlgo;
	private String gameInterface;
	
	
	public Properties()
	{
		this.numOfThreads = 10;
		this.generationAlgo = "my";
		this.solveAlgo = "astarair";
		this.gameInterface = "gui";
	}
	
	public Properties(int _num, String _gen, String _solve, String _interface)
	{
		this.numOfThreads = _num;
		this.generationAlgo = new String(_gen);
		this.solveAlgo = new String(_solve);
		this.gameInterface = new String(_interface);
	}
	
	public Properties(String _num, String _gen, String _solve, String _interface)
	{
		this.numOfThreads = Integer.parseInt(_num);
		this.generationAlgo = new String(_gen);
		this.solveAlgo = new String(_solve);
		this.gameInterface = new String(_interface);
	}
	
	public int getNumOfThreads()
	{
		return numOfThreads;
	}
	public String getGenerationAlgo()
	{
		return generationAlgo;
	}
	public String getSolveAlgo()
	{
		return solveAlgo;
	}
	public String getGameInterface()
	{
		return gameInterface;
	}
}
