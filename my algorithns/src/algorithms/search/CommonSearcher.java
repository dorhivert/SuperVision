/*
 * 
 */
package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

import solution.Solution;
import stateComparator.StateComparator;
import stateComparator.StateCostComparator;
import states.State;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonSearcher.
 */
public abstract class CommonSearcher implements Searcher 
{

	/** The open list. */
	protected PriorityQueue<State> openList;
	
	/** The evaluated nodes. */
	private int evaluatedNodes;
	
	/**
	 * CTOR.
	 * default comparator - stateCost comparator
	 */
	public CommonSearcher()
	{
		StateComparator sc = new StateCostComparator();
		Comparator<State> comparator = (Comparator<State>)sc;
		openList=new PriorityQueue<State>(1, comparator);
		setEvaluatedNodes(0);
	}

	/**
	 * CTOR.
	 *
	 * @param sc the state comparator
	 */
	public CommonSearcher(StateComparator sc)
	{
		Comparator<State> comparator = (Comparator<State>)sc;
		openList=new PriorityQueue<State>(1, comparator);
		setEvaluatedNodes(0);
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public abstract Solution search(Searchable s);


	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#getNumberOfNodesEvaluated()
	 */
	@Override
	public int getNumberOfNodesEvaluated()
	{
		return evaluatedNodes;
	}

	/**
	 * Pop open list.
	 *
	 * @return the state
	 */
	protected State popOpenList()
	{
		evaluatedNodes++;
		return openList.poll();
	}


	/**
	 * Gets the evaluated nodes.
	 *
	 * @return the evaluated nodes
	 */
	public int getEvaluatedNodes() 
	{
		return evaluatedNodes;
	}

	/**
	 * Sets the evaluated nodes.
	 *
	 * @param evaluatedNodes the new evaluated nodes
	 */
	private void setEvaluatedNodes(int evaluatedNodes)
	{
		this.evaluatedNodes = evaluatedNodes;
	}

	/**
	 * Adds the state to open list.
	 *
	 * @param s1 the state
	 */
	public void addToOpenList(State s1)
	{
		State s2 = new State(s1);
		s2.toString();
		openList.add(s2);
	}
	
	/**
	 * Back trace.
	 *
	 * @param goalState the goal state
	 * @param startState the start state
	 * @return the solution (in sorted list)
	 */
	protected Solution backTrace(State goalState, State startState) 
	{
		State s1 = new State(startState);
		State s2 = new State(goalState);

		Solution solution1 = new Solution();
		while (!s2.equals(s1)) 
		{
			solution1.getSolutionList().add(s2.getStateString());
			State sTemp = new State (s2);
			s2 = new State(sTemp.getPreviousState());
		}
		solution1.getSolutionList().add(startState.getStateString());
		solution1.sortback();
		return solution1;
	}
}
