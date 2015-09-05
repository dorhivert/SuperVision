package io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
public class MyCompressorOutputStream extends OutputStream {

	OutputStream out;
	
	


	public MyCompressorOutputStream(OutputStream out) {
		super();
		this.out = new DataOutputStream(out);
	}

	@Override
	public void write(int arg0) throws IOException {
		((DataOutputStream)out).writeInt(arg0);
	}
	
	@Override
	public void write(byte[] b) throws IOException
	{
		byte previousByte = b[0];
		int counter=1;
		for (int i = 1; i < b.length; i++) {
			if(b.length == 1)
			{
				out.write(b[i]);
				out.write(',');
				out.write(counter);
			}
			
			if(b[i] != previousByte) //saves the current row of bytes into the outputstream
			{
				out.write(previousByte);
				out.write(',');	
				out.write(counter);
				out.write(',');

				counter=1;
				previousByte=b[i];
			}
			else if(b[i] == previousByte)
			{
				counter++;
			}
				
		}
	}
	
	

}
