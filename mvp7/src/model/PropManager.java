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
	Properties prop;
	XMLDecoder decoder;
	XMLEncoder encoder;

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

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
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(_fileName)));
		encoder.writeObject(_prop);
		encoder.flush();
		encoder.close();
	}
	public Properties loadProp()
	{
		File myFile = new File("properties.xml");
		try
		{
			if(!myFile.createNewFile())
			{
				decoder = new XMLDecoder(new FileInputStream(myFile));
				prop = (Properties) decoder.readObject();
				decoder.close();
			}
			else
			{
				prop = new Properties(9,"my","astarair");
				XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(myFile)));
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
}
