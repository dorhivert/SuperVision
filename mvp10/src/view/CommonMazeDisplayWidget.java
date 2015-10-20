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
	public Color green, red, black, yellow, white;
	


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
		white = new Color(getDisplay(),240,240,240);
		yellow = new Color(getDisplay(),255,255,0);
	}
}
