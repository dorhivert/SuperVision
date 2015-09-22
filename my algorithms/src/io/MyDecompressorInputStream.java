
package io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class MyDecompressorInputStream.
 */
public class MyDecompressorInputStream extends InputStream 
{
	
	/** The in. */
	DataInputStream in;
	
	/** The length. */
	private int length;
	
	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength()
	{
		return this.length;
	}
	
	
	/**
	 * Instantiates a new my decompressor input stream.
	 *
	 * @param in the in
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MyDecompressorInputStream(InputStream in) throws IOException
	{
		super();
		this.in = new DataInputStream(in);
		length = read();
	}

	/* (non-Javadoc)
	 * @see java.io.InputStream#read()
	 */
	@Override
	public int read() throws IOException 
	{
		return ((DataInputStream)in).readInt();
	}
	
	/* (non-Javadoc)
	 * @see java.io.InputStream#read(byte[])
	 */
	@Override
	public int read(byte[] b) throws IOException
	{
		int value, number,total_index = 0;
		while(in.available() > 0)
		{
			value = in.read(); // the current value to add
			number = in.read(); // the number of values to add
			for (int i = total_index; i < (total_index+number); i++) 
			{
				b[i] = (byte) value;
			}
			total_index += number;
		}
		return length;
	}

}
