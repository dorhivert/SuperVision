package io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream
{

	private OutputStream out;
	

	public MyCompressorOutputStream(OutputStream out)
	{
		super();
		this.out = new DataOutputStream(out);
	}
	

	@Override
	public void write(int b) throws IOException 
	{
		((DataOutputStream) out).writeInt(b);
	}
	
	@Override
	public void write(byte [] b) throws IOException
	{
		int counter = 1;
		byte previousByte = b[0];
		for (int i = 1; i < b.length; i++) 
		{
			if (b.length == 1)
			{
				out.write(previousByte);
				out.write(counter);
				return;
			}
			if (b[i] != previousByte) 
			{
				out.write(previousByte);
				out.write(counter);
				out.write(',');
				counter = 1;
				previousByte = b[i];
			}
			else
			{
				if (b[i] == previousByte) 
				{
					counter++;
				}
			}
		}
	}
}
