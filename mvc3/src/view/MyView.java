package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import mazeGenerators.Maze3d;
import solution.Solution;
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
		DecimalFormat df = new DecimalFormat("#.##");
		String newValue = new String(df.format(mazeSize));
		System.out.print("For Maze: ");
		System.out.print("'"+name+"'");
		System.out.print(" - the MAZE size is: ");
		System.out.println(newValue+" Bytes.");
	}
	
	/* (non-Javadoc)
	 * @see view.View#displayFileSize(java.lang.String, double)
	 */
	@Override
	public void displayFileSize(String name, double fileSize)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		String newValue = new String(df.format(fileSize));
		System.out.print("For Maze: ");
		System.out.print("'"+name+"'");
		System.out.print(" - the FILE size is: ");
		System.out.println(newValue+" Bytes.");
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
		maze.displayMaze(maze);
	}

	/* (non-Javadoc)
	 * @see view.View#displayCommandManu()
	 */
	@Override
	public void displayCommandManu() 
	{
		System.out.println("=== Commands available: ===");
		System.out.println("Please make sure to write commands correctly");
		System.out.println("  dir <path>");
		System.out.println("  generate 3d maze <name> <size>");
		System.out.println("  display <name>");
		System.out.println("  display cross section by <x/y/z> <index> for <name>");
		System.out.println("  save maze <name> <file name>");
		System.out.println("  load maze <file name> <file>");
		System.out.println("  maze size <name>");
		System.out.println("  file size <name>");
		System.out.println("  solve <name> <algorithm (BFS / ASTARAIR / ASTARMAN>");
		System.out.println("  display solution <name>");
		System.out.println("  menu");
		System.out.println("  exit");
	}
}
