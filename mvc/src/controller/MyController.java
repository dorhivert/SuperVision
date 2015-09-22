package controller;

import java.util.HashMap;

import mazeGenerators.Maze3d;
import solution.Solution;

public class MyController extends CommonController {

	private HashMap<String,Maze3d> mazeCollection = new HashMap<String,Maze3d>();
	private HashMap<String,Solution> solutionCollection = new HashMap<String,Solution>();
	
	public MyController()
	{
		super();
		this.map = new HashMap<String, Command>();
		initCommands(map);
	}
	
	public HashMap<String, Maze3d> getMazeCollection() {
		return mazeCollection;
	}

	public HashMap<String, Solution> getSolutionCollection() {
		return solutionCollection;
	}

	@Override
	public void initCommands(HashMap<String, Command> map) {
		
		//dir <path>
		map.put("dir", new Command() {

			@Override
			public void doCommand(String[] args) {
				
				getFilesInDirectory(args[1]);
				
		        }
		});
		map.put("generate", new Command() {

			@Override
			public void doCommand(String[] args) {
				int size = Integer.parseInt(args[4]);
				generate3dMaze(args[3],size);
			}
			
		});
		map.put("display", new Command() {

			@Override
			public void doCommand(String[] args) {
				switch (args[1]) {
				case "cross":
				{
					char xyz = args[3].charAt(0);
					int index = Integer.parseInt(args[4]);
					getCrossSection(args[5], index, xyz);
				}
					break;
				case "solution":
				{
					if(solutionCollection.get(args[2])!=null)
					{
						view.displaySolution(solutionCollection.get(args[2]));
					}
					else
					{
						notifyView("No solution exists for this maze, please create one.");
					}
				}

				default:
					if(mazeCollection.get(args[1])!=null)
					{
						view.displayMaze(mazeCollection.get(args[1]));
					}
					else
					{
						notifyView("No maze exists by this name, please create one.");
					}
					break;
				}
				

			}
			
		});
		map.put("save", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				model.saveMaze(args[3], args[2]);
			}
		});
		map.put("load", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				mazeCollection.put(args[3], model.loadMaze(args[2], args[3]));
			}
		});
		map.put("maze", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				
				displayMazeSize(args[2]);
				
			}
		});
		
		map.put("file", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				displayFileSize(args[2]);
				
			}
		});
		map.put("solve", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				model.Solve(args[1], args[2]);
			}
		});
	}

	@Override
	public void getFilesInDirectory(String path) {
		this.view.displayDirectory(this.model.getFilesInDirectory(path));
		
	}

	@Override
	public void generate3dMaze(String name, int size) {
		this.model.generate3dMaze(name, size);
		
	}

	@Override
	public void displayMaze(String name) {
		Maze3d maze = this.mazeCollection.get(name);
		if(maze!=null)
			this.view.displayMaze(maze);
		else
			notifyView("no maze by that name here");
	}

	@Override
	public void getCrossSection(String name, int index, char xyz) {
		this.view.displayCrossSection(this.model.getCrossSection(xyz, index, name));
	}

	@Override
	public void displayMazeSize(String name) {
		this.view.displayMazeSize(name, this.model.calculateMazeSize(name));
		
	}

	@Override
	public void displayFileSize(String name) {
		this.view.displayFileSize(name, this.model.calculateFileSize(name));
		
	}

	@Override
	public void displaySolution(String name) {
		if(solutionCollection.containsKey(name))
		{
			view.displaySolution(solutionCollection.get(name));
		}
		else
		{
			view.writeToConsole("No solution exists for your request!, please try again");
		}
	}


	public void notifyView(String message) {
		view.writeToConsole(message);
		
	}



}
