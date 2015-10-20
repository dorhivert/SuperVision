package model;

import heuristics.MazeEuclideanDistance;
import heuristics.MazeManhattanDistance;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.File;
import java.io.FileInputStream;
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

import presenter.Properties;
import mazeGenerators.Maze3d;
import mazeGenerators.Maze3dGenerator;
import mazeGenerators.MyMaze3dGenerator;
import mazeGenerators.SimpleMaze3dGenerator;
import solution.Solution;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.SearchableMaze;

public class MyModel extends Observable implements Model
{

	ExecutorService threadPool;

	/** The maze collection. */
	private HashMap<String, Maze3d> mazeCollection = new HashMap<String, Maze3d>();

	/** The solution collection. */
	private HashMap<Maze3d, Solution> solutionCollection = new HashMap<Maze3d, Solution>();

	private HashMap<String, Object> commandData = new HashMap<String, Object>();
	
	private Properties prop;
	



	public MyModel(Properties _prop)
	{
		super();
		this.setProp(_prop);
		threadPool = Executors.newFixedThreadPool(this.getProp().getNumOfThreads());
		try 
		{
			loadFromZip();
			changeAndNotify("loadZip", "Mazes has been loaded from file");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public Properties getProp()
	{
		return prop;
	}

	public void setProp(Properties prop)
	{
		this.prop = prop;
	}

	@Override
	public void generate3dMaze(String name, int size)
	{
		Future<Maze3d> myMaze = threadPool.submit(new Callable<Maze3d>()
				{
			@Override
			public Maze3d call() throws Exception
			{
				Maze3dGenerator mg = new MyMaze3dGenerator();
				if (getProp().getGenerationAlgo().equalsIgnoreCase("simple"))
				{
					mg = new SimpleMaze3dGenerator();
				}
				Maze3d myMaze = mg.generate(size, size, size);
				return myMaze;
			}
				});
		try 
		{
			getMazeCollection().put(name, myMaze.get());
		}
		catch (InterruptedException | ExecutionException e) 
		{

			e.printStackTrace();
		}
		changeAndNotify("generated", name);
	}


	@Override
	public void saveMaze(String mazeName, String fileName) 
	{
		Maze3d myMaze = new Maze3d(getMazeCollection().get(mazeName));
		try 
		{
			OutputStream out=new MyCompressorOutputStream( new FileOutputStream(fileName));
			out.write(myMaze.toByteArray());
			out.flush();
			out.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		changeAndNotify("saved", mazeName);
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


					if (prop.getSolveAlgo().equalsIgnoreCase("astarman"))
					{
						searcher = new AStar(new MazeManhattanDistance());
						sol = searcher.search(sMaze);
					}
					if (prop.getSolveAlgo().equalsIgnoreCase("astarair"))
					{
						searcher = new AStar(new MazeEuclideanDistance());
						sol = searcher.search(sMaze);
					}
					if (prop.getSolveAlgo().equalsIgnoreCase("bfs"))
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
	public HashMap<String, Maze3d> getMazeCollection()
	{
		return this.mazeCollection;
	}

	@Override
	public HashMap<Maze3d, Solution> getSolutionCollection() 
	{
		return this.solutionCollection;
	}


	@Override
	public void getFilesInDirectory(String path)
	{
		File f = new File(path);
		File[] fList = f.listFiles();
		String[] fileNames = new String[1];
		if (fList == null) 
		{
			fileNames[0] = "No Files in these directory";
			changeAndNotify("dir", fileNames);
			return;
		}
		else
		{
			fileNames = new String[fList.length];
			for (int i = 0; i < fList.length; i++)
			{
				fileNames[i] = fList[i].getName();	
			}
			changeAndNotify("dir", fileNames);
		}
	}


	@Override
	public void getCrossSection(char xyz, int index, String name) 
	{
		if(getMazeCollection().containsKey(name))
		{
			Maze3d myMaze = new Maze3d(getMazeCollection().get(name));
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
			changeAndNotify("notify", "Bad Maze Name (m.getcross)");
			return;
		}
	}


	@Override
	public void loadMaze(String mazeName, String fileName)
	{
		InputStream in;
		try 
		{
			in = new MyDecompressorInputStream( new FileInputStream(fileName));
			byte[] b = new byte[((MyDecompressorInputStream) in).getLength()];
			in.read(b);
			in.close();
			this.mazeCollection.put(mazeName, new Maze3d(b));
			changeAndNotify("loaded", mazeName);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}


	@Override
	public void calcMazeSize(String name) 
	{
		double size =-5;
		if(getMazeCollection().containsKey(name))
		{
			Maze3d myMaze = new Maze3d(getMazeCollection().get(name));
			size = myMaze.toByteArray().length;
			commandData.put("maze", name);
			changeAndNotify("calcedMazeSize", size);
		}
		else
		{
			changeAndNotify("notify", "Bad Maze Name (m.calcmazesize)");
		}
	}


	@Override
	public void calcFileSize(String name) 
	{
		File f = new File(name);
		if (f.length() == 0L)
		{
			this.saveMaze(name, "tempFileName");
			f = new File("tempFileName");
		}
		commandData.put("maze", name);
		changeAndNotify("calcedFileSize", f.length());

	}

	@Override
	public HashMap<String, Object> getCommandData() 
	{
		return this.commandData;
	}

	@Override
	public void officialExit() 
	{
		threadPool.shutdown();
		try 
		{
			saveToZip();
			changeAndNotify("saveZip", "File has been saved");
			threadPool.awaitTermination(59, TimeUnit.SECONDS);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		threadPool.shutdownNow();
		changeAndNotify("quit", "Official Exit");
	}

	private void changeAndNotify(String command, Object obj)
	{
		if (obj != null) 
		{
			this.commandData.put(command, obj);
		}
		setChanged();
		notifyObservers(command);
	}

	private void saveToZip()
	{
		try
		{
			ObjectOutputStream zipMaze = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("mazeSolutionCache.gzip")));
			zipMaze.writeObject(mazeCollection);
			zipMaze.writeObject(solutionCollection);
			zipMaze.flush();
			zipMaze.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadFromZip()
	{
		File myFile = new File("mazeSolutionCache.gzip");
		try
		{
			if(!myFile.createNewFile())
			{
				ObjectInputStream mazeZip = new ObjectInputStream(new GZIPInputStream(new FileInputStream(myFile)));

				this.mazeCollection = (HashMap<String, Maze3d>) mazeZip.readObject();
				this.solutionCollection = (HashMap<Maze3d, Solution>) mazeZip.readObject();
				
				mazeZip.close();
			} 
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void creatNewProperties(String[] args) 
	{
		new PropManager().setNewProperties(args);
	}

	@Override
	public void loadNewProperties(String[] args)
	{
		new PropManager().loadNewPropsFromFile(args);
	}
}
