package view;

import org.eclipse.swt.events.PaintEvent;

public interface GameChar 
{
	public void print(PaintEvent e, int pointX, int pointY, int width, int height);
}
