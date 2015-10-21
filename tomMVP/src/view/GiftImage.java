package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class GiftImage implements GameChar{
	
	public GiftImage(){}
	@Override
	public void print(PaintEvent e, int pointX, int pointY, int width, int height) 
	{
		Image i = new Image(null, "./resources/gift.png");
		e.gc.drawImage(i, 0, 0, 1600, 1568, pointX, pointY, width, height);
	}

}
