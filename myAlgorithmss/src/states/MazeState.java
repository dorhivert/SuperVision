/*
 * 
 */
package states;

import java.util.ArrayList;
import java.util.Iterator;
import mazeGenerators.Position;


// TODO: Auto-generated Javadoc
/**
 * The Class MazeState.
 */
public class MazeState extends State
{

	/**  The position. */
	private Position p;

	/**
	 * C'tor.
	 *
	 * @param p the position
	 */
	public MazeState(Position p)
	{
		super();
		this.p = new Position(p);
		this.toString();
	}
	
	/**
	 * copy C'TOR.
	 *
	 * @param s the state
	 */
	public MazeState(State s) 
	{
		super();
		this.p = new Position(((MazeState) s).getP());
		this.toString();
		if (s.getPreviousState() != null) 
		{
			this.setPreviousState(s.getPreviousState());
		}
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the p
	 */
	public Position getP() 
	{
		return p;
	}
	
	/**
	 * converts MazeState to string.
	 *
	 * @return the p
	 */
	public String toString ()
	{
		Integer tempIntx = new Integer(p.getX());
		Integer tempInty = new Integer(p.getY());
		Integer tempIntz = new Integer(p.getZ());
		this.setStateString((tempIntx.toString() + ',' + tempInty.toString() + ',' +tempIntz.toString()));
		return (this.getStateString());
	}
	
	/**
	 * converts Position array to state array.
	 *
	 * @param pArray the array
	 * @return the array list
	 */
	public ArrayList<State> positionArraytoStateArray (ArrayList<Position> pArray)
	{
		ArrayList<State> sArray = new ArrayList<State>();
		for (Iterator<Position> iterator = pArray.iterator(); iterator.hasNext();) 
		{
			Position position = (Position) iterator.next();
			State sTemp = new MazeState(position);
			sTemp.toString();
			sArray.add(sTemp);
		}
		return sArray;
	}
}
