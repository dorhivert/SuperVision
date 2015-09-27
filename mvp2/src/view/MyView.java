package view;

import java.io.IOException;
import java.text.DecimalFormat;

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
					while (!(line = cli.in.readLine()).equals("exit")) 
					{
						String [] splittedLine = line.split(" ");
						if (cli.commandMap.containsKey(splittedLine[0])) 
						{
							System.out.println("--- Command '" + line + "' is OK --- ");
							cli.out.flush();
//							cli.commandMap.get(splittedLine[0]).doCommand(splittedLine);
							setChanged();
							notifyObservers(splittedLine);

						}
						else
						{
							System.out.println("ERROR: Wrong command: "+line);
							cli.out.flush();
						}
					}
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

	@Override
	public void writeToConsole(String userInput)
	{
		System.out.println(userInput);
	}

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

	@Override
	public void displayMaze(Maze3d maze) 
	{
		maze.displayMaze(maze);

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
		System.out.println("  solve <name> <algorithm (BFS / ASTARAIR / ASTARMAN>");
		System.out.println("  display solution <name>");
		System.out.println("  menu");
		System.out.println("  exit");
	}
}
