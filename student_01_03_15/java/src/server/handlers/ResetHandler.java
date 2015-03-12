package server.handlers;

import server.IServer;

public class ResetHandler extends Handler
{
	public ResetHandler(IServer server) 
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Clears out the command history of the current game.
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
