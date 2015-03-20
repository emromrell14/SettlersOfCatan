package server.handlers;

import server.IServer;
import server.IServerFacade;
import server.ServerFacade;

public class MovesHandler extends Handler
{
	IServerFacade serverFacade = new ServerFacade();
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
		Response res = new Response();
		int gameID = req.getCookie().getGameID();
		int userID = req.getCookie().getPlayerID();
		
		if(gameID == -1 || userID == -1)
		{
			res.setBody("Failed - missing cookie");
			res.setStatusCode(400);
			return res;
		}
		
		serverFacade.parseBody(req.getRequestURI(), req.getBody(),server.getGame(gameID));
		
		res.setStatusCode(200);
		res.setBody(server.getGameModelJSON(0, gameID));
		server.updateVersion(gameID);
		return res;
	}

}
