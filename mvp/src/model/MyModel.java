package model;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import mazeGenerators.Maze3d;
import solution.Solution;

public class MyModel extends ObservableModel {

	ExecutorService threadPool;
	
	/** The maze collection. */
	private HashMap<String, Maze3d> mazeCollection = new HashMap<String, Maze3d>();

	/** The solution collection. */
	private HashMap<Maze3d, Solution> solutionCollection = new HashMap<Maze3d, Solution>();

	
	
	@Override
	public String[] getFilesInDirectory(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generate3dMaze(String name, int size) {
		// TODO Auto-generated method stub

	}

	@Override
	public int[][] getCrossSection(char xyz, int index, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMaze(String mazeName, String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public Maze3d loadMaze(String mazeName, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calcMazeSize(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcFileSize(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void solve(String name, String algo) {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Maze3d> getMazeCollection() {
		return mazeCollection;
		
	}

	@Override
	public HashMap<Maze3d, Solution> getSolutionCollection() {
		return solutionCollection;
		
	}

}
