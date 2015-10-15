package model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;

public class PropManager {

	public PropManager(){}
	
	public Properties readProp(String filename) throws FileNotFoundException
	{
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		Properties prop = (Properties) (decoder.readObject());
		decoder.close();
		return prop;
	}
	public void writeProp(Properties prop, String filename) throws FileNotFoundException
	{
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		
		encoder.writeObject(prop);
		encoder.flush();
		encoder.close();
	}
}
