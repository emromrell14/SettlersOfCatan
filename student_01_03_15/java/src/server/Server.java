package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.Game;
import models.IGame;
import models.Index;
import models.Message;
import models.Player;
import server.handlers.*;
import shared.definitions.CatanColor;
import JSONmodels.ClientModelJSON;

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
			portNumber = 8081;
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
			System.out.println("Server running on port "+portNumber);
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
		
		server.createContext("/docs/api/data", new Handlers.JSONAppender(""));
		server.createContext("/docs/api/view", new Handlers.BasicFile(""));
		
		server.start();
		
		
		
		// HARD CODED USERS -------------------------------------------------------
		User a = new User(0);
		a.setUsername("aaa");
		a.setPassword("aaa");
		
		User b = new User(1);
		b.setUsername("bbb");
		b.setPassword("bbb");
		User c = new User(2);
		c.setUsername("ccc");
		c.setPassword("ccc");
		User d = new User(3);
		d.setUsername("ddd");
		d.setPassword("ddd");
		
		users.put(0, a);
		users.put(1, b);
		users.put(2, c);
		users.put(3, d);
		
		
		try 
		{
			//Game g = new Game(false,false,false);
			Player p1 = new Player();
			p1.setColor(CatanColor.PUCE);
			p1.setUser(a);
			p1.setPlayerID(0);
			p1.setName("aaa");
			p1.setPlayerIndex(new Index(0));
			Player p2 = new Player();
			p2.setColor(CatanColor.YELLOW);
			p2.setUser(b);
			p2.setPlayerID(1);
			p2.setName("bbb");
			p2.setPlayerIndex(new Index(1));
			Player p3 = new Player();
			p3.setUser(c);
			p3.setColor(CatanColor.BLUE);
			p3.setName("ccc");
			p3.setPlayerID(2);
			p3.setPlayerIndex(new Index(2));
			Player p4 = new Player();
			p4.setUser(d);
			p4.setColor(CatanColor.GREEN);
			p4.setPlayerID(3);
			p4.setName("ddd");
			p4.setPlayerIndex(new Index(3));
			
			/*
			g.setName("game 1");
			g.addPlayer(p1);
			g.addPlayer(p2);
			g.addPlayer(p3);
			g.addPlayer(p4);
			g.setId(99);
			*/
			createGame("game 1", 0, false, false, false);
			Game g = (Game)(games.get(0));
			g.addPlayer(p1);
			g.addPlayer(p2);
			g.addPlayer(p3);
			g.addPlayer(p4);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		// -------------------------------------------------------------------------
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
	public synchronized IGame getGame(int id) 
	{
		return games.get(id);
	}

	@Override
	public synchronized IUser getUser(int id) 
	{
		return null;
	}

	@Override
	public synchronized void createGame(String name, int id, boolean randomTiles, boolean randomNumbers, boolean randomPorts) 
	{
		Game g = new Game(randomTiles,randomNumbers,randomPorts);
		g.setName(name);
		g.setId(id);
		g.setVersion(0);
		g.setWinner(-1);
		List<Message> m = new ArrayList<Message>();
		List<Message> log = new ArrayList<Message>();
		g.setChat(m);
		g.setLog(log);
		//TODO: set other values found in clientModelJSON or the dejsonifying breaks client when poller grabs game
		games.put(games.size(), g);
	}

	@Override
	public synchronized void registerUser(User user)
	{
		users.put(user.getID(), user);
	}

	public synchronized Map<Integer, IUser> getUsers() {
		return users;
	}

	public synchronized void setUsers(Map<Integer, IUser> users) {
		this.users = users;
	}

	public synchronized Map<Integer, IGame> getGames() {
		return games;
	}

	public synchronized void setGames(Map<Integer, IGame> games) {
		this.games = games;
	}

	@Override
	public synchronized void createGame() {
		// TODO Auto-generated method stub
		
	}
	
	public synchronized IUser getCurrentUser(String username)
	{
		Iterator it = users.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			System.out.println(username + " " +((User)pair.getValue()).getUsername() );
			if(username.equals(((User)pair.getValue()).getUsername()))
			{
				return (IUser)pair.getValue();
			}
		}
		return null;
	}

	@Override
	public synchronized String getGameModelJSON(int version, int gameID) 
	{
		String json = "\"true\"";
		IGame game = games.get(gameID);
		if(game.version() > version)
		{
			ClientModelJSON c = new ClientModelJSON(game);
			json = c.toJSON();
		}
		
		return json;
	}

	@Override
	public synchronized void updateVersion(int gameID) 
	{
		games.get(gameID).incrementVersion();
	}
	
	
}
