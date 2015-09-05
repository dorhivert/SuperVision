package view;

import java.io.BufferedReader;
import java.io.FileReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

import controller.Controller;

public class MyAsciiArtMaker extends BasicWindow
{
	String fileName;
	Text ascii;
	Controller controller;

	public void display(String fileName)
	{
		try 
		{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line;
			ascii.setText("");
			while ((line = in.readLine()) != null)
			{
				ascii.append(line + "\n");
			}
			System.out.println(ascii);
			in.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	public MyAsciiArtMaker(String title, int width, int height) 
	{
		super(title, width, height);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	void InitWidgets() 
	{
		shell.setLayout(new GridLayout(2, false));


		Button open = new Button(shell, SWT.PUSH);
		open.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		open.setText("open img file");

		open.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				fd.setText("open");
				fd.setFilterPath("");
				String[] filterExt = { "*.gif", "*.jpg", ".png", ".bmp", "*.*" };
				fd.setFilterExtensions(filterExt);
				fileName = fd.open();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});


		ascii = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		ascii.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));

		Button convert = new Button(shell, SWT.PUSH);
		convert.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
		convert.setText("convert to ascii art");
		convert.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				controller.convert(fileName);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});




	}
}
