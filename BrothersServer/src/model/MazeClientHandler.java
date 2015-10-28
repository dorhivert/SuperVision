package model;

import heuristics.Heuristic;
import heuristics.MazeEuclideanDistance;
import heuristics.MazeManhattanDistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import mazeGenerators.Maze3d;
import solution.Solution;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.Searchable;
import algorithms.search.SearchableMaze;
import algorithms.search.Searcher;


/**
 * The Class MyClientHandler.
 */
public class MazeClientHandler implements ClientHandler
{

	/** The maze solution. */
	private HashMap<Maze3d, Solution> mazeSolution;

	/** The threadpool. */
	private ExecutorService threadpool;

	/** The message to client. */
	private ObjectOutputStream messageToClient;

	/** The message from client. */
	private ObjectInputStream messageFromClient;


	/**
	 * Instantiates a new my client handler.
	 */
	public MazeClientHandler() 
	{
		try 
		{
			loadFromZip();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(this.mazeSolution==null)
		{
			this.mazeSolution = new HashMap<Maze3d, Solution>();
		}
		this.threadpool = Executors.newFixedThreadPool(3);
	}

	/* (non-Javadoc)
	 * @see model.ClientHandler#handleClient(java.io.InputStream, java.io.OutputStream)
	 */
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient)
	{
		try 
		{
			messageFromClient = new ObjectInputStream(inFromClient);
			@SuppressWarnings("unchecked")
			ArrayList<Object> problem = (ArrayList<Object>)messageFromClient.readObject();
			messageToClient = new ObjectOutputStream(outToClient);
			if (problem.get(0).equals("solve"))
			{
				handleSolution(problem);
			}	
			else
			{
				handleSolution(problem);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}

	/**
	 * Handle solution.
	 *
	 * @param list the list
	 */
	private void handleSolution(ArrayList<Object> list) 
	{
		String algorithm = (String) list.get(1);
		Maze3d maze = (Maze3d) list.get(2);
		Future<Solution> sol = threadpool.submit(new Callable<Solution>() 
				{

			public Solution call() 
			{
				Searcher algo = null;
				Searchable _smaze = new SearchableMaze(maze);
				if (algorithm.equalsIgnoreCase("bfs"))
				{
					algo = new BFS();
				} else if (algorithm.equalsIgnoreCase("astarman"))
				{
					Heuristic ManhattanDistance = new MazeManhattanDistance();
					algo = new AStar(ManhattanDistance);
				} else if (algorithm.equalsIgnoreCase("astarair"))
				{
					Heuristic AirDistance = new MazeEuclideanDistance();
					algo = new AStar(AirDistance);
				}

				Solution solution = algo.search(_smaze);
				return solution;
			}
				});

		Solution solution;
		try
		{
			solution = sol.get();
			mazeSolution.put(maze, solution);

			messageToClient.writeObject(solution);
			messageToClient.flush();


			messageFromClient.close();
			messageToClient.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	/**
	 * Load from zip.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private void loadFromZip() throws Exception 
	{
		ObjectInputStream in;
		File file = new File("ServerSolutionCache.gzip");

		if(!file.createNewFile())
		{
			try 
			{
				in = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
				this.mazeSolution = (HashMap<Maze3d, Solution>) in.readObject();
				in.close();
			} catch (IOException e) {}
		}
	}


	/**
	 * Save to zip.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveToZip() throws IOException
	{
		FileOutputStream fos = null;
		GZIPOutputStream gzip = null;
		ObjectOutputStream out = null;

		try
		{
			fos = new FileOutputStream("ServerSolutionCache.gzip");
			gzip = new GZIPOutputStream(fos);
			out = new ObjectOutputStream(gzip);
			out.writeObject(mazeSolution);
			out.flush();
			out.close();
		} 

		catch (FileNotFoundException e) 
		{
			System.out.println("File doesn't found");
		} 
		catch (IOException e)
		{
			System.out.println("IO exception");
		} 
		finally 
		{
			if(gzip != null)
			{
				gzip.close();
			}
		}
		try 
		{
			threadpool.shutdown();
			threadpool.awaitTermination(59, TimeUnit.SECONDS);
			threadpool.shutdownNow();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
