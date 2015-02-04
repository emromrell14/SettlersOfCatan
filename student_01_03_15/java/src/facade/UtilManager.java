package facade;

import proxy.IProxy;

public class UtilManager
{
	private IProxy mProxy;
	
	/**
	 * Creates a new UtilManager facade that connects the Client to the Proxy
	 * @param proxy 
	 * 
	 * @return New UtilManager object
	 */
	public UtilManager(IProxy proxy)
	{
		mProxy = proxy;
	}
	/**
	 * Sets the server's log level (ALL, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, OFF)
	 * @pre must be one of the levels enumerated above
	 * @post sets the server's log level
	 * @return JSON string
	 */
	public String changeLogLevel(String level) 
	{
		String response;
		String body;
		
		body = "{logLevel:\"" + level + "\"}";
		
		response = mProxy.post("/util/changeLogLevel", body);
		return response;
	}
}
