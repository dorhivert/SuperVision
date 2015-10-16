package view;

import java.io.Closeable;
import java.io.IOException;

import mazeGenerators.Maze3d;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

import solution.Solution;

public class MyGUIView extends BasicWindow implements Closeable
{
	String fileName;
	Text ascii;


	public MyGUIView(String title, int width, int height)
	{
		super(title, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start()
	{
		run();
		
	}

	@Override
	public void writeToConsole(String userInput)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSection(int[][] crossSection)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMazeSize(String name, double mazeSize)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayFileSize(String name, double fileSize) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Maze3d maze)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution s)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayDirectory(String[] path)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCommandMenu()
	{
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void close() throws IOException
	{
		// TODO Auto-generated method stub
		
	}

}
