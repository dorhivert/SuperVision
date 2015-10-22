/*
 * 
 */
package mazeGenerators;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonMaze3dGenerator.
 */
public abstract class CommonMaze3dGenerator implements Maze3dGenerator
{

	/* (non-Javadoc)
	 * @see mazeGenerators.Maze3dGenerator#generate(int, int, int)
	 */
	@Override
	public abstract Maze3d generate(int x, int y, int z); 

	/* (non-Javadoc)
	 * @see mazeGenerators.Maze3dGenerator#measureAlgorithmTime(int, int, int)
	 */
	@Override
	public String measureAlgorithmTime(int x, int y, int z) 
	{
		Long time1 = System.currentTimeMillis();
		Long time2;
		Long time3;
		String timeDiffrences;
		this.generate(x,y,z);
		time2 = System.currentTimeMillis();
		time3 = time2-time1;
		timeDiffrences = time3.toString();
		return timeDiffrences;
	}

	/**
	 * Random number maker.
	 *
	 * @param max the max
	 * @param min the min
	 * @return the int
	 */
	protected int randomNumberMaker(int max, int min)
	{
		int randomized = 0;
		Random rand = new Random();
		randomized = rand.nextInt((max - min) + 1) + min;
		return randomized; 
	}

	/**
	 * Checks if is move legal.
	 *
	 * @param move the move
	 * @param maze the maze
	 * @return true, if is move legal
	 */
	protected boolean isMoveLegal(int move,Maze3d maze)
	{
		if (move == 1)
		{
			if (maze.getCorrentPosition().getY()+1>maze.getMaxY())
			{
				return false;
			}

		}
		if (move == 2)
		{
			if (maze.getCorrentPosition().getY()-1<0)
			{
				return false;
			}

		}
		if (move == 3)
		{
			if (maze.getCorrentPosition().getX()+1>maze.getMaxX())
			{
				return false;
			}

		}
		if (move == 4)
		{
			if (maze.getCorrentPosition().getY()-1<0)
			{
				return false;
			}

		}
		if (move == 5)
		{
			if (maze.getCorrentPosition().getZ()+1>maze.getMaxZ())
			{
				return false;
			}

		}
		if (move == 6)
		{
			if (maze.getCorrentPosition().getZ()-1<0)
			{
				return false;
			}

		}
		return true;
	}
}
