package server.handlers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import models.Game;
import models.Index;
import models.Player;
import server.IServer;
import server.User;
import server.JSON.JoinGamesRequest;
import server.JSON.RegisterRequest;
import shared.definitions.CatanColor;

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
		String body = req.getBody();
		Response resp = new Response();

		int gameID = req.getCookie().getGameID();
		int userID = req.getCookie().getPlayerID();
		
		if(userID == -1)
		{
			resp.setBody("Failed - missing cookie");
			resp.setStatusCode(400);
			return resp;
		}
		
		JoinGamesRequest jgr = JoinGamesRequest.fromJSON(body);
		int id = jgr.getId();
		String color = jgr.getColor().toUpperCase();
		String playerName = req.getCookie().getPlayerName();
		
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
					System.out.println(p.color().name() + " " + color);
					if(p.color().name().equals(color))
					{
						// Check names if rejoin
						if(p.name().equals(playerName))
						{
							game.setPlayersColor(playerName, color);
							resp = setCookie(game, resp);
							return resp;
						}
						else
						{
							return new Response(400,"The player could not be added to the specified game - color taken.");
						}
						 
					}
				}
				// If this point is reached, color was not taken and player is joining for first time
				try 
				{
					game.addPlayer(new Player(CatanColor.valueOf(color), playerName, 
							new Index(game.players().size()), server.getCurrentUser(playerName).getID()));
					resp = setCookie(game, resp);
					System.out.println("Player added to new game.");
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return resp;
			}
		}
		System.out.println("Player NOTTT added to game.");
		// Set fail response
		return new Response(400,"The player could not be added to the specified game.");
	}
	
	public Response setCookie(Game g, Response resp)
	{
		resp.setCookie("catan.game", g.id()+"");
		resp.setStatusCode(200);
		resp.setBody("Success");
		return resp;
	}
	
}
