package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow extends Observable implements Runnable, View
{

	protected Display display;
	protected Shell shell;
	
	abstract void InitWidgets();

	public BasicWindow(String title, int width,int height)
	{
		display = new Display();
		shell = new Shell(display);
		shell.setLocation(390, 50);
		shell.setSize(width, height);
		shell.setText(title);
	}

	@Override
	public void run() 
	{
		InitWidgets();
		shell.open();

		while(!shell.isDisposed())
		{ 
			if(!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		display.dispose();
	}
}
