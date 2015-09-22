/*
 * 
 */
package mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import states.State;
import states.MazeState;

// TODO: Auto-generated Javadoc
/**
 * The Class MyMaze3dGenerator.
 */
public class MyMaze3dGenerator extends CommonMaze3dGenerator
{

	/* (non-Javadoc)
	 * @see mazeGenerators.CommonMaze3dGenerator#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int x, int y, int z) 
	{
		Maze3d maze = new Maze3d(x,y,z);
		int min = 1;
		maze.setMaxX(x);
		maze.setMaxY(y);
		maze.setMaxZ(z);
		Random randomGenerator = new Random();
		Stack <State> cellStack = new Stack <State>();
		ArrayList<Position> neighboors = new ArrayList<Position>();
		ArrayList<State> neighboorsState = new ArrayList<State>();
		maze.buildAllWalls();
		maze.setStartPosition(randomNumberMaker(maze.getMaxX(), min), randomNumberMaker(maze.getMaxY(), min), randomNumberMaker(maze.getMaxZ(), min));
		maze.setCorrentPosition(1, 1, 1);
		maze.getCorrentPosition().copyPosition(maze.getStartPosition());
		maze.setFinishPosition(randomNumberMaker(maze.getMaxX(), min), randomNumberMaker(maze.getMaxY(), min), randomNumberMaker(maze.getMaxZ(), min));
		maze.setCellValueByPosition(maze.getStartPosition(), 0); 
		Position p = new Position(maze.getCorrentPosition());
		State s1 = new MazeState(p);

		do 
		{
			neighboors = maze.getDoubleNeighboorsWithWalls(p);
			if (!(neighboors.isEmpty())) 
			{
				neighboorsState = ((MazeState) s1).positionArraytoStateArray(neighboors);
				int index = randomGenerator.nextInt(neighboorsState.size());
				State s2 = neighboorsState.get(index);
				maze.clearTheWay(s1, s2);	
				cellStack.push(s1);
				s2.setPreviousState(s1);
				s1 = new MazeState(s2);
				p.copyPosition(((MazeState) s2).getP());
			}
			else
			{
				s1 = cellStack.pop();
				p.copyPosition(((MazeState) s1).getP());
			}

		} while (!(cellStack.isEmpty()));

		while (maze.getCellValueByPosition(maze.getFinishPosition()) == 1)
		{
			maze.setFinishPosition(randomNumberMaker(maze.getMaxX(), min), randomNumberMaker(maze.getMaxY(), min), randomNumberMaker(maze.getMaxZ(), min));
		}
		return maze;
	}

	/* (non-Javadoc)
	 * @see mazeGenerators.Maze3dGenerator#printMazeKind()
	 */
	@Override
	public void printMazeKind() 
	{
		System.out.println("--- Maze Generator kind: myMaze (DFS) --- ");
	}

}
