package view;

import java.text.DecimalFormat;

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


public class GamePropertiesDialog extends Dialog {
	Double value;
	String stringValue;
	String tempValue;
	int numberOfThreads;
	String numberOfThreadStringi;
	String myInterface;
	String generationAlgorithm;
	String solveAlgorithm;
	boolean flag = true;
	boolean verifyData1 = false;
	boolean verifyData2 = false;
	boolean verifyData3 = false;
	boolean verifyData4 = false;


	/**
	 * @param parent
	 */
	public GamePropertiesDialog(Shell parent) {
		super(parent);
	}

	/**
	 * @param parent
	 * @param style
	 */
	public GamePropertiesDialog(Shell parent, int style) {
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
		shell.setText("Properties");

		shell.setLayout(new GridLayout(2, true));

		Label label = new Label(shell, SWT.NULL);
		label.setText("Select number of threads:");
		final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);

		Label label2 = new Label(shell, SWT.NULL);
		label2.setText("Select interface:\n <gui/cli>");
		final Text text2 = new Text(shell, SWT.SINGLE | SWT.BORDER);

		Label label3 = new Label(shell, SWT.NULL);
		label3.setText("Select Maze Generation Algorithm:\n <simple/dfs>");
		final Text text3 = new Text(shell, SWT.SINGLE | SWT.BORDER);

		Label label4 = new Label(shell, SWT.NULL);
		label4.setText("Select Maze Solve Algorithm:\n <bfs/astarman/astarair>");
		final Text text4 = new Text(shell, SWT.SINGLE | SWT.BORDER);

		final Button buttonOK = new Button(shell, SWT.PUSH);
		buttonOK.setText("Ok");
		buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		Button buttonCancel = new Button(shell, SWT.PUSH);
		buttonCancel.setText("Cancel");

		text.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				try 
				{
					DecimalFormat df = new DecimalFormat("#.##");
					value = new Double(text.getText());
					if (value>0 || value<80000)
					{
						numberOfThreadStringi = new String(df.format(value));
						verifyData1 = true;
						stringValue = numberOfThreadStringi+" ";
						if (verifyData1 && verifyData2 && verifyData3 && verifyData4)
						{
							buttonOK.setEnabled(true);
						}
					}
				} 
				catch (Exception e) 
				{
					buttonOK.setEnabled(false);
				}
			}
		});

		text2.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				try 
				{
					myInterface = new String(text2.getText());
					if (myInterface.equals("cli") || myInterface.equals("gui"))
					{
						verifyData2 = true;
						stringValue = stringValue + myInterface	+" ";
						if (verifyData1 && verifyData2 && verifyData3 && verifyData4)
						{
							buttonOK.setEnabled(true);
						}
					}
				}

				catch (Exception e) 
				{
					buttonOK.setEnabled(false);
				}
			}
		});

		text3.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				try 
				{
					generationAlgorithm = new String(text3.getText());
					if (generationAlgorithm.equals("simple") || generationAlgorithm.equals("dfs"))
					{
						verifyData3 = true;
						stringValue = stringValue + generationAlgorithm	+" ";
						if (verifyData1 && verifyData2 && verifyData3 && verifyData4)
						{
							buttonOK.setEnabled(true);
						}
					}
				}
				catch (Exception e) 
				{
					buttonOK.setEnabled(false);
				}
			}
		});
		
		text4.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				try 
				{
					solveAlgorithm = new String(text4.getText());
					if (solveAlgorithm.equals("bfs") || solveAlgorithm.equals("astarman")|| solveAlgorithm.equals("astarair"))
					{
						verifyData4 = true;
						stringValue = stringValue + solveAlgorithm;
						if (verifyData1 && verifyData2 && verifyData3 && verifyData4)
						{
							buttonOK.setEnabled(true);
						}
					}
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

		buttonCancel.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				DecimalFormat df = new DecimalFormat("#.##");
				value = new Double(5.0);
				stringValue = new String("unNamed Maze");
				tempValue = new String(df.format(value));

				stringValue = stringValue+" "+tempValue;
				shell.dispose();
			}
		});

		shell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event event) {
				if(event.detail == SWT.TRAVERSE_ESCAPE)
					event.doit = false;
			}
		});

		text.setText("");
		shell.pack();
		shell.open();

		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		
		return stringValue;
	}

//	public static void main(String[] args) {
//		Shell shell = new Shell();
//		GamePropertiesDialog dialog = new GamePropertiesDialog(shell);
//		System.out.println(dialog.open());
//	}
}
