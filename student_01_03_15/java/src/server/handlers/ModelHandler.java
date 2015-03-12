package server.handlers;

import server.IServer;

public class ModelHandler  extends Handler
{
	public ModelHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Returns the current state of the game in JSON format.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	@Override
	public Response processRequest(Request req)
	{
		return null;
	}

}
