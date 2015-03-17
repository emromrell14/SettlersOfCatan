package server.handlers;

import java.util.Iterator;
import java.util.Map;

import models.Game;
import models.Player;
import server.IServer;
import server.User;
import server.JSON.JoinGamesRequest;
import server.JSON.RegisterRequest;

public class JoinGamesHandler  extends Handler
{
	private final int MAX_PLAYERS = 4;
	public JoinGamesHandler(IServer server)
	{
		super.server = server;
	}

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
		Response resp = new Response();
		String urlEncoded;
		String body = req.getBody();
		JoinGamesRequest jgr = JoinGamesRequest.fromJSON(body);
		int id = jgr.getId();
		String color = jgr.getColor();
		
		Iterator it = server.getGames().entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			
			if(id == (int)pair.getKey())
			{
				Game game = (Game)pair.getValue();
				
				// Check game doesn't already have 4 players
				if(game.players().size() >= MAX_PLAYERS)
				{
					break;
				}
				
				// Check color isn't already taken by a different player
				for(Player p : game.players())
				{
					if(p.color().name().equals(color))
					{
						// Check names for case rejoin
						if(p.name().equals(req.getCookie().getPlayerName()))
						{
							return new Response(200,"Player added.");
						}
						else
						{
							return new Response(400,"The player could not be added to the specified game - color taken.");
						}
						 
					}
				}
				// If this point is reached, color was not taken
				return new Response(200,"Player added.");
			}
		}
		// Set fail response
		return new Response(400,"The player could not be added to the specified game.");
	}
	

}
