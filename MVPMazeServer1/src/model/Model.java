package model;

import java.io.IOException;

/**
 * The Interface Model.
 */
public interface Model 
{


	/**
	 * Open.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void open() throws IOException;
	
	/**
	 * Close.
	 *
	 * @throws Exception the exception
	 */
	public void close() throws Exception;
	
}
