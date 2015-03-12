package server.handlers;

import server.IServer;

public class MovesHandler extends Handler
{
	public MovesHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Depending on the requested move, the move will be executed. All of the moves are handled by one class because
	 * they all require the same response in return.
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
