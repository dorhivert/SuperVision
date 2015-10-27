package model;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The Interface ClientHandler.
 */
public interface ClientHandler {

	/**
	 * Handle client.
	 *
	 * @param in The input stream from the client
	 * @param out The output stream to the client
	 * @param Client An object representing the client
	 */
	public void handleClient(InputStream in, OutputStream out);

	/**
	 * Stop the client handler.
	 */
	public void stop();
}
