package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;


/**
 * The Class GameFinishGesture.
 */
public class GameFinishGesture implements GameChar {

	/**
	 * Instantiates a new game finish gesture.
	 */
	public GameFinishGesture() {}
	
	/* (non-Javadoc)
	 * @see view.GameChar#print(org.eclipse.swt.events.PaintEvent, int, int, int, int)
	 */
	@Override
	public void print(PaintEvent e, int pointX, int pointY, int width, int height) 
	{
		Image i = new Image(null, "./resources/finished.jpg");
		e.gc.drawImage(i, 0, 0, 400, 226, pointX, pointY, width, height);
	}

}
