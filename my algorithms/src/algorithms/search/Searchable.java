/*
 * 
 */
package algorithms.search;

import java.util.ArrayList;

import states.State;

/**
 * The Interface Searchable.
 */
public interface Searchable 
{
	
	/**
	 * Gets the start state.
	 *
	 * @return the start state
	 */
	State getStartState();
	
	/**
	 * Gets the goal state.
	 *
	 * @return the goal state
	 */
	State getGoalState();
	
	/**
	 * Gets the all possible states.
	 *
	 * @param s the s
	 * @return the all possible states
	 */
	ArrayList<State> getAllPossibleStates(State s);
	
	/**
	 * Gets the move price.
	 *
	 * @param s1 the s1
	 * @param s2 the s2
	 * @return the price
	 */
	double getPrice (State s1, State s2);
}
