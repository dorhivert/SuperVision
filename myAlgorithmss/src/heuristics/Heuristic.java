/*
 * 
 */
package heuristics;

import states.State;

// TODO: Auto-generated Javadoc
/**
 * The Interface Heuristic.
 */
public interface Heuristic 
{
	
	/**
	 * H.
	 * (h is heuristics cost)
	 * @param s1 the s1
	 * @param s2 the s2
	 * @return the double
	 */
	public double h(State s1, State s2);
}
