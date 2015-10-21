package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;

public class MyMazeDisplayWidget extends CommonMazeDisplayWidget 
{
	private GameCharacter myMegi;


	MyMazeDisplayWidget(Composite composite, int style) 
	{
		super(composite, style);
		myMegi = new GameCharacter();

		addPaintListener(new PaintListener()
		{

			@Override
			public void paintControl(PaintEvent arg0)
			{

				int startFromX = 0;
				int startFromY = 0;
				setBackground(white);
				//				setBackground(new Color(getDisplay(), 0, 255, 255));
				int min=0;

				if(getSize().y < getSize().x)
					min=getSize().y+2;
				else 
					min = getSize().x+2;

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


				arg0.gc.setBackground(black);
				arg0.gc.fillRectangle(0, 0, cellSize-1, cellSize-1);

				for(int x = 0;x<getMyMaze().getMaxX()+1;x++)
				{
					for (int y = 0; y < getMyMaze().getMaxY()+1; y++)
					{
						{
							startFromX = x*cellSize;
							startFromY = y*cellSize;
							arg0.gc.drawRectangle(startFromX, startFromY, cellSize, cellSize);


							if(getMyMaze().getCellValue(x, y, levelSelected)==0)
							{
								arg0.gc.setBackground(white);
								arg0.gc.fillRectangle(startFromX, startFromY, cellSize, cellSize);
								if(getMyMaze().getCellValue(x, y, levelSelected+1)==0)
								{
			
									arg0.gc.setBackground(superLightBlack);
									arg0.gc.fillRectangle(startFromX, startFromY, cellSize, cellSize);
				
								}
								if(getMyMaze().getCellValue(x, y, levelSelected-1)==0)
								{
									
									arg0.gc.setBackground(superLightBlack);
									arg0.gc.fillRectangle(startFromX, startFromY, cellSize, cellSize);
									
								}
								if((getMyMaze().getCellValue(x, y, levelSelected-1)==0)&&(getMyMaze().getCellValue(x, y, levelSelected+1)==0))
								{
									
									arg0.gc.setBackground(superLightBlack);
									arg0.gc.fillRectangle(startFromX, startFromY, cellSize, cellSize);
									
								}
							}
							else
							{
								arg0.gc.setBackground(black);
								arg0.gc.fillRectangle(startFromX+1, startFromY+1, cellSize-1, cellSize-1);
							}
						}
					}
				}
				myMegi.print(arg0, 0, 0, cellSize, cellSize);
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
