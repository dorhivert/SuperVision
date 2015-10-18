package model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import presenter.Properties;

public class PropManager
{
	private XMLDecoder decoder;
	private XMLEncoder encoder;
	private Properties prop;

	public XMLDecoder getDecoder() {
		return decoder;
	}

	public void setDecoder(XMLDecoder decoder) {
		this.decoder = decoder;
	}

	public XMLEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(XMLEncoder encoder) {
		this.encoder = encoder;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public PropManager(){};
	
	public Properties readProp(String path) throws FileNotFoundException
	{
		XMLDecoder decode = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
		Properties _prop = (Properties) decode.readObject();
		decode.close();
		return _prop;
	}
	
	public void writeProp(Properties _prop, String _fileName) throws FileNotFoundException
	{
		XMLEncoder encode = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(_fileName)));
		encode.writeObject(_prop);
		encode.flush();
		encode.close();
	}
	
	public Properties loadProp()
	{
		File myFile = new File("properties.xml");
		try
		{
			if(!myFile.createNewFile())
			{
				this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(myFile)));
				this.prop = (Properties) decoder.readObject();
				decoder.close();
			} 
			else
			{
				this.prop = new Properties(9,"my","astarair","firstRun");
				this.encoder = new XMLEncoder(new FileOutputStream("properties.xml"));
				encoder.writeObject(prop);
				encoder.flush();
				encoder.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return prop;
	}
	
	public Properties setNewProperties(String[] settings)
	{
		
		@SuppressWarnings("unused")
		File myFile = new File("properties.xml");
		try
		{		
				this.prop = new Properties(settings[1],settings[3],settings[4],settings[2]);
				this.encoder = new XMLEncoder(new FileOutputStream("properties.xml"));
				encoder.writeObject(prop);
				encoder.flush();
				encoder.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return prop;
	}

	public Properties loadNewPropsFromFile(String[] args)
	{
		File mySecondFile = new File(args[1]);
		try
		{
				this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(mySecondFile)));
				this.prop = (Properties) decoder.readObject();
				decoder.close();

				this.encoder = new XMLEncoder(new FileOutputStream("properties.xml"));
				encoder.writeObject(prop);
				encoder.flush();
				encoder.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return prop;
	}
}
