package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;

public class MyMazeDisplayWidget extends CommonMazeDisplayWidget 
{
	private GameChar myMegi;
	private GiftImage finishGift;

	MyMazeDisplayWidget(Composite composite, int style) 
	{
		super(composite, style);
		myMegi = new GameCharacter();
		finishGift = new GiftImage();
		

		addPaintListener(new PaintListener()
		{
			@Override
			public void paintControl(PaintEvent arg0)
			{
				int startFromX = 0;
				int startFromY = 0;

				int min=0;

				if(getSize().y < getSize().x)
					min=getSize().y+2;
				else 
					min = getSize().x+2;

				int levelSelected = getMyMaze().getCorrentPosition().getZ();

				int cellSize = min / getMyMaze().getMaze().length;

				arg0.gc.setBackground(arg0.display.getSystemColor(SWT.COLOR_BLACK));
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
								arg0.gc.setBackground(arg0.display.getSystemColor(SWT.COLOR_WHITE));
								arg0.gc.fillRectangle(startFromX, startFromY, cellSize, cellSize);
								if (levelSelected < getMyMaze().getMaxZ())
									if(getMyMaze().getCellValue(x, y, levelSelected+1)==0)
									{
										arg0.gc.setBackground(arg0.display.getSystemColor(SWT.COLOR_GRAY));
										arg0.gc.fillRectangle(startFromX, startFromY, cellSize, cellSize);
									}
									else{}
								else{}
								if (levelSelected > 0)
									if(getMyMaze().getCellValue(x, y, levelSelected-1)==0)
									{
										arg0.gc.setBackground(arg0.display.getSystemColor(SWT.COLOR_GRAY));
										arg0.gc.fillRectangle(startFromX, startFromY, cellSize, cellSize);
									}
									else{}
								else{}
								if ((levelSelected < getMyMaze().getMaxZ())&&(levelSelected > 0))
									if((getMyMaze().getCellValue(x, y, levelSelected-1)==0)&&(getMyMaze().getCellValue(x, y, levelSelected+1)==0))
									{
										arg0.gc.setBackground(arg0.display.getSystemColor(SWT.COLOR_GRAY));
										arg0.gc.fillRectangle(startFromX, startFromY, cellSize, cellSize);
									}
									else{}
								else{}
							}
							else
							{
								arg0.gc.setBackground(arg0.display.getSystemColor(SWT.COLOR_BLACK));
								arg0.gc.fillRectangle(startFromX+1, startFromY+1, cellSize-1, cellSize-1);
							}
						}
					}
				}
				myMegi.print(arg0, getMyMaze().getCorrentPosition().getX()*cellSize, getMyMaze().getCorrentPosition().getY()*cellSize, cellSize, cellSize);
				finishGift.print(arg0,
						getMyMaze().getFinishPosition().getX()*cellSize,
						getMyMaze().getFinishPosition().getY()*cellSize,
						cellSize, cellSize);
				setBackground(arg0.display.getSystemColor(SWT.COLOR_WHITE));
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
