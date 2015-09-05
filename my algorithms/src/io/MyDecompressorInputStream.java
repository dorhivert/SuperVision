package io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream
{

	private InputStream in;


	public MyDecompressorInputStream(InputStream in)
	{
		super();
		this.in = new DataInputStream(in);
	}


	@Override
	public int read() throws IOException 
	{
		return ((DataInputStream) in).readInt();
	}
	
	public int read(byte [] b) throws IOException 
	{
		return 0;	
	}
}
