
package model;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

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

	/* (non-Javadoc)
	 * @see model.Model#getFilesInDirectory(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see model.Model#generate3dMaze(java.lang.String, int)
	 */
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

	/* (non-Javadoc)
	 * @see model.Model#getCrossSection(char, int, java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see model.Model#saveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveMaze(String mazeName, String fileName) 
	{

		Maze3d myMaze = new Maze3d(((MyController) controller).getMazeCollection().get(mazeName+".maze"));
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
	}

	/* (non-Javadoc)
	 * @see model.Model#loadMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public Maze3d loadMaze(String mazeName, String fileName)
	{
		try 
		{
			MyDecompressorInputStream in = new MyDecompressorInputStream(new FileInputStream(fileName+".maze"));
			byte [] b = IOUtils.toByteArray(in);
			in.read(b);
			in.close();
			return (new Maze3d(b));
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

	/* (non-Javadoc)
	 * @see model.Model#calcMazeSize(java.lang.String)
	 */
	@Override
	public double calcMazeSize(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see model.Model#calcFileSize(java.lang.String)
	 */
	@Override
	public double calcFileSize(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see model.Model#solve(java.lang.String, java.lang.String)
	 */
	@Override
	public void solve(String name, String algo) {
		// TODO Auto-generated method stub

	}






}
