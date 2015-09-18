package controller;

import java.util.HashMap;

public class MyController extends CommonController
{

	@Override
	public void initCommands(HashMap<String, Command> map) 
	{

		map.put("dir", new Command() 
		{

			@Override
			public void doCommand() 
			{
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void displayDirectory(String[] path) 
	{
		view.displayDirectory(path);
	}

	@Override
	public void generate3dMaze(String name, int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSection(String name, int index, int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMazeSize(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayFileSize(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFilesInDirectory(String path) {
		// TODO Auto-generated method stub
		
	}
}
