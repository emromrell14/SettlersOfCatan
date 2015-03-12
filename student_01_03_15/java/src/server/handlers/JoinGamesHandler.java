package server.handlers;

import server.IServer;

public class JoinGamesHandler  extends Handler
{
	public JoinGamesHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Adds (or re-adds) the player to the specified game.
	 * @post Sets their catan.game HTTP cookie.
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
