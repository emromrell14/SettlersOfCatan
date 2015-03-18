package server.handlers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import models.Game;
import server.IServer;
import server.User;
import server.JSON.CreateGamesRequest;
import server.JSON.LoginRequest;

public class CreateGamesHandler  extends Handler
{
	private String gameName;
	private int gameId;
	
	public CreateGamesHandler(IServer server)
	{
		super.server = server; 
	}

	/**
	 * @pre Request != null
	 * @post Creates a new game.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	@Override
	public Response processRequest(Request req) 
	{
		String body = req.getBody();
		CreateGamesRequest gamesRequest = CreateGamesRequest.fromJSON(body);
		gameName = gamesRequest.getName();	
		
		// This could lead to synchronization problems. Test creating 2 games at the same time
		gameId = server.getGames().size();

		// Check for games with same name
		if(existsDuplicateName(gameName))
		{
			return new Response(400,"Game name already taken.");
		}
		
		String respBody = buildBody();
		server.createGame(gameName, gameId, gamesRequest.isRandomTiles(), gamesRequest.isRandomNumbers(), gamesRequest.isRandomPorts());
		return new Response(200, respBody);
	}
	
	public String buildBody()
	{
		String body;
		String id = gameId + "";
		body = "{\"title\": \"" + gameName + "\", \"id\": " + id + ", \"players\": [ {}, {}, {}, {} ] }";
		return body;
	}

	public boolean existsDuplicateName(String name)
	{
		Iterator it = server.getGames().entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			if(name.equals(((Game) pair.getValue()).name()))
			{
				return true;
			}
		}
		return false;
	}
}
