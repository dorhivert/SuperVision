package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;



/**
 * The Class GameCharacter.
 */
public class GameCharacter implements GameChar
{
	/**
	 * Instantiates a new game character.
	 */
	public GameCharacter() {}
	
	/* (non-Javadoc)
	 * @see view.GameChar#print(org.eclipse.swt.events.PaintEvent, int, int, int, int)
	 */
	@Override
	public void print(PaintEvent e, int pointX, int pointY, int width, int height) 
	{
		Image i = new Image(null, "./resources/pic.png");
		e.gc.drawImage(i, 0, 0, 256, 256, pointX, pointY, width, height);
	}
	
}
