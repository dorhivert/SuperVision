/*
 * 
 */
package algorithms.demo;

import heuristics.MazeEuclideanDistance;
import heuristics.MazeManhattanDistance;
import mazeGenerators.Maze3d;
import mazeGenerators.Maze3dGenerator;
import mazeGenerators.MyMaze3dGenerator;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.Searchable;
import algorithms.search.SearchableMaze;
import algorithms.search.Searcher;

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
		run();
	}

	/**
	 * Run.
	 */
	public static void run()
	{
		Maze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d myMaze = mg.generate(15, 15, 15);
		System.out.print("Start Position : ");
		System.out.println(myMaze.getStartPosition());
		System.out.print("Finish Position : ");
		System.out.println(myMaze.getGoalPosition());
		int bound = myMaze.getMaxZ();
		System.out.print("Printing the maze by ");
		System.out.print((bound+1));
		System.out.println(" Z levels: ");
		for (int i = 0; i <= bound; i++)
		{
			try
			{
				int[][] maze2dZ = myMaze.getCrossSectionByZ(i);
				myMaze.print2dMazeCleanByZ(maze2dZ, i);
			} 
			catch (Exception e) 
			{
				System.out.println("Ftl Erssrorr TOM wizz maze printaion");
			}
		}
		Searchable sm = new SearchableMaze(myMaze);
		Searcher sBFS = new BFS();
		sBFS.search(sm);
		Searcher sManhatan = new AStar(new MazeManhattanDistance());
		sManhatan.search(sm);
		Searcher sEuc = new AStar(new MazeEuclideanDistance());
		sEuc.search(sm);

		System.out.print("BFS algorithm has developed: ");
		System.out.print(sBFS.getNumberOfNodesEvaluated());
		System.out.println(" states.");
		System.out.print("Astar (Manhatten) algorithm has developed: ");
		System.out.print(sManhatan.getNumberOfNodesEvaluated());
		System.out.println(" states.");
		System.out.print("Astar (Euclidean) algorithm has developed: ");
		System.out.print(sEuc.getNumberOfNodesEvaluated());
		System.out.println(" States.");

	}



}

