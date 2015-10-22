package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SavedMazeDialog extends Dialog{

	public SavedMazeDialog(Shell parent) 
	{
		super(parent);
	}
	public SavedMazeDialog(Shell parent, int style)
	{
		super(parent,style);
	}
	public String open()	
	{
		Shell parent = getParent();
		final Shell shell =
				new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		shell.setText("Please enter the name of the maze:");
		
		shell.setLayout(new GridLayout(2, true));
		shell.setSize(400, 130);
		
		Label label = new Label(shell, SWT.NULL);
		label.setText("Please enter maze name:");

		final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);
		text.setMessage(getText());

		final Button buttonOK = new Button(shell, SWT.PUSH);
		buttonOK.setText("Ok");
		buttonOK.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		Button buttonCancel = new Button(shell, SWT.PUSH);
		buttonCancel.setText("Cancel");
		buttonCancel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		shell.open();
		String mazeName = text.getMessage();
		System.out.println(mazeName);
		
		buttonOK.addSelectionListener(new SelectionListener() {
			
			
			
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				String newMazeName = mazeName;
				System.out.println(newMazeName);
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		buttonCancel.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return mazeName;

	}

}
