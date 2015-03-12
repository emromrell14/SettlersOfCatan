package server.handlers;

import server.IServer;

public class LoginHandler extends Handler
{
	public LoginHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Validates the player's credentials.
	 * @post Logs player in to the server.
	 * 
	 * @param req	The Request object from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	@Override
	public Response processRequest(Request req) 
	{
		return null;
	}

}
