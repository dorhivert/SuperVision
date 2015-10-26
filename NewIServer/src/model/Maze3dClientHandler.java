package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Maze3dClientHandler implements ClientHandler 
{

	@Override
	public void handleClient(InputStream in, OutputStream out) 
	{
		try
		{

			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			ObjectOutputStream oos = new ObjectOutputStream(out);

			String str;
			String[] sp;
			while (!((str = br.readLine()).equals("stop"))){
				sp = str.split(" ");
				switch (sp[0]){
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

@Override
public void stop() {
	// TODO Auto-generated method stub

}

}
