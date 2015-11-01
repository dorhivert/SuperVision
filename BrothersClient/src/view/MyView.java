package view;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Observable;

import mazeGenerators.Maze3d;
import solution.Solution;


// TODO: Auto-generated Javadoc
/**
 * The Class MyView.
 */
public class MyView extends Observable implements View
{
	
	/** The cli. */
	private CLI cli;

	/**
	 * Sets the cli.
	 *
	 * @param cli the new cli
	 */
	public void setCli(CLI cli)
	{
		this.cli = cli;
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run() 
			{
				try 
				{
					String line;	
					System.out.println("You can start writing commands");
					System.out.println("Type menu to see syntax");
					do
					{
						line = cli.in.readLine();
						String [] splittedLine = line.split(" ");
						if (cli.commandMap.containsKey(splittedLine[0])) 
						{
							System.out.println("--- Command '" + line + "' is OK --- ");
							cli.out.flush();
							setChanged();
							notifyObservers(splittedLine);
						}
						else
						{
							System.out.println("ERROR: Wrong command: "+line);
							cli.out.flush();
						}
					}
					while (!(line.equals("exit")));
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				System.out.println("Good-Bye!!");
				cli.out.flush();
				cli.out.close();
			}
		}).start();
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
	public void displayCrossSection(int[][] crossSection) 
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
	 * @see view.View#displayMaze(mazeGenerators.Maze3d)
	 */
	@Override
	public void displayMaze(Maze3d maze) 
	{
		maze.displayMaze(maze);
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
	 * @see view.View#displayCommandMenu()
	 */
	@Override
	public void displayCommandMenu() 
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
		System.out.println("  solve <name> <algorithm (BFS / ASTARAIR / ASTARMAN)>");
		System.out.println("  display solution <name>");
		System.out.println("  menu");
		System.out.println("  exit");
	}

	/* (non-Javadoc)
	 * @see view.View#displayMazeGUI(mazeGenerators.Maze3d)
	 */
	@Override
	public void displayMazeGUI(Maze3d maze3d) {}

	/* (non-Javadoc)
	 * @see view.View#setMazeName(java.lang.String)
	 */
	@Override
	public void setMazeName(String _name) {}

	/* (non-Javadoc)
	 * @see view.View#setSolution(solution.Solution)
	 */
	@Override
	public void setSolution(Solution solution) {}

	/* (non-Javadoc)
	 * @see view.View#enableSolve()
	 */
	@Override
	public void enableSolve() {}

}
