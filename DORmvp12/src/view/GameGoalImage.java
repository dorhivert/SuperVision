package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class GameGoalImage implements GameChar {

	public GameGoalImage() {}
	
	@Override
	public void print(PaintEvent e, int pointX, int pointY, int width, int height) 
	{
		Image i = new Image(null, "./resources/gift.png");
		e.gc.drawImage(i, 0, 0, 1600, 1568, pointX, pointY, width, height);
	}

}
