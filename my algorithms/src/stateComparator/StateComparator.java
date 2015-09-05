/*
 * 
 */
package stateComparator;

import java.util.Comparator;

import states.State;

/**
 * The Interface StateComparator.
 */
public interface StateComparator extends Comparator<State> 
{

	/**
	 * compares two state
	 */
	public int compare(State s1, State s2);
}
