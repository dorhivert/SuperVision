package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;


public class GameCharacter implements GameChar
{
	/**
	 * Instantiates a new game character.
	 */
	public GameCharacter() {}
	
	@Override
	public void print(PaintEvent e, int pointX, int pointY, int width, int height) 
	{
		Image i = new Image(null, "./resources/pic.png");
		e.gc.drawImage(i, 0, 0, 256, 256, pointX, pointY, width, height);
	}
	
}
