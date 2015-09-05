/*
 * 
 */
package mazeGenerators;

/**
 * The Class SimpleMaze3dGenerator.
 */
public class SimpleMaze3dGenerator extends CommonMaze3dGenerator 
{

	@Override
	public Maze3d generate(int x, int y, int z)
	{
		final int min = 1; //minimum value to randomize moves
		Maze3d maze = new Maze3d(x,y,z);
		maze.setMaxX(x);
		maze.setMaxY(y);
		maze.setMaxZ(z);
		if ((x<1) || (y<1) || (z<1)) 
		{
			return null;
		}

		for (int i = 0; i < x; i++) 
		{
			for (int j = 0; j < y; j++) 
			{
				for (int k = 0; k < z; k++) 
				{
					maze.setCellValue(i, j, k, randomNumberMaker(1, 0));
				}
			}
		}
		maze.setStartPosition(randomNumberMaker(maze.getMaxX(), min), randomNumberMaker(maze.getMaxY(), min), randomNumberMaker(maze.getMaxZ(), min));
		maze.setFinishPosition(randomNumberMaker(maze.getMaxX(), min), randomNumberMaker(maze.getMaxY(), min), randomNumberMaker(maze.getMaxZ(), min));
		maze.setCorrentPosition(1, 1, 1);
		maze.getCorrentPosition().copyPosition(maze.getStartPosition());
		while (!(maze.getCorrentPosition().isEqual(maze.getFinishPosition())))
		{
			int move = randomNumberMaker(6, 1);
			if (move == 1 && (isMoveLegal(1, maze))) 
			{
				maze.moveUp();
				maze.setCellValueByPosition(maze.getCorrentPosition(), 0);
			}
			if (move == 2 && (isMoveLegal(2, maze))) 
			{
				maze.moveDown();
				maze.setCellValueByPosition(maze.getCorrentPosition(), 0);
			}
			if (move == 3 && (isMoveLegal(3, maze))) 
			{
				maze.moveRight();
				maze.setCellValueByPosition(maze.getCorrentPosition(), 0);
			}
			if (move == 4 && (isMoveLegal(4, maze))) 
			{
				maze.moveLeft();
				maze.setCellValueByPosition(maze.getCorrentPosition(), 0);
			}
			if (move == 5 && (isMoveLegal(5, maze))) 
			{
				maze.moveIn();
				maze.setCellValueByPosition(maze.getCorrentPosition(), 0);
			}
			if (move == 6 && (isMoveLegal(6, maze))) 
			{
				maze.moveOut();
				maze.setCellValueByPosition(maze.getCorrentPosition(), 0);
			}
		}
		return maze;
	}

	/* (non-Javadoc)
	 * @see mazeGenerators.Maze3dGenerator#printMazeKind()
	 */
	@Override
	public void printMazeKind() 
	{
		System.out.println("--- Maze Generator kind: SimpleMazeGenerator --- ");
	}
}
