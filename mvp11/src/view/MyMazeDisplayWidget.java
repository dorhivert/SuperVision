package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

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
//				setBackground(new Color(getDisplay(), 0, 255, 255));
				int min=0;
				if(getSize().y<getSize().x)
					min=getSize().y;
				else {
					min = getSize().x;
				}
				int levelSelected = 5;
//		        Color c1 = new Color(arg0.display, 50, 50, 200);
//		        arg0.gc.setBackground(c1);
//		        arg0.gc.fillRectangle(10, 15, 90, 60);
//
//		        Color c2 = new Color(arg0.display, 105, 90, 60);
//		        arg0.gc.setBackground(c2);
//		        arg0.gc.fillRectangle(130, 15, 90, 60);
//
//		        Color c3 = new Color(arg0.display, 33, 200, 100);
//		        arg0.gc.setBackground(c3);
//		        arg0.gc.fillRectangle(250, 15, 90, 60);
				
				int cellSize = min / getMyMaze().getMaze().length;
				
				for(int x = 0;x<getMyMaze().getMaxX();x++)
				{
					for (int y = 0; y < getMyMaze().getMaxY(); y++)
					{
						
						{


							startFromX = x*cellSize;
							startFromY = y*cellSize;
							arg0.gc.drawRectangle(startFromX, startFromY, cellSize, cellSize);


							if(getMyMaze().getCellValue(x, y, levelSelected)==0)
							{
								Color bg = new Color(arg0.display,0,255,255);
								arg0.gc.setBackground(bg);
								arg0.gc.fillRectangle(startFromX+1, startFromY+1, cellSize-1, cellSize-1);
								bg.dispose();
			
							}
						}
						
					}
				}
				setBackground(white);

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
