package view;

import mazeGenerators.Maze3d;
import solution.Solution;

public class MyView extends ObservableView 
{
	CLI cli;

	public void setCli(CLI cli)
	{
		this.cli = cli;
	}
	@Override
	public void start()
	{
		this.cli.start();

	}

	@Override
	public void writeToConsole(String userInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayCrossSection(int[][] crossed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMazeSize(String name, double mazeSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayFileSize(String name, double fileSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySolution(Solution s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayDirectory(String[] path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayCommandManu() {
		// TODO Auto-generated method stub

	}

}
