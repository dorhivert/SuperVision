/*
 * 
 */
package algorithms.search;

import java.util.ArrayList;
import mazeGenerators.Maze3d;
import states.MazeState;
import states.State;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchableMaze.
 */
public class SearchableMaze implements Searchable 
{

	/** The maze. */
	private Maze3d maze;
	
	/** The start state. */
	private State startState;
	
	/** The goal state. */
	private State goalState;
	
	/** The move price. */
	private final int movePrice = 10;

	/**
	 * CTOR.
	 *
	 * @param _maze the _maze
	 */
	public SearchableMaze(Maze3d _maze) 
	{
		this.maze = new Maze3d(_maze);
		this.startState = new MazeState(_maze.getStartPosition());
		this.goalState = new MazeState(_maze.getFinishPosition());
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getStartState()
	 */
	@Override
	public State getStartState()
	{
		return this.startState;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getGoalState()
	 */
	@Override
	public State getGoalState() 
	{
		return this.goalState;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getAllPossibleStates(states.State)
	 */
	@Override
	public ArrayList<State> getAllPossibleStates(State s1) 
	{
		ArrayList<String> stringList = maze.getPossibleMovesInArrayList(s1);
		ArrayList<State> statesList = s1.stringArraytoStateArray(stringList);

		return statesList;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getPrice(states.State, states.State)
	 */
	@Override
	public double getPrice(State s1, State s2) 
	{
		if (s1.equals(s2))
		{
			return 0;
		}
		return movePrice;
	}
}
