package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


// TODO: Auto-generated Javadoc
/**
 * The Class BasicWindow.
 */
public abstract class BasicWindow extends Observable implements Runnable, View
{

	/** The display. */
	protected Display display;
	
	/** The shell. */
	protected Shell shell;
	
	/**
	 * Inits the widgets.
	 */
	abstract void InitWidgets();

	/**
	 * Instantiates a new basic window.
	 *
	 * @param title the title
	 * @param width the width
	 * @param height the height
	 */
	public BasicWindow(String title, int width,int height)
	{
		display = new Display();
		shell = new Shell(display);
		shell.setLocation(390, 50);
		shell.setSize(width, height);
		shell.setText(title);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
