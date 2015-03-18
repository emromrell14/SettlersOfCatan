package server.handlers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.IGame;
import models.Player;
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
		Response resp = new Response();
		
		if(req.getCookie().getPlayerID() == -1)
		{
			// Set fail response
			resp.setStatusCode(400);
			resp.setBody("Failed to get games list - user cookie is not set.");
			return resp;
		}
		
		StringBuilder responseBody = new StringBuilder();
		@SuppressWarnings("rawtypes")
		Iterator it = server.getGames().entrySet().iterator();
		
		responseBody.append("[");
		while(it.hasNext())
		{
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
			responseBody.append(getGameListJSON(pair));
			if(it.hasNext())
			{
				responseBody.append(",");
			}
		}
		responseBody.append("]");
		
		resp.setStatusCode(200);
		resp.setBody(responseBody.toString());
		return resp;
	}

	private String getGameListJSON(@SuppressWarnings("rawtypes") Entry pair) 
	{
		StringBuilder json = new StringBuilder();
		IGame game = (IGame)pair.getValue();
		
		json.append("{");
		json.append("\"title\": \"" + game.name() + "\",");
		json.append("\"id\": " + game.id()+ ",");
		json.append("\"players\": [");
		json.append(getPlayerJSON(game.players()));
		json.append("]");
		json.append("}");
		
		return json.toString();
	}

	private String getPlayerJSON(List<Player> players) 
	{
		StringBuilder json = new StringBuilder();
		
		for(int i = 0; i < players.size(); ++i)
		{
			Player p = players.get(i);
			json.append("{");
			json.append("\"color\": \"" + p.color() + "\",");
			json.append("\"name\": \"" + p.name() + "\",");
			json.append("\"id\": " + p.playerID());
			json.append("}");
			if(i != players.size()-1)
			{
				json.append(",");
			}
		}
		
		return json.toString();
	}
}
