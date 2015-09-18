package view;

import mazeGenerators.Maze3d;
import solution.Solution;

public interface View 
{

	public void start();
	
	public void writeToConsole(String userInput);
	
	public void displayCrossSection (int[][] crossed);
	
	public void displayMazeSize(String name, double mazeSize);
	
	public void displayFileSize(String name, double fileSize);
	
	public void displayMaze(Maze3d maze);
	
	public void displaySolution (Solution s);
	
	public void displayDirectory(String[] path);
}
