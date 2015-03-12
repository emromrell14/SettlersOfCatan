package server.handlers;

import server.IServer;

public class ListAIHandler  extends Handler
{
	public ListAIHandler(IServer server) 
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Returns a list of supported AI player types.
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
