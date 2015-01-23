package server;

public interface IServer 
{
	/**
	 * Get the server's port number.
	 * 
	 * @return the port number
	 */
	int getPortNumber();
	/**
	 * Get the host name.
	 * 
	 * @return the host name
	 */
	String getHost();
	
	/**
	 * Runs the server on a specific port.
	 */
	void run();
}
