package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import io.MyDecompressorInputStream;
import mazeGenerators.Maze3d;
import solution.Solution;

public class MyModel extends ObservableModel {

	ExecutorService threadPool;
	
	/** The maze collection. */
	private HashMap<String, Maze3d> mazeCollection = new HashMap<String, Maze3d>();

	/** The solution collection. */
	private HashMap<Maze3d, Solution> solutionCollection = new HashMap<Maze3d, Solution>();
	
	private HashMap<String,Object> commandData = new HashMap<String,Object>();

	
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
		changeAndNotify("",345.0);
	}
	
	@Override
	public void getFilesInDirectory(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generate3dMaze(String name, int size) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCrossSection(char xyz, int index, String name) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveMaze(String mazeName, String fileName) {
		// TODO Auto-generated method stub

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
			return new Maze3d(b);
		}

		catch (FileNotFoundException e) 
		{

			e.printStackTrace();
		} catch (IOException e)
		{

			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void calcMazeSize(String name) {
		// TODO Auto-generated method stub
	}

	@Override
	public void calcFileSize(String name) {
		// TODO Auto-generated method stub
	}

	@Override
	public void solve(String name, String algo) {
		// TODO Auto-generated method stub

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
