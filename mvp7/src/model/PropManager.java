package model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;

public class PropManager
{

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
}
