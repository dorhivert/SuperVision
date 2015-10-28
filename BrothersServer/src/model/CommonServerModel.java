package model;

import java.io.IOException;
import java.util.Observable;


/**
 * The Class CommonModel.
 */
public abstract class CommonServerModel extends Observable implements Model
{

	/* (non-Javadoc)
	 * @see model.Model#open()
	 */
	@Override
	public abstract void open() throws IOException;

	/* (non-Javadoc)
	 * @see model.Model#close()
	 */
	@Override
	public abstract void close() throws Exception;

}
