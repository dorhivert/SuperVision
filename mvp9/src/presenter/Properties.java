package presenter;

import java.io.Serializable;

public class Properties implements Serializable
{	
	private static final long serialVersionUID = -2831963573720498979L;

	private int numOfThreads;
	private String generationAlgo;
	private String solveAlgo;
	
	
	public Properties()
	{
		this.numOfThreads = 10;
		this.generationAlgo = "my";
		this.solveAlgo = "astarair";
	}
	
	public Properties(int _num, String _gen, String _solve)
	{
		this.numOfThreads = _num;
		this.generationAlgo = new String(_gen);
		this.solveAlgo = new String(_solve);
	}
	
	public int getNumOfThreads() {
		return numOfThreads;
	}
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	public String getGenerationAlgo() {
		return generationAlgo;
	}
	public void setGenerationAlgo(String generationAlgo) {
		this.generationAlgo = generationAlgo;
	}
	public String getSolveAlgo() {
		return solveAlgo;
	}
	public void setSolveAlgo(String solveAlgo) {
		this.solveAlgo = solveAlgo;
	}

}
