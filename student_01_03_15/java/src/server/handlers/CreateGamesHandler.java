package server.handlers;

import server.IServer;

public class CreateGamesHandler  extends Handler
{
	public CreateGamesHandler(IServer server)
	{
		super.server = server; 
	}

	/**
	 * @pre Request != null
	 * @post Creates a new game.
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
