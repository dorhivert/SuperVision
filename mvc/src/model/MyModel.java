package model;

import java.io.File;

import controller.Controller;
import controller.MyController;
import mazeGenerators.Maze3d;
import mazeGenerators.Maze3dGenerator;
import mazeGenerators.MyMaze3dGenerator;

public class MyModel extends CommonModel {

	
	
	
	public MyModel(Controller controller) {
		super(controller);
	}

	@Override
	public String[] getFilesInDirectory(String path) {
        File directory = new File(path);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        String[] fileNames = new String[fList.length];
        for (int i = 0; i < fList.length; i++) {
			fileNames[i] =  fList[i].getName();
		}
		return fileNames;
	}

	@Override
	public void generate3dMaze(String name, int size) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				Maze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d maze = mg.generate(size, size, size);
				((MyController)controller).getMazeCollection().put(name, maze);
				((MyController)controller).notifyView("Maze named:" + name + "completed");
			}
			
		}).start();

		
	}

	@Override
	public int[][] getCrossSection(char xyz, int index, String name) {
		if(((MyController)controller).getMazeCollection().containsKey(name))
		{
			Maze3d tempMaze = ((MyController)controller).getMazeCollection().get(name);
			if((xyz=='x')||(xyz=='X'))
			{
				return tempMaze.getCrossSectionByX(index);
			}
			if((xyz=='y')||(xyz=='Y'))
			{
				return tempMaze.getCrossSectionByY(index);
			}
			if((xyz=='z')||(xyz=='Z'))
			{
				return tempMaze.getCrossSectionByZ(index);
			}
			else
			{
				((MyController)controller).notifyView("Bad letter, please enter x y or z, null returned");
				return null;
			}

		}
		else
		{
			((MyController)controller).notifyView("Illegal maze name, please try again,null returned");
			return null;
		}
	}

	@Override
	public void saveMaze(String fileName, String mazeName) {
		  Maze3dGenerator mg = new MyMaze3dGenerator();
		  Maze3d maze = mg.generate(15, 15, 15) ; 
		  // save it to a file
		  OutputStream out=new MyCompressorOutputStream( new FileOutputStream("1.maz"));
		//  OutputStream out= new FileOutputStream("1.maz");
		  out.write(maze.toByteArray());
		  out.flush();
		  out.close();
		  InputStream in=new MyDecompressorInputStream( new FileInputStream("1.maz"));
		//  InputStream in=new FileInputStream("1.maz");
		  byte[] b=new byte[maze.toByteArray().length];
		  in.read(b);
		  in.close();  
		//  Maze3d loaded2=new Maze3d(b);
		  Maze3d loaded=new Maze3d(b);
//		  System.out.println(loaded.equals(maze));
		 boolean flag = true;
		 flag = maze.equals(loaded);
		  System.out.println(flag);

		
	}
	
	@Override
	public Maze3d loadMaze(String fileName, String mazeName) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public double calculateMazeSize(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateFileSize(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Solve(String name, String algorithm) {
		// TODO Auto-generated method stub
		
	}


		
}
