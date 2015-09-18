package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public abstract class CommonController implements Controller {

	View view;
	Model model;
	HashMap<String,Command> map;
	
	public CommonController()
	{
		map = new HashMap<String,Command>();
		initCommands(map);
	}
	
	
	@Override
	public void setView(View view) {
		this.view = view;
		
	}

	@Override
	public void setModel(Model model) {
		this.model = model;
		
	}

	@Override
	public HashMap<String, Command> getMap() {
		return map;
	}
	
	public void setMap(HashMap<String, Command> map)
	{
		this.map = map;
	}
	public abstract void initCommands(HashMap<String, Command> map);


	public void getFilesInDirectory(String path) {
		
		
	}
	

}
