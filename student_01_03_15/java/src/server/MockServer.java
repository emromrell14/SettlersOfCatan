package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

import models.IGame;




//import server.handlers.AddAIHandler;
//import server.handlers.CommandsHandler;
//import server.handlers.CreateGamesHandler;
//import server.handlers.JoinGamesHandler;
//import server.handlers.ListAIHandler;
//import server.handlers.ListGamesHandler;
//import server.handlers.LoadGamesHandler;
//import server.handlers.LogHandler;
//import server.handlers.LoginHandler;
//import server.handlers.ModelHandler;
//import server.handlers.MovesHandler;
//import server.handlers.RegisterHandler;
//import server.handlers.ResetHandler;
//import server.handlers.SaveGamesHandler;
//
//import com.sun.net.httpserver.Headers;
//import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MockServer implements IServer
{
	private HttpServer mServer;
	private static int mPortNumber;
	private String mHost = "localhost";
	
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			mPortNumber = 8898;
		}
		else
		{
			mPortNumber = Integer.parseInt(args[0]);
		}
		new Server().run();
	}
	
	@Override
	public void run()
	{
		try 
		{
			mServer = HttpServer.create(new InetSocketAddress(mPortNumber),10);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		mServer.setExecutor(null);
		
		mServer.createContext("/user/login",loginHandler());
		mServer.createContext("/user/register",registerHandler());
		
		mServer.createContext("/games/list",listGamesHandler());
		mServer.createContext("/games/create",createGamesHandler());
		mServer.createContext("/games/join",joinGamesHandler());
		mServer.createContext("/games/save",saveGamesHandler());
		mServer.createContext("/games/load",loadGamesHandler());

		mServer.createContext("/game/model",modelHandler());
		mServer.createContext("/game/reset",resetHandler());
		mServer.createContext("/game/commands",commandsHandler());
		mServer.createContext("/game/addAI",addAIHandler());
		mServer.createContext("/game/listAI",listAIHandler());
		
		//since all of the moves requests return the same thing, we only need one object for all of the different requests
		mServer.createContext("/moves/sendChat", movesHandler());
		mServer.createContext("/moves/rollNumber", movesHandler());
		mServer.createContext("/moves/robPlayer", movesHandler());
		mServer.createContext("/moves/finishTurn", movesHandler());
		mServer.createContext("/moves/buyDevCard", movesHandler());
		mServer.createContext("/moves/Year_of_Plenty", movesHandler());
		mServer.createContext("/moves/Road_Building", movesHandler());
		mServer.createContext("/moves/Soldier", movesHandler());
		mServer.createContext("/moves/Monopoly", movesHandler());
		mServer.createContext("/moves/Monument", movesHandler());
		mServer.createContext("/moves/buildRoad", movesHandler());
		mServer.createContext("/moves/buildSettlement", movesHandler());
		mServer.createContext("/moves/buildCity", movesHandler());
		mServer.createContext("/moves/offerTrade", movesHandler());
		mServer.createContext("/moves/acceptTrade", movesHandler());
		mServer.createContext("/moves/maritimeTrad", movesHandler());
		mServer.createContext("/moves/discardCards", movesHandler());
		
		mServer.createContext("/util/changeLogLevel", logHandler());
		
		mServer.start();
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
		return mPortNumber;
	}

	@Override
	public String getHost() 
	{
		return mHost;
	}

	@Override
	public IGame getGame(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUser getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, IUser> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUsers(Map<Integer, IUser> users) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, IGame> getGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGames(Map<Integer, IGame> games) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createGame(String name, int id, boolean randomTiles,
			boolean randomNumbers, boolean randomPorts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IUser getCurrentUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGameModelJSON(int version,int gameID) {
		return mHost;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVersion(int gameID) {
		// TODO Auto-generated method stub
		
	}
}
