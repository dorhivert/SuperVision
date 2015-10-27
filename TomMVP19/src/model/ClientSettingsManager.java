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

import presenter.ClientSettings;
import presenter.Properties;

public class ClientSettingsManager
{
	private XMLDecoder decoder;
	private XMLEncoder encoder;
	private ClientSettings cs;
	private File myFile;

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


	public ClientSettingsManager(){};
	
	public ClientSettings loadCS()
	{
		myFile = new File("ClientSettings.xml");
		try
		{
			if(!myFile.createNewFile())
			{
				this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(myFile)));
				this.cs = (ClientSettings) decoder.readObject();
				decoder.close();
			} 
			else
			{
				this.cs = new ClientSettings();
				this.encoder = new XMLEncoder(new FileOutputStream("ClientSettings.xml"));
				encoder.writeObject(cs);
				encoder.flush();
				encoder.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return cs;
	}
	
	public void setNewClientSettings(String[] settings)
	{
		myFile = new File("ClientSettings.xml");
		try
		{		
				this.cs = new ClientSettings(settings[0],settings[1],settings[2]);
				this.encoder = new XMLEncoder(new FileOutputStream("ClientSettings.xml"));
				encoder.writeObject(cs);
				encoder.flush();
				encoder.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void loadNewCSFromFile(String[] args)
	{
		File mySecondFile = new File(args[1]);
		try
		{
				this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(mySecondFile)));
				this.cs = (ClientSettings) decoder.readObject();
				decoder.close();

				this.encoder = new XMLEncoder(new FileOutputStream("ClientSettings.xml"));
				encoder.writeObject(cs);
				encoder.flush();
				encoder.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
