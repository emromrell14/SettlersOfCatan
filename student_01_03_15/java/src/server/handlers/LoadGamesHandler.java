package server.handlers;

import server.IServer;

public class LoadGamesHandler extends Handler
{
	public LoadGamesHandler(IServer server) 
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Load a previously saved game file to restore the state of a game.
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
