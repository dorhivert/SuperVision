/*
 * 
 */
package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerators.Maze3d;
import mazeGenerators.Maze3dGenerator;
import mazeGenerators.MyMaze3dGenerator;

/**
 * The Class Demo.
 */
public class Demo 
{

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		try{
			run();	
		}catch(IOException e)
		{
			System.out.println("file was not found");
		}
		
	}

	/**
	 * Run.
	 * @throws IOException 
	 */
	public static void run() throws IOException
	{
//		Maze3dGenerator mg = new MyMaze3dGenerator();
//		Maze3d myMaze = mg.generate(15, 15, 15);
//		System.out.print("Start Position : ");
//		System.out.println(myMaze.getStartPosition());
//		System.out.print("Finish Position : ");
//		System.out.println(myMaze.getGoalPosition());
//		int bound = myMaze.getMaxZ();
//		System.out.print("Printing the maze by ");
//		System.out.print((bound+1));
//		System.out.println(" Z levels: ");
//		for (int i = 0; i <= bound; i++)
//		{
//			try
//			{
//				int[][] maze2dZ = myMaze.getCrossSectionByZ(i);
//				myMaze.print2dMazeCleanByZ(maze2dZ, i);
//			} 
//			catch (Exception e) 
//			{
//				System.out.println("Ftl Erssrorr TOM wizz maze printaion");
//			}
//		}
//		Searchable sm = new SearchableMaze(myMaze);
//		Searcher sBFS = new BFS();
//		sBFS.search(sm);
//		Searcher sManhatan = new AStar(new MazeManhattanDistance());
//		sManhatan.search(sm);
//		Searcher sEuc = new AStar(new MazeEuclideanDistance());
//		sEuc.search(sm);
//
//		System.out.print("BFS algorithm has developed: ");
//		System.out.print(sBFS.getNumberOfNodesEvaluated());
//		System.out.println(" states.");
//		System.out.print("Astar (Manhatten) algorithm has developed: ");
//		System.out.print(sManhatan.getNumberOfNodesEvaluated());
//		System.out.println(" states.");
//		System.out.print("Astar (Euclidean) algorithm has developed: ");
//		System.out.print(sEuc.getNumberOfNodesEvaluated());
//		System.out.println(" States.");
		  Maze3dGenerator mg = new MyMaze3dGenerator();
		  Maze3d maze = mg.generate(15, 15, 15) ; 
		  // save it to a file
		  OutputStream out=new MyCompressorOutputStream( new FileOutputStream("1.maz"));
		  out.write(maze.toByteArray());
		  out.flush();
		  out.close();
		  InputStream in=new MyDecompressorInputStream( new FileInputStream("1.maz"));
		  byte b[]=new byte[maze.toByteArray().length];
		  in.read(b);
		  in.close();  
		  Maze3d loaded=new Maze3d(b);
		  System.out.println(loaded.equals(maze));
//		  boolean flag = true;
//		  for (int x = 0; x < maze.getMaze().length; x++) 
//		  {
//			for (int y = 0; y < maze.getMaze()[x].length; y++)
//			{
//				for (int z = 0; z < maze.getMaze()[x][y].length; z++)
//				{
//					if (maze.getMaze()[x][y][z]!=loaded.getMaze()[x][y][z]) 
//					{
//						flag = false;
//					}
//				}
//			}
//		  }
//		  System.out.println(flag);

	}



}

