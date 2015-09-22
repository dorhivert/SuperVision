/*
 * 
 */
package stateComparator;

import java.util.Comparator;

import states.State;

// TODO: Auto-generated Javadoc
/**
 * The Interface StateComparator.
 */
public interface StateComparator extends Comparator<State> 
{

	/**
	 * compares two state.
	 *
	 * @param s1 the s1
	 * @param s2 the s2
	 * @return the int
	 */
	public int compare(State s1, State s2);
}
