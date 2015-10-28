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


/**
 * The Class ServerPropManager.
 */
public class ServerPropManager
{
	
	/** The decoder. */
	private XMLDecoder decoder;
	
	/** The encoder. */
	private XMLEncoder encoder;
	
	/** The prop. */
	private ServerProperties prop;
	
	/** The my file. */
	private File myFile;

	/**
	 * Gets the decoder.
	 *
	 * @return the decoder
	 */
	public XMLDecoder getDecoder() 
	{
		return decoder;
	}

	/**
	 * Sets the decoder.
	 *
	 * @param decoder the new decoder
	 */
	public void setDecoder(XMLDecoder decoder)
	{
		this.decoder = decoder;
	}

	/**
	 * Gets the encoder.
	 *
	 * @return the encoder
	 */
	public XMLEncoder getEncoder()
	{
		return encoder;
	}

	/**
	 * Sets the encoder.
	 *
	 * @param encoder the new encoder
	 */
	public void setEncoder(XMLEncoder encoder)
	{
		this.encoder = encoder;
	}

	/**
	 * Gets the prop.
	 *
	 * @return the prop
	 */
	public ServerProperties getProp()
	{
		return prop;
	}

	/**
	 * Sets the prop.
	 *
	 * @param prop the new prop
	 */
	public void setProp(ServerProperties prop) 
	{
		this.prop = prop;
	}

	/**
	 * Instantiates a new server prop manager.
	 */
	public ServerPropManager(){};
	
	/**
	 * Read prop.
	 *
	 * @param path the path
	 * @return the server properties
	 * @throws FileNotFoundException the file not found exception
	 */
	public ServerProperties readProp(String path) throws FileNotFoundException
	{
		XMLDecoder decode = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
		ServerProperties _prop = (ServerProperties) decode.readObject();
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
	public void writeProp(ServerProperties _prop, String _fileName) throws FileNotFoundException
	{
		XMLEncoder encode = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(_fileName)));
		encode.writeObject(_prop);
		encode.flush();
		encode.close();
	}
	
	/**
	 * Load prop.
	 *
	 * @return the server properties
	 */
	public ServerProperties loadProp()
	{
		myFile = new File("Serverproperties.xml");
		try
		{
			if(!myFile.createNewFile())
			{
				this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(myFile)));
				this.prop = (ServerProperties) decoder.readObject();
				decoder.close();
			} 
			else
			{
				this.prop = new ServerProperties();
				this.encoder = new XMLEncoder(new FileOutputStream("Serverproperties.xml"));
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
		myFile = new File("Serverproperties.xml");
		try
		{		
				this.prop = new ServerProperties(settings[0],settings[1]);
				this.encoder = new XMLEncoder(new FileOutputStream("Serverproperties.xml"));
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
				this.prop = (ServerProperties) decoder.readObject();
				decoder.close();

				this.encoder = new XMLEncoder(new FileOutputStream("Serverproperties.xml"));
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
