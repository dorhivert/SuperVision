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


/**
 * The Class PropManager.
 */
public class PropManager
{
	
	/** The decoder. */
	private XMLDecoder decoder;
	
	/** The encoder. */
	private XMLEncoder encoder;
	
	/** The prop. */
	private Properties prop;
	
	/** The my file. */
	private File myFile;

	/**
	 * Gets the decoder.
	 *
	 * @return the decoder
	 */
	public XMLDecoder getDecoder() {
		return decoder;
	}

	/**
	 * Sets the decoder.
	 *
	 * @param decoder the new decoder
	 */
	public void setDecoder(XMLDecoder decoder) {
		this.decoder = decoder;
	}

	/**
	 * Gets the encoder.
	 *
	 * @return the encoder
	 */
	public XMLEncoder getEncoder() {
		return encoder;
	}

	/**
	 * Sets the encoder.
	 *
	 * @param encoder the new encoder
	 */
	public void setEncoder(XMLEncoder encoder) {
		this.encoder = encoder;
	}

	/**
	 * Gets the prop.
	 *
	 * @return the prop
	 */
	public Properties getProp() {
		return prop;
	}

	/**
	 * Sets the prop.
	 *
	 * @param prop the new prop
	 */
	public void setProp(Properties prop) {
		this.prop = prop;
	}

	/**
	 * Instantiates a new prop manager.
	 */
	public PropManager(){};
	
	/**
	 * Read prop.
	 *
	 * @param path the path
	 * @return the properties
	 * @throws FileNotFoundException the file not found exception
	 */
	public Properties readProp(String path) throws FileNotFoundException
	{
		XMLDecoder decode = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
		Properties _prop = (Properties) decode.readObject();
		decode.close();
		return _prop;
	}
	
	/**
	 * Write prop.
	 *
	 * @param _prop the _prop
	 * @param _fileName the _file name
	 * @throws FileNotFoundException the file not found exception
	 */
	public void writeProp(Properties _prop, String _fileName) throws FileNotFoundException
	{
		XMLEncoder encode = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(_fileName)));
		encode.writeObject(_prop);
		encode.flush();
		encode.close();
	}
	
	/**
	 * Load prop.
	 *
	 * @return the properties
	 */
	public Properties loadProp()
	{
		myFile = new File("properties.xml");
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
				this.prop = new Properties();
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
	
	/**
	 * Sets the new properties.
	 *
	 * @param settings the new new properties
	 */
	public void setNewProperties(String[] settings)
	{
		myFile = new File("properties.xml");
		try
		{		
				this.prop = new Properties(settings[1],settings[3],settings[4],settings[2],settings[5],settings[6]);
				this.encoder = new XMLEncoder(new FileOutputStream("properties.xml"));
				encoder.writeObject(prop);
				encoder.flush();
				encoder.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Load new props from file.
	 *
	 * @param args the args
	 */
	public void loadNewPropsFromFile(String[] args)
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
	}
}
