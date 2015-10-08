/*
 * 
 */
package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;

import solution.Solution;
import states.State;

// TODO: Auto-generated Javadoc
/**
 * The Class BFS.
 */
public class BFS extends CommonSearcher 
{

	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution search(Searchable s) 
	{
		addToOpenList(s.getStartState());
		HashSet<State> closedSet=new HashSet<State>();
		while(openList.size()>0)
		{
			State n=popOpenList();
			if(n.equals(s.getGoalState()))
				return backTrace(n,s.getStartState()); 
			closedSet.add(n); 
			ArrayList<State> successors=s.getAllPossibleStates(n);  
			for(State state : successors)
			{
				if(!closedSet.contains(state) && ! openList.contains(state))
				{
					state.setCameFrom(n);
					state.calcG(n.getG(), s.getPrice(n, state));
					state.calcCost();
					addToOpenList(state);
				}
				else
				{
					if (n.getG() + s.getPrice(n, state) < state.getG())
					{
						state.setCameFrom(n);
						state.calcG(n.getG(), s.getPrice(n, state));
						state.calcCost();
						if (!openList.contains(state)) 
						{
							addToOpenList(state);
						}
						else
						{
							openList.remove(state);
							addToOpenList(state);
						}
					}
				}
			}
		}
		System.out.println("NO SLUTION FOR THIS MAZE!!!");
		return null; 
	}
}
