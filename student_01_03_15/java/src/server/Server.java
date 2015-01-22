package server;

import java.io.IOException;
import java.net.InetSocketAddress;

import server.handlers.*;

import com.sun.net.httpserver.HttpServer;

public class Server implements IServer 
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
		
		server.createContext("/user/login",new LoginHandler());
		server.createContext("/user/register",new RegisterHandler());
		
		server.createContext("/games/list",new ListGamesHandler());
		server.createContext("/games/create",new CreateGamesHandler());
		server.createContext("/games/join",new JoinGamesHandler());
		server.createContext("/games/save",new SaveGamesHandler());
		server.createContext("/games/load",new LoadGamesHandler());

		server.createContext("/game/model",new ModelHandler());
		server.createContext("/game/reset",new ResetHandler());
		server.createContext("/game/commands",new CommandsHandler());
		server.createContext("/game/addAI",new AddAIHandler());
		server.createContext("/game/listAI",new ListAIHandler());
		
		//since all of the moves requests return the same thing, we only need one object for all of the differen requests
		MovesHandler movesHandler = new MovesHandler();
		server.createContext("/moves/sendChat", movesHandler);
		server.createContext("/moves/rollNumber", movesHandler);
		server.createContext("/moves/robPlayer", movesHandler);
		server.createContext("/moves/finishTurn", movesHandler);
		server.createContext("/moves/buyDevCard", movesHandler);
		server.createContext("/moves/Year_of_Plenty", movesHandler);
		server.createContext("/moves/Road_Building", movesHandler);
		server.createContext("/moves/Soldier", movesHandler);
		server.createContext("/moves/Monopoly", movesHandler);
		server.createContext("/moves/Monument", movesHandler);
		server.createContext("/moves/buildRoad", movesHandler);
		server.createContext("/moves/buildSettlement", movesHandler);
		server.createContext("/moves/buildCity", movesHandler);
		server.createContext("/moves/offerTrade", movesHandler);
		server.createContext("/moves/acceptTrade", movesHandler);
		server.createContext("/moves/maritimeTrad", movesHandler);
		server.createContext("/moves/discardCards", movesHandler);
		
		server.createContext("/util/changeLogLevel", new LogHandler());
		
		server.start();
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
