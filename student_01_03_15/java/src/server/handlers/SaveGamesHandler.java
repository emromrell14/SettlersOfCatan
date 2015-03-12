package server.handlers;

import server.IServer;

public class SaveGamesHandler extends Handler
{
	public SaveGamesHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Saves the current state of the specified game to a file.
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
