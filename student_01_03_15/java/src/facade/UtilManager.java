package facade;

public class UtilManager extends MasterManager
{
	/**
	 * Creates a new UtilManager facade that connects the Client to the Proxy
	 * 
	 * @return New UtilManager object
	 */
	public UtilManager()
	{
		
	}
	/**
	 * Sets the server's log level (ALL, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, OFF)
	 * @pre must be one of the levels enumerated above
	 * @post sets the server's log level
	 * @return JSON string
	 */
	public String changeLogLevel() 
	{
		return null;
	}
}
