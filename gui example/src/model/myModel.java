package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import controller.Controller;

public class myModel
{
	public Controller controller;
	
	public Controller getController() 
	{
		return controller;
	}

	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	
	public void convert (String fileName)
	{
		ASCIIMaker am = new ASCIIMaker();
		try 
		{
			FileInputStream in = new FileInputStream(fileName);
			FileOutputStream out = new FileOutputStream(fileName +".asc");
			am.convertToAscii(in, out);
			in.close();
			out.close();
			controller.display(fileName +".asc");
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
