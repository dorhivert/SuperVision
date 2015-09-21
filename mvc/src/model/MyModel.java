package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.SearchableMaze;
import controller.Controller;
import controller.MyController;
import heuristics.MazeEuclideanDistance;
import heuristics.MazeManhattanDistance;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerators.Maze3d;
import mazeGenerators.Maze3dGenerator;
import mazeGenerators.MyMaze3dGenerator;
import solution.Solution;


public class MyModel extends CommonModel {

	
	
	
	public MyModel(Controller controller) {
		super(controller);
	}

	@Override
	public String[] getFilesInDirectory(String path) {
        File directory = new File(path);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        String[] fileNames = new String[fList.length];
        for (int i = 0; i < fList.length; i++) {
			fileNames[i] =  fList[i].getName();
		}
		return fileNames;
	}

	@Override
	public void generate3dMaze(String name, int size) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				Maze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d maze = mg.generate(size, size, size);
				((MyController)controller).getMazeCollection().put(name, maze);
				((MyController)controller).notifyView("Maze named:" + name + "completed");
			}
			
		}).start();

		
	}

	@Override
	public int[][] getCrossSection(char xyz, int index, String name) {
		if(((MyController)controller).getMazeCollection().containsKey(name))
		{
			Maze3d tempMaze = ((MyController)controller).getMazeCollection().get(name);
			if((xyz=='x')||(xyz=='X'))
			{
				return tempMaze.getCrossSectionByX(index);
			}
			if((xyz=='y')||(xyz=='Y'))
			{
				return tempMaze.getCrossSectionByY(index);
			}
			if((xyz=='z')||(xyz=='Z'))
			{
				return tempMaze.getCrossSectionByZ(index);
			}
			else
			{
				((MyController)controller).notifyView("Bad letter, please enter x y or z, null returned");
				return null;
			}

		}
		else
		{
			((MyController)controller).notifyView("Illegal maze name, please try again,null returned");
			return null;
		}
	}

	@Override
	public void saveMaze(String fileName, String mazeName) {
		 
		  Maze3d maze = ((MyController)controller).getMazeCollection().get(mazeName);
		  try{
		  OutputStream out=new MyCompressorOutputStream( new FileOutputStream(fileName));
		  out.write(maze.toByteArray());
		  out.flush();
		  out.close();
		  }
		  catch(IOException e)
		  {
			  e.printStackTrace();
		  }

	}
	
	@Override
	public Maze3d loadMaze(String fileName, String mazeName)
	{
		try{
		  MyDecompressorInputStream in=new MyDecompressorInputStream( new FileInputStream(fileName));
		  byte[] b= IOUtils.toByteArray(in);

		  in.read(b);//check if needed
		  in.close();
		  return new Maze3d(b);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public double calculateMazeSize(String name) {
		if(((MyController)controller).getMazeCollection().containsKey(name))
		{
			return ((MyController)controller).getMazeCollection().get(name).toByteArray().length;	
		}
		else
		{
			((MyController)controller).notifyView("Illegal maze name, please try again");
			return -5;
		}
		
	}

	@Override
	public double calculateFileSize(String name) {
		File tmpFile = new File(name);
		if(tmpFile.length()==0L) //if the file doesn't exist
		{
			saveMaze("TempFileName", name);
			tmpFile = new File("TempFileName");
		}
		return tmpFile.length();
		
	}

	@Override
	public void Solve(String name, String algorithm)
	{
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				
				if(((MyController)controller).getMazeCollection().containsKey(name))
				{
					Maze3d myMaze =((MyController)controller).getMazeCollection().get(name);
					SearchableMaze sMaze = new SearchableMaze(myMaze);
					if(algorithm.equalsIgnoreCase("astarman"))
					{
						AStar astar = new AStar(new MazeManhattanDistance());
						Solution tmpSolution = astar.search(sMaze);
						astar.search(sMaze);
						((MyController)controller).getSolutionCollection().put(name, tmpSolution);
						((MyController)controller).notifyView("Solution for maze:"+name+"added.");
					}
					if(algorithm.equalsIgnoreCase("astarair"))
					{
						AStar astar = new AStar(new MazeEuclideanDistance());
						Solution tmpSolution = astar.search(sMaze);
						astar.search(sMaze);
						((MyController)controller).getSolutionCollection().put(name, tmpSolution);
						((MyController)controller).notifyView("Solution for maze:"+name+"added.");
					}
					if(algorithm.equalsIgnoreCase("BFS"))
					{
						BFS bfs = new BFS();
						Solution tmpSolution = bfs.search(sMaze);
						((MyController)controller).getSolutionCollection().put(name, tmpSolution);
						((MyController)controller).notifyView("Solution for maze:"+name+"added.");
					}
					else
					{
						((MyController)controller).notifyView("Illegal algorithm, please try again.");
					}
				}

			}

		}).start();



	}


		
}
