
package io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

	InputStream in;
	
	
	
	
	public MyDecompressorInputStream(InputStream in) {
		super();
		this.in = new DataInputStream(in);
	}

	@Override
	public int read() throws IOException 
	{
		return ((DataInputStream)in).readInt();
	}
	
	@Override
	public int read(byte[] b) throws IOException
	{
		int length = in.available();
		int value, number,total_index = 0;
		while(in.available()<4)
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
