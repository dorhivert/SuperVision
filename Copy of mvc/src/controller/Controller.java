package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public interface Controller {
	
	public void setView(View view);
	
	public void setModel(Model model);
		
	public void generate3dMaze(String name,int size);
	
	public void displayMaze(String name);
	
	public void getCrossSection(String name, int index, char xyz);
	
	public void displayMazeSize(String name);
	
	public void displayFileSize(String name);
	
	public void displaySolution(String name);
	
	public HashMap<String,Command> getMap();

	public void getFilesInDirectory(String path);
	

}
