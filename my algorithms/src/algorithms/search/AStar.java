/*
 * 
 */
package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;

import heuristics.Heuristic;
import solution.Solution;
import states.State;

// TODO: Auto-generated Javadoc
/**
 * The Class AStar.
 */
public class AStar extends CommonSearcher
{

	/** The h. */
	Heuristic h;

	/**
	 * Instantiates a new Astar searcher.
	 *
	 * @param h the h
	 */
	public AStar(Heuristic h)
	{
		super();
		this.h = h;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution search(Searchable s) 
	{
		HashSet<State> closedSet = new HashSet<State>();
		addToOpenList(s.getStartState());

		while (!openList.isEmpty())
		{
			State n = popOpenList();
			if(n.equals(s.getGoalState()))
				return backTrace(n,s.getStartState()); 
			closedSet.add(n); 
			ArrayList<State> successors=s.getAllPossibleStates(n);
			for (State state : successors)
			{
				if (closedSet.contains(state))
					continue;
				else if (!openList.contains(state))
				{
					state.setCameFrom(n);
					state.calcG(n.getG(), s.getPrice(n, state));
					state.setH(h.h(state, s.getGoalState()));
					state.calcCost();
					openList.add(state);
				}
				else if (openList.contains(state))
				{
					if	(n.getG() + s.getPrice(n, state) < state.getG())
					{
						state.setCameFrom(n);
						state.calcG(n.getG(), s.getPrice(n, state));
						state.calcCost();
						openList.remove(state);
						openList.add(state);
					}	
				}
			}
		}
		System.out.println("no solution");
		return null;
	}
}
