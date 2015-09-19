package model;

import mazeGenerators.Maze3d;

public interface Model 
{

	public String[] getFilesInDirectory(String path);
	
	public void generate3dMaze(String name, int size);
	
	public int[][] getCrossSection (char xyz, int index, String name);
	
	public void saveMaze (String mazeName, String fileName);
	
	public Maze3d loadMaze (String mazeName, String fileName);
	
	public double calcMazeSize(String name);
	
	public double calcFileSize(String name);
	
	public void solve(String name, String algo);
}
