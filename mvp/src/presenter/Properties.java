package presenter;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

	public void writeProperties(String filename) throws FileNotFoundException
	{
		XMLEncoder encoder;

		encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		encoder.writeObject(this);
		encoder.flush();
		encoder.close();

	}
	
	public void readProperties(String filename) throws FileNotFoundException
	{
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		Properties tmpProp = (Properties) decoder.readObject();
		decoder.close();
		this.howManyThreads = tmpProp.howManyThreads;
		this.generationAlgo = tmpProp.generationAlgo;
		this.solvingAlgo = tmpProp.solvingAlgo;
	}
}
