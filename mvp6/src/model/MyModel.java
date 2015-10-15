package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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
import mazeGenerators.SimpleMaze3dGenerator;
import presenter.Properties;
import solution.Solution;

public class MyModel extends Observable implements Model {

	ExecutorService threadPool;
	
	/** The maze collection. */
	private HashMap<String, Maze3d> mazeCollection = new HashMap<String, Maze3d>();

	/** The solution collection. */
	private HashMap<Maze3d, Solution> solutionCollection = new HashMap<Maze3d, Solution>();
	
	/** The Commands collection. */
	private HashMap<String,Object> commandData = new HashMap<String,Object>();
	
	/** The Properties object. */
	private Properties prop;

	
	
	public MyModel(Properties prop)
	{
		super();
		threadPool = Executors.newFixedThreadPool(prop.getHowManyThreads());
		loadFromZip();
		changeAndNotify("loadZip", "Maze has been loaded from the file");
		
	}
	
	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
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
				if (prop.getGenerationAlgo().equalsIgnoreCase("simple"))
				{
					mg = new SimpleMaze3dGenerator();
				}
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
	public void solve(String name) 
	{
		if (getMazeCollection().containsKey(name))
		{
			Future<Solution> mySolution = threadPool.submit(new Callable<Solution>()
					{
				@Override
				public Solution call() throws Exception 
				{
					Maze3d myMaze = new Maze3d(getMazeCollection().get(name));
					SearchableMaze sMaze = new SearchableMaze(myMaze);
					CommonSearcher searcher;
					Solution sol = new Solution();


					if (prop.getSolvingAlgo().equalsIgnoreCase("astarman"))
					{
						searcher = new AStar(new MazeManhattanDistance());
						sol = searcher.search(sMaze);
					}
					if (prop.getSolvingAlgo().equalsIgnoreCase("astarair"))
					{
						searcher = new AStar(new MazeEuclideanDistance());
						sol = searcher.search(sMaze);
					}
					if (prop.getSolvingAlgo().equalsIgnoreCase("bfs"))
					{
						searcher = new BFS();
						sol = searcher.search(sMaze);
					}
					return sol;
				}
					});
			try
			{
				getSolutionCollection().put(getMazeCollection().get(name), mySolution.get());
			} 
			catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
			changeAndNotify("solved", name);
		}
		else 
		{
			changeAndNotify("notify", "Bad Maze Name (m.solve)");
		}
	}
	

	

	@Override
	public HashMap<String, Maze3d> getMazeCollection() {
		return mazeCollection;
		
	}

	@Override
	public HashMap<Maze3d, Solution> getSolutionCollection() {
		return solutionCollection;
		
	}

	@Override
	public void officialExit() {
		saveToZip();
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(59,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadPool.shutdownNow();
		changeAndNotify("quit", "Official Exit");
	}
	
	@SuppressWarnings("unchecked")
	private void loadFromZip()
	{
		File file = new File("mazeSolutionCache.gzip");
		try {
				if(!(file.createNewFile()))	
				{
				ObjectInputStream mazeZip = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
				mazeCollection = (HashMap<String, Maze3d>) mazeZip.readObject();
				solutionCollection = (HashMap<Maze3d, Solution>) mazeZip.readObject();
				mazeZip.close();
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	private void saveToZip()
	{
		File file = new File("mazeSolutionCache.gzip");

			try {
				ObjectOutputStream mazeZip = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
				mazeZip.writeObject(mazeCollection);
				mazeZip.writeObject(solutionCollection);
				mazeZip.flush();
				mazeZip.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

}
