package controller;

import java.util.HashMap;

import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonController.
 */
public abstract class CommonController implements Controller
{
	
	/** The view. */
	View view;
	
	/** The model. */
	Model model;
	
	/** The map. */
	HashMap<String, Command> map;
	
	/**
	 * Instantiates a new common controller.
	 */
	public CommonController()
	{
		map = new HashMap<String, Command>();
		this.initCommands(map);
	}

	/* (non-Javadoc)
	 * @see controller.Controller#setView(view.View)
	 */
	@Override
	public void setView(View view) 
	{
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see controller.Controller#setModel(model.Model)
	 */
	@Override
	public void setModel(Model model)
	{
		this.model = model;

	}

	/* (non-Javadoc)
	 * @see controller.Controller#getMap()
	 */
	@Override
	public HashMap<String, Command> getMap()
	{
		return this.map;
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the map
	 */
	public void setMap (HashMap<String, Command> map) 
	{
		this.map = map;
	}
	
	/**
	 * Initializes the commands.
	 *
	 * @param map the map
	 */
	public abstract void initCommands(HashMap<String, Command> map);
	

}
