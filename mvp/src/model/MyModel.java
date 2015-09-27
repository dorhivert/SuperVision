package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.SearchableMaze;
import heuristics.MazeEuclideanDistance;
import heuristics.MazeManhattanDistance;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerators.Maze3d;
import mazeGenerators.Maze3dGenerator;
import mazeGenerators.MyMaze3dGenerator;
import solution.Solution;

public class MyModel extends ObservableModel {

	ExecutorService threadPool;
	
	/** The maze collection. */
	private HashMap<String, Maze3d> mazeCollection = new HashMap<String, Maze3d>();

	/** The solution collection. */
	private HashMap<Maze3d, Solution> solutionCollection = new HashMap<Maze3d, Solution>();
	
	private HashMap<String,Object> commandData = new HashMap<String,Object>();

	
	
	public MyModel()
	{
		super();
		threadPool = Executors.newCachedThreadPool();
	}
	
	@Override
	public HashMap<String,Object> getCommandData()
	{
		return commandData;
	}
	
	public void changeAndNotify(String command,Object obj)
	{
		if(obj != null)
			commandData.put(command, obj);
		setChanged();
		notifyObservers(command);
	}
	
	@Override
	public void getFilesInDirectory(String path) {
		File f = new File(path);
		File[] fList = f.listFiles();
		f.list();
		if (f.length()==0)
		{
			changeAndNotify("notify", "No files in directory");
			return;
		}
		if (!f.isDirectory())
		{
			changeAndNotify("notify", "This is not a directory!");
			return;
		}
		String[] fileNames = new String[fList.length];
		
		for (int i = 0; i < fList.length; i++)
		{
			fileNames[i] = fList[i].getName();	
		}
		changeAndNotify("dir", fileNames);
	}

	@Override
	public void generate3dMaze(String name, int size) {
		Future<Maze3d> f = threadPool.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				Maze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d myMaze = mg.generate(size, size, size);
				return myMaze;
			}
		});
		try {
			Maze3d myMaze = f.get();
			getMazeCollection().put(name, myMaze);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		changeAndNotify("generated", name);
		
	}

	@Override
	public void getCrossSection(char xyz, int index, String name) {
		if((getMazeCollection().containsKey(name)))
		{
			Maze3d myMaze = new Maze3d((getMazeCollection().get(name)));
			if (xyz == 'x' || xyz == 'X') 
			{
				changeAndNotify("crossed", myMaze.getCrossSectionByX(index));
				return;
			}
			if (xyz == 'y' || xyz == 'Y') 
			{
				changeAndNotify("crossed", myMaze.getCrossSectionByY(index));
				return;
			}
			if (xyz == 'z' || xyz == 'Z') 
			{
				changeAndNotify("crossed", myMaze.getCrossSectionByZ(index));
				return;
			}
			else 
			{
				changeAndNotify("notify", "bad X/Y/Z cord");
			}
		}
		else
		{
			changeAndNotify("notify", "Bad Maze Name (m.getcross)" );
		}
	}

	@Override
	public void saveMaze(String mazeName, String fileName) {
		Maze3d myMaze = new Maze3d(getMazeCollection().get(mazeName));
		try 
		{
			OutputStream out=new MyCompressorOutputStream( new FileOutputStream(fileName));
			out.write(myMaze.toByteArray());
			out.flush();
			out.close();
			changeAndNotify("saved", mazeName);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	

	}

	@Override
	public void loadMaze(String mazeName, String fileName) 
	{
		try
		{
			InputStream in=new MyDecompressorInputStream( new FileInputStream(fileName));
			byte[] b = new byte[((MyDecompressorInputStream) in).getLength()];
			in.read(b);
			in.close();
			mazeCollection.put(mazeName, new Maze3d(b));
			changeAndNotify("loaded", mazeName);
		}

		catch (FileNotFoundException e) 
		{

			e.printStackTrace();
		} catch (IOException e)
		{

			e.printStackTrace();
		}
	}

	@Override
	public void calcMazeSize(String name) {
		double size =-5;
		if(getMazeCollection().containsKey(name))
		{
			Maze3d myMaze = new Maze3d(getMazeCollection().get(name));
			size = myMaze.toByteArray().length;
			commandData.put("maze", name);
			changeAndNotify("calcedmazesize", size);
		}
		else
		{
			changeAndNotify("notify", "Bad Maze Name (m.calcmazesize)");
		}
		
	}

	@Override
	public void calcFileSize(String name) {
		File f = new File(name);
		if (f.length() == 0L)
		{
			this.saveMaze(name, "tempFileName");
			f = new File("tempFileName");
		}
		commandData.put("maze", name);
		changeAndNotify("calcedfilesize", f.length());
	}


	@Override
	public void solve(String name, String algo) 
	{
		new Thread(new Runnable() 
		{

			@Override
			public void run() 
			{
				if (getMazeCollection().containsKey(name))
				{
					SearchableMaze sMaze = new SearchableMaze(new Maze3d(getMazeCollection().get(name)));
					CommonSearcher searcher;
					Solution sol = new Solution();

					if (algo.equalsIgnoreCase("astarman"))
					{
						searcher = new AStar(new MazeManhattanDistance());
						sol = searcher.search(sMaze);
					}
					if (algo.equalsIgnoreCase("astarair"))
					{
						searcher = new AStar(new MazeEuclideanDistance());
						sol = searcher.search(sMaze);
					}
					if (algo.equalsIgnoreCase("bfs"))
					{
						searcher = new BFS();
						sol = searcher.search(sMaze);
					}
					getSolutionCollection().put(getMazeCollection().get(name), sol);
					changeAndNotify("solved",name);

				} else 
				{
					changeAndNotify("notify", "No maze by this name");
				}
			}
		}).start();
	}
	

	

	@Override
	public HashMap<String, Maze3d> getMazeCollection() {
		return mazeCollection;
		
	}

	@Override
	public HashMap<Maze3d, Solution> getSolutionCollection() {
		return solutionCollection;
		
	}

}
