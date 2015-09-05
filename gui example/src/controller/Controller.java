package controller;

import model.myModel;
import view.MyAsciiArtMaker;

public class Controller 
{
	
	MyAsciiArtMaker view;
	myModel model;
	
	public Controller(MyAsciiArtMaker _view, myModel _model)
	{
		view = _view;
		model = _model;
	}
	
	public void display(String fileName)
	{
		this.view.display(fileName);
	}
	public void convert(String fileName)
	{
		this.model.convert(fileName);
	}

}
