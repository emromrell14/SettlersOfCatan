package server.handlers;

import server.IServer;

public class ListGamesHandler extends Handler
{
	public ListGamesHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Get a list of all games in progress.
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
