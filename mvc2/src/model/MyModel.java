
package model;

import java.io.File;

import mazeGenerators.Maze3d;
import mazeGenerators.Maze3dGenerator;
import mazeGenerators.MyMaze3dGenerator;
import controller.Controller;
import controller.MyController;

/**
 * The Class MyModel.
 */
public class MyModel extends CommonModel
{

	/**
	 * Instantiates a new my model.
	 *
	 * @param controller the controller
	 */
	public MyModel(Controller controller)
	{
		super(controller);
	}

	@Override
	public String[] getFilesInDirectory(String path)
	{
		File f = new File(path);
		File[] fList = f.listFiles();
		String[] fileNames = new String[fList.length];
		for (int i = 0; i < fList.length; i++)
		{
			fileNames[i] = fList[i].getName();	
		}
		return fileNames;
	}

	@Override
	public void generate3dMaze(String name, int size)
	{
		new Thread(new Runnable()
		{

			@Override
			public void run() 
			{
				Maze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d myMaze = mg.generate(size, size, size);
				((MyController) controller).getMazeCollection().put(name, myMaze);
				((MyController) controller).notifyView("Maze "+name+" is ready" );;
			}
		}).start();

	}

	@Override
	public int[][] getCrossSection(char xyz, int index, String name)
	{
		if(((MyController) controller).getMazeCollection().containsKey(name))
		{
			Maze3d myMaze = new Maze3d(((MyController) controller).getMazeCollection().get(name));
			if (xyz == 'x' || xyz == 'X') 
			{
				return myMaze.getCrossSectionByX(index);
			}
			if (xyz == 'y' || xyz == 'Y') 
			{
				return myMaze.getCrossSectionByX(index);
			}
			if (xyz == 'z' || xyz == 'Z') 
			{
				return myMaze.getCrossSectionByX(index);
			}
			else 
			{
				((MyController) controller).notifyView("bad X/Y/Z cord" );
				return null;
			}
		}
		else
		{
			((MyController) controller).notifyView("Bad Maze Name (m.getcross)" );
			return null;
		}
	}

	@Override
	public void saveMaze(String mazeName, String fileName) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Maze3d loadMaze(String mazeName, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calcMazeSize(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcFileSize(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void solve(String name, String algo) {
		// TODO Auto-generated method stub

	}






}
