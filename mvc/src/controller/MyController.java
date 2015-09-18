package controller;

import java.io.File;
import java.util.HashMap;

import algorithms.search.Searcher;

public class MyController extends CommonController {

	@Override
	public void initCommands(HashMap<String, Command> map) {
		
		//dir <path>
		map.put("dir", new Command() {

			@Override
			public void doCommand() {
				
		        }
		});
	}

	@Override
	public void getFilesInDirectory(String path) {
		
	}

	@Override
	public void generate3dMaze(String name, int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSection(String name, int index, int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMazeSize(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayFileSize(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(String name, Searcher algorithm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayDirectory(String path) {
		// TODO Auto-generated method stub
		
	}

}
