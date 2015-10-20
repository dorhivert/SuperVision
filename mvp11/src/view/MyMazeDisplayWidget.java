package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MyMazeDisplayWidget extends CommonMazeDisplayWidget {

	private Color black,white;
	
	MyMazeDisplayWidget(Composite composite, int style) {
		super(composite, style);
		black = new Color(getDisplay(), 0, 0, 0);
		white = new Color(getDisplay(), 255, 255 ,255);
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent arg0) {

				int startFromX = 0;
				int startFromY = 0;
				setBackground(white);
				int min=0;
				if(getSize().y<getSize().x)
					min=getSize().y;
				else {
					min = getSize().x;
				}
				
				int cellSize = min / getMyMaze().getMaze().length;
				
				for(int x = 0;x<getMyMaze().getMaxX();x++)
				{
					for (int y = 0; y < getMyMaze().getMaxY(); y++)
					{
						for (int z = 0; z < getMyMaze().getMaxZ(); z++)
						{


							startFromX = x*cellSize;
							startFromY = y*cellSize;
							arg0.gc.drawRectangle(startFromX, startFromY, cellSize, cellSize);

//							if(getMyMaze().getCellValue(x, y, z)==1)
//							{
//								arg0.gc.fillRectangle(startFromX, startFromY, cellSize, cellSize);
//							}
						}
						
					}
				}
				
			}
		});
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
