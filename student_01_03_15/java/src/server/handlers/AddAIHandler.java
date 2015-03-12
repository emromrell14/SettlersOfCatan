package server.handlers;

import server.IServer;

public class AddAIHandler  extends Handler
{
	public AddAIHandler(IServer server) 
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Adds an AI player to the current game.
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
