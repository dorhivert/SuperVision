package view;

import mazeGenerators.Maze3d;
import solution.Solution;

public interface View {
	
	public void start();
	
	public void writeToConsole(String s);
	
	public void displayCrossSection(int[][] crossed);
	
	public void displayMazeSize(String name, double size);
	
	public void displayFileSize(String name, double size);
	
	public void displayMaze(Maze3d maze);
	
	public void displaySolution(Solution solution);
	
	public void displayDirectory(String[] filenames);
	

	

}
