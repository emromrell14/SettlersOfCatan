package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.Building;
import models.Game;
import models.IGame;
import models.Index;
import models.Message;
import models.Player;
import models.Road;
import models.Status;
import server.handlers.*;
import shared.definitions.BuildingType;
import shared.definitions.CatanColor;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import JSONmodels.ClientModelJSON;

import com.sun.net.httpserver.HttpServer;

public class Server implements IServer 
{
	private HttpServer server;
	private static int portNumber;
	private String host = "localhost";
	private Map<Integer,IGame> games = new HashMap<Integer,IGame>();
	private Map<Integer,IUser> users = new HashMap<Integer,IUser>();
	private Map<Integer, List<String>> commands = new HashMap<Integer, List<String>>();
	
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
		
		//since all of the moves requests return the same thing, we only need one object for all of the different requests
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
//		User a = new User(0);
//		a.setUsername("a");
//		a.setPassword("a");
//		
//		User b = new User(1);
//		b.setUsername("b");
//		b.setPassword("b");
//		User c = new User(2);
//		c.setUsername("c");
//		c.setPassword("c");
//		User d = new User(3);
//		d.setUsername("d");
//		d.setPassword("d");
//		
//		users.put(0, a);
//		users.put(1, b);
//		users.put(2, c);
//		users.put(3, d);
		
		
//		try 
//		{
//			Building settlement1 = new Building(new Index(0), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthWest));
//			Building settlement2 = new Building(new Index(0), new VertexLocation(new HexLocation(1,0), VertexDirection.NorthEast));
//			Building settlement3 = new Building(new Index(1), new VertexLocation(new HexLocation(-1,1), VertexDirection.NorthWest));
//			Building settlement4 = new Building(new Index(1), new VertexLocation(new HexLocation(0,1), VertexDirection.NorthEast));
//			Building settlement5 = new Building(new Index(2), new VertexLocation(new HexLocation(0,-1), VertexDirection.NorthWest));
//			Building settlement6 = new Building(new Index(2), new VertexLocation(new HexLocation(2,-1), VertexDirection.East));
//			Building settlement7 = new Building(new Index(3), new VertexLocation(new HexLocation(0,2), VertexDirection.NorthEast));
//			Building settlement8 = new Building(new Index(3), new VertexLocation(new HexLocation(1,-1), VertexDirection.NorthEast));
//
//			Road road1 = new Road(new Index(0), new EdgeLocation(new HexLocation(0,0), EdgeDirection.North));
//			Road road2 = new Road(new Index(0), new EdgeLocation(new HexLocation(1,0), EdgeDirection.North));
//			Road road3 = new Road(new Index(1), new EdgeLocation(new HexLocation(0,1), EdgeDirection.NorthEast));
//			Road road4 = new Road(new Index(1), new EdgeLocation(new HexLocation(-1,1), EdgeDirection.NorthWest));
//			Road road5 = new Road(new Index(2), new EdgeLocation(new HexLocation(0,-1), EdgeDirection.NorthWest));
//			Road road6 = new Road(new Index(2), new EdgeLocation(new HexLocation(3,-1), EdgeDirection.NorthWest));
//			Road road7 = new Road(new Index(3), new EdgeLocation(new HexLocation(0,2), EdgeDirection.NorthEast));
//			Road road8 = new Road(new Index(3), new EdgeLocation(new HexLocation(1,-1), EdgeDirection.NorthEast));
//			
//			
//			//Game g = new Game(false,false,false);
//			Player p1 = new Player();
//			p1.setColor(CatanColor.PUCE);
//			p1.setUser(a);
//			p1.setPlayerID(0);
//			p1.setName("a");
//			p1.setPlayerIndex(new Index(0));
//			p1.addSettlement(settlement1);
//			p1.addSettlement(settlement2);
//			p1.addRoad(road1);
//			p1.addRoad(road2);
//			p1.setRoadCount(13);
//			p1.setSettlementCount(3);
//			
//			Player p2 = new Player();
//			p2.setColor(CatanColor.YELLOW);
//			p2.setUser(b);
//			p2.setPlayerID(1);
//			p2.setName("b");
//			p2.setPlayerIndex(new Index(1));
//			p2.addSettlement(settlement3);
//			p2.addSettlement(settlement4);
//			p2.addRoad(road3);
//			p2.addRoad(road4);
//			p2.setRoadCount(13);
//			p2.setSettlementCount(3);
//			
//			Player p3 = new Player();
//			p3.setUser(c);
//			p3.setColor(CatanColor.BLUE);
//			p3.setName("c");
//			p3.setPlayerID(2);
//			p3.setPlayerIndex(new Index(2));
//			p3.addSettlement(settlement5);
//			p3.addSettlement(settlement6);
//			p3.addRoad(road5);
//			p3.addRoad(road6);
//			p3.setRoadCount(13);
//			p3.setSettlementCount(3);
//			
//			Player p4 = new Player();
//			p4.setUser(d);
//			p4.setColor(CatanColor.GREEN);
//			p4.setPlayerID(3);
//			p4.setName("d");
//			p4.setPlayerIndex(new Index(3));
//			p4.addSettlement(settlement7);
//			p4.addSettlement(settlement8);
//			p4.addRoad(road7);
//			p4.addRoad(road8);
//			p4.setRoadCount(13);
//			p4.setSettlementCount(3);
//			
//			/*
//			g.setName("game 1");
//			g.addPlayer(p1);
//			g.addPlayer(p2);
//			g.addPlayer(p3);
//			g.addPlayer(p4);
//			g.setId(99);
//			*/
//			createGame("game 1", 0, false, false, false);
//			Game g = (Game)(games.get(0));
//			g.board().addRoad(road1);
//			g.board().addRoad(road2);
//			g.board().addRoad(road3);
//			g.board().addRoad(road4);
//			g.board().addRoad(road5);
//			g.board().addRoad(road6);
//			g.board().addRoad(road7);
//			g.board().addRoad(road8);
//
//			g.board().addSettlement(settlement1);
//			g.board().addSettlement(settlement2);
//			g.board().addSettlement(settlement3);
//			g.board().addSettlement(settlement4);
//			g.board().addSettlement(settlement5);
//			g.board().addSettlement(settlement6);
//			g.board().addSettlement(settlement7);
//			g.board().addSettlement(settlement8);
//
//			
//			g.addPlayer(p1);
//			g.addPlayer(p2);
//			g.addPlayer(p3);
//			g.addPlayer(p4);
//			g.turnTracker().setStatus(Status.ROLLING);
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
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
	
