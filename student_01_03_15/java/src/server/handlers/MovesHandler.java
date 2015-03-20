package server.handlers;

import JSONmodels.ResourceListJSON;
import models.IGame;
import server.IServer;
import server.IServerFacade;
import server.ServerFacade;
import server.JSON.*;

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
		
		serverFacade.setGame(server.getGame(gameID));
		parseBody(req.getRequestURI(), req.getBody());
		
		res.setStatusCode(200);
		res.setBody(server.getGameModelJSON(0, gameID));
		server.updateVersion(gameID);
		return res;
	}
	
	public void parseBody(String url, String jsonBody)
	{
		switch(url)
		{
		case "moves/rollNumber":
			
			break;
		case "moves/sendChat":
			
			break;
		case "moves/robPlayer":
			
			break;
		case "moves/finishTurn":
			
			break;
		case "moves/buyDevCard":
			
			break;
		case "moves/Year_of_Plenty":
			
			break;
		case "moves/Road_Building":
			
			break;
		case "moves/Soldier":
			
			break;
		case "moves/Monopoly":
			
			break;
		case "moves/Monument":
			
			break;
		case "moves/buildRoad":
			
			break;
		case "moves/buildSettlement":
			
			break;
		case "moves/buildCity":
			
			break;
		case "moves/offerTrade":
			
			break;
		case "moves/acceptTrade":
			
			break;
		case "moves/maritimeTrade":
			
			break;
		case "moves/discardCards":
 
			break;
		default:
			System.out.println("ServerFacade - should never get here. url=" + url);
		}
	}
}
