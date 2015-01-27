package server.handlers;

public class JoinGamesHandler  extends Handler
{
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
