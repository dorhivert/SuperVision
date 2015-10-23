package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class GameSaveMazeDialog extends Dialog
{

	private String totalValue;
	private boolean verifyData2 = false;

	/**
	 * @param parent
	 */
	public GameSaveMazeDialog(Shell parent)
	{
		super(parent);
	}

	/**
	 * @param parent
	 * @param style
	 */
	public GameSaveMazeDialog(Shell parent, int style)
	{
		super(parent, style);
	}

	/**
	 * Makes the dialog visible.
	 * 
	 * @return
	 */
	public String open()
	{
		Shell parent = getParent();
		final Shell shell =
				new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		shell.setText("Choose Maze Size");

		shell.setLayout(new GridLayout(2, true));

		Label label2 = new Label(shell, SWT.NULL);
		label2.setText("Please enter maze name:");

		final Text text2 = new Text(shell, SWT.SINGLE | SWT.BORDER);

		final Button buttonOK = new Button(shell, SWT.PUSH);
		buttonOK.setText("Ok");
		buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		buttonOK.setEnabled(false);
		Button buttonCancel = new Button(shell, SWT.PUSH);
		buttonCancel.setText("Cancel");

		
		text2.addListener(SWT.Modify, new Listener() 
		{
			public void handleEvent(Event event) 
			{
				try 
				{
					totalValue = new String(text2.getText());
		
					if ((text2.getText() == null) || (text2.getText().length() > 20) || (text2.getText().length() < 2))
					{
						verifyData2 = false;
						buttonOK.setEnabled(false);
					}
					else
					{
						verifyData2 = true;
					}

					if (verifyData2)
						buttonOK.setEnabled(true);
				} 
				catch (Exception e) 
				{
					buttonOK.setEnabled(false);
				}
			}
		});

		buttonOK.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event event)
			{
				shell.dispose();
			}
		});

		buttonCancel.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event event)
			{
				totalValue = null;
				shell.dispose();
			}
		});

		shell.addListener(SWT.Traverse, new Listener() 
		{
			public void handleEvent(Event event)
			{
				if(event.detail == SWT.TRAVERSE_ESCAPE)
					event.doit = false;
			}
		});

		shell.pack();
		shell.open();

		Display display = parent.getDisplay();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		return totalValue;
	}
	
//	public static void main(String[] args)
//	{
//		Shell shell = new Shell();
//		GameSaveMazeDialog dor = new GameSaveMazeDialog(shell);
//		System.out.println(dor.open());
//	}
}
