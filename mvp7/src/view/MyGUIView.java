package view;

import java.io.Closeable;
import java.io.IOException;

import mazeGenerators.Maze3d;
import solution.Solution;

public class MyGUIView extends BasicWindow implements Closeable{

	public MyGUIView(String title, int width, int height) {
		super(title, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeToConsole(String userInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayCrossSection(int[][] crossSection) {
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
	public void displayCommandMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	void InitWidgets() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		if (display!=null&&(!display.isDisposed()))
		{
			display.dispose();
		}

		if(shell!=null&&(!shell.isDisposed()))
		{
			shell.dispose();
		}

		
	}

}
