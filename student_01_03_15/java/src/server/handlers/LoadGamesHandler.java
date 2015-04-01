package server.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.IGame;
import JSONmodels.ClientModelJSON;
import server.IServer;
import server.JSON.LoadGamesRequest;

public class LoadGamesHandler extends Handler
{
	public LoadGamesHandler(IServer server) 
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Load a previously saved game file to restore the state of a game.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	@SuppressWarnings("resource")
	@Override
	public Response processRequest(Request req) 
	{
//		System.out.println("server game size before:"+server.getGames().size());
		boolean success = true;
		LoadGamesRequest l = LoadGamesRequest.fromJSON(req.getBody());
		
		try 
		{
			//loads files in the /java/dist/saves folder
			String gameJSON = new Scanner(new File("saves/"+l.getName()+".txt")).useDelimiter("\\Z").next();
			ClientModelJSON c = ClientModelJSON.fromJSON(gameJSON);
			IGame g = c.getGameObject();
			server.loadGame(g);
//			System.out.println("server game size after:"+server.getGames().size());
		} 
		catch (FileNotFoundException e) 
		{
			success = false;
			e.printStackTrace();
		}
		
		return success ? new Response(200,"Success") : new Response(400,"Failed - There was an error loading your file");
	}

}
