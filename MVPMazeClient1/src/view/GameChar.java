package view;

import org.eclipse.swt.events.PaintEvent;


/**
 * The Interface GameChar.
 */
public interface GameChar 
{
	
	/**
	 * Prints the.
	 *
	 * @param e the e
	 * @param pointX the point x
	 * @param pointY the point y
	 * @param width the width
	 * @param height the height
	 */
	public void print(PaintEvent e, int pointX, int pointY, int width, int height);
}
