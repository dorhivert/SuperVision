/*
 * 
 */
package states;

import java.util.ArrayList;
import java.util.Iterator;

import mazeGenerators.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class State.
 */
public class State 
{

	/** The string that holds the state data. */
	private String stateString;   

	/** The previous state. */
	private State previousState;  		

	/** The cost calculated by g+h (called f in AStar algorithm. */
	private double cost;   		

	/**  The g and h are numbers to calculate cost. */
	private double g,h;					

	/**
	 * default CTOR.
	 */
	public State() 
	{
		this.stateString = new String();
		this.cost = 0;
		this.g = 0;
		this.h = 0;
	}

	/**
	 * Not default C'TOR.
	 *
	 * @param state the state
	 */
	private State(String state)
	{      
		this.stateString = new String(state);
	}

	/**
	 * copy CTOR.
	 *
	 * @param s1 the state to be copied
	 */
	public State(State s1) 
	{
		this.stateString = new String(s1.stateString);
		this.cost = s1.cost;
		this.previousState = new State();
		previousState = s1.previousState;
		this.g = s1.g;
		this.h = s1.h;
	}
	
	/**
	 * overrides equals method.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj)
	{ 
		return stateString.equals(((State)obj).stateString);
	}

	/**
	 * Gets the g.
	 *
	 * @return the g
	 */
	public double getG() 
	{
		return g;
	}

	/**
	 * Sets the g.
	 *
	 * @param g the new g
	 */
	public void setG(double g)
	{
		this.g = g;
	}

	/**
	 * Gets the h.
	 *
	 * @return the h
	 */
	public double getH()
	{
		return h;
	}

	/**
	 * Sets the h.
	 *
	 * @param h the new h
	 */
	public void setH(double h) 
	{
		this.h = h;
	}

	/**
	 * Gets the state string.
	 *
	 * @return the state string
	 */
	public String getStateString()
	{
		return stateString;
	}

	/**
	 * Sets the state string.
	 *
	 * @param stateString the string to be copied
	 */
	public void setStateString(String stateString)
	{
		this.stateString = new String(stateString);
	}

	/**
	 * Gets the previous state.
	 *
	 * @return the previous state
	 */
	public State getPreviousState()
	{
		return previousState;
	}

	/**
	 * Sets the previous state.
	 *
	 * @param prevState the new previous state
	 */
	public void setPreviousState(State prevState)
	{
		this.previousState = new State();
		this.previousState = prevState;
	}
	
	/**
	 * EXCACLY THE SAME
	 * used to perfectly fit into Eli's main code.
	 *
	 * @param n the new previous state
	 */
	public void setCameFrom(State n)
	{
		this.previousState = new State(n);
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public double getCost() 
	{
		return cost;
	}

	/**
	 * Calculate cost.
	 */
	public void calcCost()
	{
		this.cost = this.g + this.h;
	}

	/**
	 * Calculate g.
	 *
	 * @param g the g
	 * @param price the price (default 10)
	 */
	public void calcG(double g, double price)
	{
		this.g = g+ price;
	}

	/**
	 * String array to state array
	 * help method to convert string arrays to state arrays.
	 *
	 * @param pArray the array of strings
	 * @return the array of states
	 */
	public ArrayList<State> stringArraytoStateArray (ArrayList<String> pArray)
	{
		ArrayList<State> sArray = new ArrayList<State>();
		for (Iterator<String> iterator = pArray.iterator(); iterator.hasNext();) 
		{
			String string1 = (String) iterator.next();
			State sTemp = new State(string1);
			sTemp.toString();
			sArray.add(sTemp);
		}
		return sArray;
	}

	/**
	 * gets string and turns it into position.
	 *
	 * @param posInString the position in string
	 * @return the position
	 */
	public Position toPositionGeneric (String posInString)
	{
		Position p = new Position();
		String[] seperatedArray = posInString.split(",");
		p.setX(Integer.parseInt(seperatedArray[0]));
		p.setY(Integer.parseInt(seperatedArray[1]));
		p.setZ(Integer.parseInt(seperatedArray[2]));
		return p;
	}
}
