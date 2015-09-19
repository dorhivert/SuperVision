package model;

import mazeGenerators.Maze3d;

public interface Model {
	
	public String[] getFilesInDirectory(String path);
	
	public void generate3dMaze(String name, int size);
	
	public int[][] getCrossSection(char xyz,int index,String name);
	
	public Maze3d loadMaze(String fileName, String mazeName);
	
	public void saveMaze(String fileName, String mazeName);
	
	public double calculateMazeSize(String name);
	
	public double calculateFileSize(String name);
	
	public void Solve(String name, String algorithm);
	
	

}
