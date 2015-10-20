package view;

import org.eclipse.swt.widgets.Composite;

public class MyMazeDisplayWidget extends CommonMazeDisplayWidget {

	
	
	MyMazeDisplayWidget(Composite composite, int style) {
		super(composite, style);
	}

	@Override
	public void moveUp() {
		getMyMaze().moveUp();
		
	}

	@Override
	public void moveDown() {
		getMyMaze().moveDown();
		
	}

	@Override
	public void moveLeft() {
		getMyMaze().moveLeft();
		
	}

	@Override
	public void moveRight() {
		getMyMaze().moveRight();
		
	}

	@Override
	public void moveIn() {
		getMyMaze().moveIn();
		
	}

	@Override
	public void moveOut() {
		getMyMaze().moveOut();
		
	}

	@Override
	public void winGame() {
		// TODO Auto-generated method stub
		
	}
	
	

}