	public synchronized void loadGame(IGame game)
	{
		game.setId(games.size());
		games.put(games.size(), game);
	}

	@Override
	public synchronized void registerUser(User user)
	{
		users.put(user.getID(), user);
	}
	
	public synchronized Map<Integer, IGame> getGames() {
		return games;
	}

	public synchronized void setGames(Map<Integer, IGame> games) {
		this.games = games;
	}

	public synchronized Map<Integer, IUser> getUsers() {
		return users;
	}

	public synchronized void setUsers(Map<Integer, IUser> users) {
		this.users = users;
	}

	public Map<Integer, List<String>> getCommands()
	{
		return this.commands;
	}
	
	public void setCommands(Map<Integer, List<String>> commands)
	{
		this.commands = commands;
	}

	public void resetCommands(int gameID)
	{
		this.commands.remove(gameID);
	}
	
	public void addCommand(int gameID, String command)
	{
		if(!this.commands.containsKey(gameID)) {
			this.commands.put(gameID, new ArrayList<String>());
		}
		this.commands.get(gameID).add(command);
	}
	
	@Override
	public synchronized void createGame() {
		
	}
	
	public synchronized IUser getCurrentUser(String username)
	{
		Iterator<?> it = users.entrySet().iterator();
		while(it.hasNext())
		{
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
//			System.out.println(username + " " +((User)pair.getValue()).getUsername() );
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

	@Override
	public synchronized void checkForWinner(int gameID) 
	{
		IGame g = games.get(gameID);
		for(Player p : g.players())
		{
			if(p.victoryPointCount() >= 10)
			{
				g.setWinner(p.playerIndex().value());
				break;
			}
		}
	}
}
