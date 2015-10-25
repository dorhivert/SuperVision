/*
 * 
 */
package mazeGenerators;

import java.io.Serializable;

/**
 * The Class Position.
 */
public class Position implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1607482662205262265L;

	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The z. */
	private int z;

	/**
	 * CTOR
	 * Instantiates a new position.
	 *
	 * @param myX the my x
	 * @param myY the my y
	 * @param myZ the my z
	 */
	public Position(int myX, int myY, int myZ)
	{
		this.x = myX;
		this.y = myY;
		this.z = myZ;
	}

	/**
	 * default CTOR.
	 */
	public Position() 
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	/**
	 * copy CTOR.
	 *
	 * @param p the p
	 */
	public Position(Position p)
	{
		this.x = p.getX();
		this.y = p.getY();
		this.z = p.getZ();
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * returns String from this Position.
	 *
	 * @return the string
	 */
	public String toString ()
	{
		Integer tempIntx = new Integer(this.x);
		Integer tempInty = new Integer(this.y);
		Integer tempIntz = new Integer(this.z);
		return (tempIntx.toString() + ',' + tempInty.toString() + ',' +tempIntz.toString());
	}
	
	/**
	 * Sets the position.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	protected void setPosition(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Copy position.
	 *
	 * @param p the position
	 */
	public void copyPosition(Position p)
	{
		this.x = p.getX();
		this.y = p.getY();
		this.z = p.getZ();
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() 
	{
		return y;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * Gets the z.
	 *
	 * @return the z
	 */
	public int getZ()
	{
		return z;
	}
	
	/**
	 * Sets the z.
	 *
	 * @param z the new z
	 */
	public void setZ(int z) 
	{
		this.z = z;
	}
	
	/**
	 * Checks if position p is equal to this position.
	 *
	 * @param p the p
	 * @return true, if is equal
	 */
	protected boolean isEqual(Position p) 
	{
		if ((this.x == p.x) && (this.y == p.y) && (this.z == p.z))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object p) 
	{
		if ((this.x == ((Position)p).x) && (this.y == ((Position)p).y) && (this.z == ((Position)p).y))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * makes moves by numbers.
	 *
	 * @param move the move
	 */
	protected void genericDoubleMove (int move)
	{
		if (move == 1) //up
		{
			y++;
			y++;
		}
		if (move == 2) //down
		{
			y--;
			y--;
		}
		if (move == 3) //right
		{
			x++;
			x++;
		}
		if (move == 4) //left
		{
			x--;
			x--;
		}
		if (move == 5) // in
		{
			z++;
			z++;
		}
		if (move == 6) // out
		{
			z--;
			z--;
		}
	}
}
