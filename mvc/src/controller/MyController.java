package controller;

import java.util.HashMap;

import algorithms.search.Searcher;
import mazeGenerators.Maze3d;
import solution.Solution;

public class MyController extends CommonController {

	private HashMap<String,Maze3d> mazeCollection = new HashMap<String,Maze3d>();
	private HashMap<String,Solution> solutionCollection = new HashMap<String,Solution>();
	
	
	public HashMap<String, Maze3d> getMazeCollection() {
		return mazeCollection;
	}

	public HashMap<String, Solution> getSolutionCollection() {
		return solutionCollection;
	}

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
	public void getCrossSection(String name, int index, char xyz) {
		this.view.displayCrossSection(this.model.getCrossSection(xyz, index, name));
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

	public void notifyView(String message) {
		view.writeToConsole(message);
		
	}



}
