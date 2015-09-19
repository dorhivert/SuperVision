package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import solution.Solution;
import mazeGenerators.Maze3d;
import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class MyView.
 */
public class MyView extends CommonView 
{

	/** The cli. */
	CLI cli;
	
	/**
	 * Instantiates a new my view.
	 *
	 * @param controller the controller
	 */
	public MyView(Controller controller)
	{
		super(controller);
		this.cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), this.getController().getMap());
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start()
	{
		this.cli.start();
	}

	/* (non-Javadoc)
	 * @see view.View#writeToConsole(java.lang.String)
	 */
	@Override
	public void writeToConsole(String userInput)
	{
		System.out.println(userInput);
	}
	
	/* (non-Javadoc)
	 * @see view.View#displayCrossSection(int[][])
	 */
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
	
	/* (non-Javadoc)
	 * @see view.View#displayMazeSize(java.lang.String, double)
	 */
	@Override
	public void displayMazeSize(String name, double mazeSize)
	{
		System.out.print("For Maze:");
		System.out.print(name);
		System.out.print(", the size is: ");
		System.out.println(mazeSize);
	}
	
	/* (non-Javadoc)
	 * @see view.View#displayFileSize(java.lang.String, double)
	 */
	@Override
	public void displayFileSize(String name, double fileSize)
	{
		System.out.print("For Maze:");
		System.out.print(name);
		System.out.print(", the file size is: ");
		System.out.println(fileSize);
	}
	

	/* (non-Javadoc)
	 * @see view.View#displaySolution(solution.Solution)
	 */
	@Override
	public void displaySolution(Solution s)
	{
		s.print();
	}

	/* (non-Javadoc)
	 * @see view.View#displayDirectory(java.lang.String[])
	 */
	@Override
	public void displayDirectory(String[] path) 
	{
		System.out.println("the files in this directory are:");
		for (int i = 0; i < path.length; i++) 
		{
			System.out.println(path[i]);
		}	
	}

	/* (non-Javadoc)
	 * @see view.View#displayMaze(mazeGenerators.Maze3d)
	 */
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
