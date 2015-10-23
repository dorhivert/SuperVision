package view;

import mazeGenerators.Maze3d;
import mazeGenerators.Position;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public abstract class CommonMazeDisplayWidget extends Canvas 
{
	
	private Maze3d myMaze;
	private int[][] my2dCrossSection;
	public MazeGameBoard myBoard;

	


	public Maze3d getMyMaze() 
	{
		return myMaze;
	}


	public void setMyMaze(Maze3d myMaze) {
		this.myMaze = myMaze;
	}


	public int[][] getMy2dCrossSection() {
		return my2dCrossSection;
	}


	public void setMy2dCrossSection(int[][] my2dCrossSection) {
		this.my2dCrossSection = my2dCrossSection;
	}


	public CommonMazeDisplayWidget(Composite parent, int style)
	{
		super(parent, style);

	}


	public abstract void moveUp();
	public abstract void moveDown(); 
	public abstract void moveLeft(); 
	public abstract void moveRight();
	public abstract void moveOut(); 
	public abstract void moveIn();
	public abstract void updateCurrentPosition(Position p);



}
