/*
 * 
 */
package mazeGenerators;

// TODO: Auto-generated Javadoc
/**
 * The Interface Maze3dGenerator.
 */
public interface Maze3dGenerator
{

	/**
	 * Generates a maze.
	 *
	 * @param x the width
	 * @param y the height
	 * @param z the depth
	 * @return the maze3d
	 */
	public Maze3d generate(int x, int y, int z);
	
	/**
	 * Measure algorithm time.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the string
	 */
	public String measureAlgorithmTime(int x, int y, int z);
	
	/**
	 * Prints the maze kind.
	 */
	public void printMazeKind();

}
