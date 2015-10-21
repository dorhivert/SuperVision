package view;

import mazeGenerators.Maze3d;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public abstract class CommonMazeDisplayWidget extends Canvas 
{
	
	private Maze3d myMaze;
	private int[][] my2dCrossSection;
	public MazeGameBoard myBoard;
	public Color green, red, black, yellow, white, lightBlack, superLightBlack;
	


	public Maze3d getMyMaze() {
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
		green = new Color(getDisplay(),0,255,0);
		red = new Color(getDisplay(),255,0,0);
		black = new Color(getDisplay(),0,0,0);
		superLightBlack = new Color(getDisplay(),200,200,200);
		lightBlack = new Color(getDisplay(),130,130,130);
		white = new Color(getDisplay(),255,255,255);
		yellow = new Color(getDisplay(),255,255,0);
	}


	public abstract void moveUp();
	public abstract void moveDown(); 
	public abstract void moveLeft(); 
	public abstract void moveRight();
	public abstract void moveOut(); 
	public abstract void moveIn();
	public abstract void winGame();
}
