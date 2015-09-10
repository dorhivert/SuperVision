package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MyClientHandler implements ClientHandler
{
	@Override
	public void handleClient(InputStream in, OutputStream out)
	{
		//		someClient.getInputStream()
		try
		{
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(in));
			PrintWriter outToClient = new PrintWriter(out);
			String line;
			while (!(line = inFromClient.readLine()).equals("exit")) {
				StringBuilder sb = new StringBuilder(line);
				outToClient.println(sb.reverse().toString());
				outToClient.flush();
			}
			outToClient.println("bye");
			outToClient.flush();
			outToClient.close();
			inFromClient.close();

		}
		catch(IOException e)
		{

		}
	}
}

