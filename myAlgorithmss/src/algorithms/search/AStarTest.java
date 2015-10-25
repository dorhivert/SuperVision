/**
 * 
 */
package algorithms.search;

import static org.junit.Assert.assertEquals;
import heuristics.MazeEuclideanDistance;
import mazeGenerators.Maze3d;
import mazeGenerators.MyMaze3dGenerator;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class AStarTest.
 *
 * @author DorDellpc
 */
public class AStarTest
{
	
	/**
	 * Test.
	 */
	@Test
	public void test()
	{
		MazeEuclideanDistance h = new MazeEuclideanDistance();
		AStar algo = new AStar(h);
		assertEquals(null, algo.search(null));

		algo = new AStar(null);

		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze = mg.generate(5, 5, 5);
		SearchableMaze s = new SearchableMaze(maze);
		assertEquals(null, algo.search(s));
	}
}
