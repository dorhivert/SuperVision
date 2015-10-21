package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class GameFinishGesture implements GameChar {

	public GameFinishGesture() {}
	
	@Override
	public void print(PaintEvent e, int pointX, int pointY, int width, int height) 
	{
		Image i = new Image(null, "./resources/finished.jpg");
		e.gc.drawImage(i, 0, 0, 400, 226, pointX, pointY, width, height);
	}

}
