package view;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import mazeGenerators.Maze3d;

abstract public class CommonMazeDisplayWidget extends Canvas {

	private Maze3d myMaze;
	
	CommonMazeDisplayWidget(Composite composite,int style)
	{
		super(composite, style);
	}
	
	
	public Maze3d getMyMaze() {
		return myMaze;
	}



	public void setMyMaze(Maze3d myMaze) {
		this.myMaze = myMaze;
	}



	public abstract void moveUp();
	
	public abstract void moveDown();
	
	public abstract void moveLeft();
	
	public abstract void moveRight();
	
	public abstract void moveIn();
	
	public abstract void moveOut();
	
	public abstract void winGame();
	
	

}
