package controller;

import java.util.HashMap;

import algorithms.search.Searcher;
import model.Model;
import view.View;

public interface Controller {
	
	public void setView(View view);
	
	public void setModel(Model model);
	
	public void displayDirectory(String path);
	
	public void generate3dMaze(String name,int size);
	
	public void displayMaze(String name);
	
	public void displayCrossSection(String name, int index, int level);
	
	public void displayMazeSize(String name);
	
	public void displayFileSize(String name);
	
	public void displaySolution(String name,Searcher algorithm);
	
	public HashMap<String,Command> getMap();

	public void getFilesInDirectory(String path);
	

}
