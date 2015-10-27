package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
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
import controller.Controller;
import controller.MyController;
import controller.Properties;
import heuristics.MazeEuclideanDistance;
import heuristics.MazeManhattanDistance;
import mazeGenerators.Maze3d;
import mazeGenerators.Maze3dGenerator;
import mazeGenerators.MyMaze3dGenerator;
import mazeGenerators.SimpleMaze3dGenerator;
import solution.Solution;
import view.MyView;
import view.View;

public class MyServerModel implements Model{

	Controller controller;
	
	ExecutorService threadPool;

	/** The maze collection. */
	private HashMap<String, Maze3d> mazeCollection = new HashMap<String, Maze3d>();

	/** The solution collection. */
	private HashMap<Maze3d, Solution> solutionCollection = new HashMap<Maze3d, Solution>();

	private HashMap<String, Object> commandData = new HashMap<String, Object>();
	
	private Properties prop;
	
	int port;
	ServerSocket server;
	
	ClinetHandler clinetHandler;
	int numOfClients;
	
	
	volatile boolean stop;
	
	Thread mainServerThread;
	
	int clientsHandled=0;
	
	public MyServerModel(int port,ClinetHandler clinetHandler,int numOfClients,Properties _prop,Controller controller) {
		this.port=port;
		this.clinetHandler=clinetHandler;
		this.numOfClients=numOfClients;
		PropManager pm = new PropManager();
		prop = pm.loadProp();

//		threadPool = Executors.newCachedThreadPool();
		threadPool = Executors.newFixedThreadPool(this.getProp().getNumOfThreads());
//		try 
//		{
//			loadFromZip();
////			changeAndNotify("loadZip", "Mazes has been loaded from file");
//			controller.notifyView("mazes have been loaded from file");//TODO
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
	}
	
	
	public void start() throws Exception{
		server=new ServerSocket(port);
		server.setSoTimeout(10*1000);
		threadPool=Executors.newFixedThreadPool(numOfClients);
		
		mainServerThread=new Thread(new Runnable() {			
			@Override
			public void run() {
				while(!stop){
					try {
						final Socket someClient=server.accept();
						if(someClient!=null){
							threadPool.execute(new Runnable() {									
								@Override
								public void run() {
									try{										
										clientsHandled++;
										System.out.println("\thandling client "+clientsHandled);
										clinetHandler.handleClient(someClient.getInputStream(), someClient.getOutputStream());
										someClient.close();
										System.out.println("\tdone handling client "+clientsHandled);										
									}catch(IOException e){
										e.printStackTrace();
									}									
								}
							});								
						}
					}
					catch (SocketTimeoutException e){
						System.out.println("no clinet connected...");
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("done accepting new clients.");
			} // end of the mainServerThread task
		});
		
		mainServerThread.start();

	}
	
	@SuppressWarnings("unused")
	public void close() throws Exception{
		stop=true;	
		// do not execute jobs in queue, continue to execute running threads
		System.out.println("shutting down");
		threadPool.shutdown();
		// wait 10 seconds over and over again until all running jobs have finished
		boolean allTasksCompleted=false;
		while(!(allTasksCompleted=threadPool.awaitTermination(10, TimeUnit.SECONDS)));
		
		System.out.println("all the tasks have finished");

		mainServerThread.join();		
		System.out.println("main server thread is done");
		
		server.close();
		System.out.println("server is safely closed");
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
//		changeAndNotify("generated", name); 
//		controller.notifyView("generated");// TODO
		System.out.println("generating");
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
//			changeAndNotify("solved", name);
//			controller.notifyView("solved");//TODO
			System.out.println("solving");
		}
		else 
		{
//			changeAndNotify("notify", "Bad Maze Name (m.solve)");
			controller.notifyView("bad maze name");//TODO
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
//			changeAndNotify("saveZip", "File has been saved");//TODO
			controller.notifyView("file has been saved");
			threadPool.awaitTermination(59, TimeUnit.SECONDS);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		threadPool.shutdownNow();
//		changeAndNotify("quit", "Official Exit");
		controller.notifyView("quit, official exit");//TODO
	}

//	private void changeAndNotify(String command, Object obj)
//	{
//		if (obj != null) 
//		{
//			this.commandData.put(command, obj);
//		}
//		setChanged();
//		notifyObservers(command);
//	}

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

	public void creatNewProperties(String[] args) 
	{
		new PropManager().setNewProperties(args);
	}

	public void loadNewProperties(String[] args)
	{
		new PropManager().loadNewPropsFromFile(args);
	}
	
	
	public static void main(String[] args) throws Exception{
		System.out.println("Server Side");
		System.out.println("type \"close the server\" to stop it");
		PropManager pm = new PropManager();
		Properties prop = pm.loadProp();
		Controller controller = new MyController();
		
		ClinetHandler ch = new MyClientHandler();
		MyServerModel server=new MyServerModel(5400,ch,10,prop,controller);
		controller.setModel(server);
		View view = new MyView(controller);
		controller.setView(view);
		server.start();
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String line;
		view.start();
//		while(!(line = in.readLine()).equals("close the server"))
//		{
//			if(line.equals("generate 3d maze tom 15"))
//			{
//				server.generate3dMaze("tom",15);
//			}
//		}
		
//		server.close();		
		
	}
}
