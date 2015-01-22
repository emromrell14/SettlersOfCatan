package server;

import java.io.IOException;
import java.net.InetSocketAddress;

import server.handlers.AddAIHandler;
import server.handlers.CommandsHandler;
import server.handlers.CreateGamesHandler;
import server.handlers.JoinGamesHandler;
import server.handlers.ListAIHandler;
import server.handlers.ListGamesHandler;
import server.handlers.LoadGamesHandler;
import server.handlers.LogHandler;
import server.handlers.LoginHandler;
import server.handlers.ModelHandler;
import server.handlers.MovesHandler;
import server.handlers.RegisterHandler;
import server.handlers.ResetHandler;
import server.handlers.SaveGamesHandler;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MockServer implements IServer
{
	private HttpServer server;
	private static int portNumber;
	private String host = "localhost";
	
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			portNumber = 8898;
		}
		else
		{
			portNumber = Integer.parseInt(args[0]);
		}
		new Server().run();
	}
	
	@Override
	public void run()
	{
		try 
		{
			server = HttpServer.create(new InetSocketAddress(portNumber),10);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		server.setExecutor(null);
		
		server.createContext("/user/login",loginHandler());
		server.createContext("/user/register",registerHandler());
		
		server.createContext("/games/list",listGamesHandler());
		server.createContext("/games/create",createGamesHandler());
		server.createContext("/games/join",joinGamesHandler());
		server.createContext("/games/save",saveGamesHandler());
		server.createContext("/games/load",loadGamesHandler());

		server.createContext("/game/model",modelHandler());
		server.createContext("/game/reset",resetHandler());
		server.createContext("/game/commands",commandsHandler());
		server.createContext("/game/addAI",addAIHandler());
		server.createContext("/game/listAI",listAIHandler());
		
		//since all of the moves requests return the same thing, we only need one object for all of the different requests
		server.createContext("/moves/sendChat", movesHandler());
		server.createContext("/moves/rollNumber", movesHandler());
		server.createContext("/moves/robPlayer", movesHandler());
		server.createContext("/moves/finishTurn", movesHandler());
		server.createContext("/moves/buyDevCard", movesHandler());
		server.createContext("/moves/Year_of_Plenty", movesHandler());
		server.createContext("/moves/Road_Building", movesHandler());
		server.createContext("/moves/Soldier", movesHandler());
		server.createContext("/moves/Monopoly", movesHandler());
		server.createContext("/moves/Monument", movesHandler());
		server.createContext("/moves/buildRoad", movesHandler());
		server.createContext("/moves/buildSettlement", movesHandler());
		server.createContext("/moves/buildCity", movesHandler());
		server.createContext("/moves/offerTrade", movesHandler());
		server.createContext("/moves/acceptTrade", movesHandler());
		server.createContext("/moves/maritimeTrad", movesHandler());
		server.createContext("/moves/discardCards", movesHandler());
		
		server.createContext("/util/changeLogLevel", logHandler());
		
		server.start();
	}

	private HttpHandler logHandler() 
	{
		return null;
	}

	private HttpHandler movesHandler() 
	{
		return null;
	}

	private HttpHandler listAIHandler() 
	{
		return null;
	}

	private HttpHandler addAIHandler() 
	{
		return null;
	}

	private HttpHandler commandsHandler() 
	{
		return null;
	}

	private HttpHandler resetHandler() 
	{
		return null;
	}

	private HttpHandler modelHandler() 
	{
		return null;
	}

	private HttpHandler loadGamesHandler() 
	{
		return null;
	}

	private HttpHandler saveGamesHandler() 
	{
		return null;
	}

	private HttpHandler joinGamesHandler() 
	{
		return null;
	}

	private HttpHandler createGamesHandler() 
	{
		return null;
	}

	private HttpHandler listGamesHandler() 
	{
		return null;
	}

	private HttpHandler registerHandler() 
	{
		return null;
	}

	private HttpHandler loginHandler() 
	{
		return null;
	}

	@Override
	public int getPortNumber() 
	{
		return portNumber;
	}

	@Override
	public String getHost() 
	{
		return host;
	}
}
