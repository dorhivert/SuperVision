/*
 * 
 */
package solution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Class Solution.
 */
public class Solution implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1368969165588483942L;
	/** The solution list. */
	private ArrayList<String> solutionList;


	/**
	 * default C'TOR.
	 */
	public Solution()
	{
		super();
		this.solutionList = new ArrayList<String>();
	}

	/**
	 * Gets the solution list.
	 *
	 * @return the solution list
	 */
	public ArrayList<String> getSolutionList()
	{
		return solutionList;
	}

	/**
	 * reverses the order of the array.
	 */
	public void sortback()
	{
		Collections.reverse(solutionList);

	}

	/**
	 * Prints the solution list.
	 */
	public void print()
	{
		if (solutionList.size() == 0)
		{
			System.out.println("Start point = Finish point, no rout calculated");
		}
		else
		{
			int i = 0;
			int k = 0;
			if (!solutionList.isEmpty()) 
			{
				for (String string : solutionList)
				{
					if	(k == 9)
					{
						System.out.println();
						k = 0;
					}
					if(i==solutionList.size()-1)
					{
						System.out.print(string);
						continue;
					}
					System.out.print(string + " -> ");
					i++;
					k++;
				}
			}
			System.out.println("\nNodes to solution: " + (i+1));
		}
	}
}