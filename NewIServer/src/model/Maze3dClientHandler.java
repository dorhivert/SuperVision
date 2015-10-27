package model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import mazeGenerators.Maze3d;
import mazeGenerators.Maze3dGenerator;
import solution.Solution;
import algorithms.search.Searcher;

public class Maze3dClientHandler implements ClientHandler 
{

	/** The maze generator algorithm. */
	private volatile Maze3dGenerator mazeGen;

	/** The solver algorithm. */
	private volatile Searcher searcher;

	ExecutorService threadPool;

	/** The maze collection. */
	private HashMap<String, Maze3d> mazeCollection = new HashMap<String, Maze3d>();

	/** The solution collection. */
	private HashMap<Maze3d, Solution> solutionCollection = new HashMap<Maze3d, Solution>();

	private HashMap<String, Object> commandData = new HashMap<String, Object>();

	@Override
	public void handleClient(InputStream in, OutputStream out) 
	{
		try
		{

			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			ObjectOutputStream oos = new ObjectOutputStream(out);

			String str;
			String[] sp;
			while (!((str = br.readLine()).equals("stop")))
			{
				sp = str.split(" ");
				switch (sp[0])
				{
				case "generate":
				{
					int _size = Integer.parseInt(sp[4]);
					boolean flag = 	generateMaze(_size, sp[3]);
					if (flag)
					{
						oos.writeObject(flag);
					}


				}break;
				case "getSolution":
				{
					int _size = Integer.parseInt(sp[4]);
					boolean flag = 	generateMaze(_size, sp[3]);
					if (flag)
					{
						oos.writeObject(flag);
					}


				}break;
				
				
				case "display":
				{
					switch (sp[1])
					{
					case "maze":
					{
						Maze3d m = getMaze(sp[2]);

						if(m != null)
						{
							oos.writeBoolean(true);
							oos.flush();

							ByteArrayOutputStream mazeBaos = new ByteArrayOutputStream();


							ObjectOutputStream mazeOut= new ObjectOutputStream(mazeBaos);
							mazeOut.writeObject(m);
							mazeOut.close();

							oos.writeObject(mazeBaos.toByteArray());
						}

					}break;
					case "solution":
					{

					}break;
					}

				}break;
				case "exit":
				{

				}
				case "solve":
				{

				}break;
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}



	@Override
	public void stop() 
	{
		// TODO Auto-generated method stub

	}

	public boolean generateMaze(int size, String name) 
	{

		Maze3d m = mazeGen.generate(size, size, size);
		mazeCollection.put(name, m);		
		return true;
	}

	public Maze3d getMaze(String name)
	{
		return mazeCollection.get(name);
	}
}

