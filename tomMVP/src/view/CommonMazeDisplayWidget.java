package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import mazeGenerators.Maze3d;

public abstract class CommonMazeDisplayWidget extends Canvas 
{
	
	private Maze3d myMaze;
	


	public Maze3d getMyMaze() {
		return myMaze;
	}


	public void setMyMaze(Maze3d myMaze) {
		this.myMaze = myMaze;
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
	public abstract void winGame();
}
