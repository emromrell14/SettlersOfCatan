package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import models.IGame;
import server.handlers.*;

import com.sun.net.httpserver.HttpServer;

public class Server implements IServer 
{
	private HttpServer server;
	private static int portNumber;
	private String host = "localhost";
	private Map<Integer,IGame> games = new HashMap<Integer,IGame>();
	private Map<Integer,IUser> users = new HashMap<Integer,IUser>();
	
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
		
		server.createContext("/user/login",new LoginHandler(this));
		server.createContext("/user/register",new RegisterHandler(this));
		
		server.createContext("/games/list",new ListGamesHandler(this));
		server.createContext("/games/create",new CreateGamesHandler(this));
		server.createContext("/games/join",new JoinGamesHandler(this));
		server.createContext("/games/save",new SaveGamesHandler(this));
		server.createContext("/games/load",new LoadGamesHandler(this));

		server.createContext("/game/model",new ModelHandler(this));
		server.createContext("/game/reset",new ResetHandler(this));
		server.createContext("/game/commands",new CommandsHandler(this));
		server.createContext("/game/addAI",new AddAIHandler(this));
		server.createContext("/game/listAI",new ListAIHandler(this));
		
		//since all of the moves requests return the same thing, we only need one object for all of the differen requests
		MovesHandler movesHandler = new MovesHandler(this);
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
		
		server.createContext("/util/changeLogLevel", new LogHandler(this));
		
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

	@Override
	public IGame getGame(int id) 
	{
		return null;
	}

	@Override
	public IUser getUser(int id) 
	{
		return null;
	}

	@Override
	public void createGame() 
	{
		
	}

	@Override
	public void registerUser(User user)
	{
		
	}
}
