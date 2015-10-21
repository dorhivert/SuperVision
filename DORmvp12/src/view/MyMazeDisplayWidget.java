package view;

import mazeGenerators.Position;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;

public class MyMazeDisplayWidget extends CommonMazeDisplayWidget 
{
	private GameChar myMegi, myPresent, myFinished;
	private boolean flag = false;
	private Position myTempPosition;

	MyMazeDisplayWidget(Composite composite, int style) 
	{
		super(composite, style);
		myMegi = new GameCharacter();
		myPresent = new GameGoalImage();
		myFinished = new GameFinishGesture();

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
				if((getMyMaze().getStartPosition().getZ()) == levelSelected)
				{
					arg0.gc.setBackground(arg0.display.getSystemColor(SWT.COLOR_GREEN));
					arg0.gc.fillRectangle(getMyMaze().getStartPosition().getX()*cellSize, getMyMaze().getStartPosition().getY()*cellSize, cellSize, cellSize);
				}
				if ((getMyMaze().getCorrentPosition().getZ()) == levelSelected) 
				{

					myMegi.print(arg0, getMyMaze().getCorrentPosition().getX()*cellSize, getMyMaze().getCorrentPosition().getY()*cellSize, cellSize, cellSize);
				}
				if ((getMyMaze().getFinishPosition().getZ()) == levelSelected) 
				{
					myPresent.print(arg0, getMyMaze().getFinishPosition().getX()*cellSize, getMyMaze().getFinishPosition().getY()*cellSize, cellSize, cellSize);
				}

				if (((getMyMaze().getCorrentPosition().getX()) == (getMyMaze().getFinishPosition().getX()))&&((getMyMaze().getCorrentPosition().getY()) == (getMyMaze().getFinishPosition().getY()))&&((getMyMaze().getCorrentPosition().getZ()) == (getMyMaze().getFinishPosition().getZ())))
				{
					myFinished.print(arg0, 0, 0, ((getMyMaze().getMaxX()+1)*cellSize), ((getMyMaze().getMaxY()+1)*cellSize));
				}

				setBackground(arg0.display.getSystemColor(SWT.COLOR_WHITE));
			}
		});
	}

	@Override
	public void moveUp()
	{
		myTempPosition = new Position(getMyMaze().getCorrentPosition());
		flag = getMyMaze().isMoveRealyLegal2(1, myTempPosition);
		if (flag)
		{
			getMyMaze().moveUp();
			redraw();
		}
	}

	@Override
	public void moveDown()
	{
		myTempPosition = new Position(getMyMaze().getCorrentPosition());
		flag = getMyMaze().isMoveRealyLegal2(2, myTempPosition);
		if (flag)
		{
			getMyMaze().moveDown();
			redraw();
		}
	}

	@Override
	public void moveLeft()
	{
		myTempPosition = new Position(getMyMaze().getCorrentPosition());
		flag = getMyMaze().isMoveRealyLegal2(4, myTempPosition);
		if (flag)
		{
			getMyMaze().moveLeft();
			redraw();
		}

	}

	@Override
	public void moveRight()
	{
		myTempPosition = new Position(getMyMaze().getCorrentPosition());
		flag = getMyMaze().isMoveRealyLegal2(3, myTempPosition);
		if (flag)
		{
			getMyMaze().moveRight();
			redraw();
		}
	}

	@Override
	public void moveIn() 
	{
		myTempPosition = new Position(getMyMaze().getCorrentPosition());
		flag = getMyMaze().isMoveRealyLegal2(5, myTempPosition);
		if (flag)
		{
			getMyMaze().moveIn();
			redraw();
		}

	}

	@Override
	public void moveOut() 
	{
		myTempPosition = new Position(getMyMaze().getCorrentPosition());
		flag = getMyMaze().isMoveRealyLegal2(6, myTempPosition);
		if (flag)
		{
			getMyMaze().moveOut();
			
			redraw();
		}

	}




}
