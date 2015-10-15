package presenter;

import java.io.Serializable;

public class Properties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 555913593004844041L;

	private int howManyThreads;
	private String generationAlgo;
	private String solvingAlgo;
	
	
	
	
	public Properties(int howManyThreads, String generationAlgo, String solvingAlgo) {
		super();
		this.howManyThreads = howManyThreads;
		this.generationAlgo = generationAlgo;
		this.solvingAlgo = solvingAlgo;		
	}
	public Properties() // default values
	{
		super();
		this.howManyThreads = 10;
		this.generationAlgo = "DFS";
		this.solvingAlgo = "astarair";

	}
	
	public int getHowManyThreads() {
		return howManyThreads;
	}
	public void setHowManyThreads(int howManyThreads) {
		this.howManyThreads = howManyThreads;
	}
	public String getGenerationAlgo() {
		return generationAlgo;
	}
	public void setGenerationAlgo(String generationAlgo) {
		this.generationAlgo = generationAlgo;
	}
	public String getSolvingAlgo() {
		return solvingAlgo;
	}
	public void setSolvingAlgo(String solvingAlgo) {
		this.solvingAlgo = solvingAlgo;
	}

}
