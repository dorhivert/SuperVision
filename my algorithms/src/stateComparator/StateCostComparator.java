/*
 * 
 */
package stateComparator;

import states.State;

/**
 * The Class StateCostComparator.
 */
public class StateCostComparator implements StateComparator
{

	/* (non-Javadoc)
	 * @see stateComparator.StateComparator#compare(states.State, states.State)
	 */
	@Override
	public int compare(State s1, State s2)
	{
		return (int) ((s1.getCost())-(s2.getCost()));
	}
}
