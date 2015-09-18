package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import solution.Solution;
import mazeGenerators.Maze3d;
import controller.Controller;

public class MyView extends CommonView 
{

	CLI cli;
	
	public MyView(Controller controller)
	{
		super(controller);
		this.cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), this.getController().getMap());
	}

	@Override
	public void start()
	{
		this.cli.start();
	}

	@Override
	public void writeToConsole(String userInput)
	{
		System.out.println(userInput);
	}
	
	@Override
	public void displayCrossSection (int[][] crossSection)
	{
		System.out.println("Your CrossSection:");
		System.out.println();
		for (int i = 0; i < crossSection.length; i++)
		{
			System.out.print("    ");
			for (int j = 0; j < crossSection.length; j++)
			{

				System.out.print(crossSection[j][crossSection.length-i-1]);
				if (j+1 < crossSection.length)
				{
					System.out.print(",");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@Override
	public void displayMazeSize(String name, double mazeSize)
	{
		System.out.print("For Maze:");
		System.out.print(name);
		System.out.print(", the size is: ");
		System.out.println(mazeSize);
	}
	
	@Override
	public void displayFileSize(String name, double fileSize)
	{
		System.out.print("For Maze:");
		System.out.print(name);
		System.out.print(", the file size is: ");
		System.out.println(fileSize);
	}
	

	@Override
	public void displaySolution(Solution s)
	{
		s.print();
	}

	@Override
	public void displayDirectory(String[] path) 
	{
		System.out.println("the files in this directory are:");
		for (int i = 0; i < path.length; i++) 
		{
			System.out.println(path[i]);
		}	
	}

	@Override
	public void displayMaze(Maze3d maze)
	{
		int bound = maze.getMaxZ();
		System.out.print("Printing the maze by ");
		System.out.print((bound+1));
		System.out.println(" Z levels: ");
		for (int i = 0; i <= bound; i++)
		{
			try
			{
				int[][] maze2dZ = maze.getCrossSectionByZ(i);
				maze.print2dMazeCleanByZ(maze2dZ, i);
			} 
			catch (Exception e) 
			{
				System.out.println("Fatal Error with maze printaion");
			}
		}	
	}
}
