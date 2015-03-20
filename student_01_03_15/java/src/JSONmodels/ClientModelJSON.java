package JSONmodels;

import java.util.List;

import models.Game;
import models.IGame;
import models.Message;

import com.google.gson.Gson;

public class ClientModelJSON 
{
	private ResourceListJSON bank; //The cards available to be distributed to the players.
	private MessageListJSON chat; //All the chat messages.
	private MessageListJSON log; //All the log messages.
	private MapJSON map;
	private PlayerJSON[] players;
	private TradeOfferJSON tradeOffer; //OPTIONAL. Current trade offer, if there is one.
	private TurnTrackerJSON turnTracker; //This tracks whose turn it is and what action's being done.
	private int version; //The version of the model. This is incremented whenever anyone makes a move.
	private int winner; //This is -1 when nobody's won yet. When they have, it's their order index [0-3].
	
	public ClientModelJSON(IGame game) 
	{
		this.bank = new ResourceListJSON(game.bank());
		this.chat = new MessageListJSON(game.chat());
		this.log = new MessageListJSON(game.log());
		this.map = new MapJSON(game.board(), game.robber().location());
		
		this.players = new PlayerJSON[game.players().size()];
		for(int i=0; i < this.players.length; i++)
		{
			this.players[i] = new PlayerJSON(game.players().get(i));
		}
		
		this.tradeOffer = new TradeOfferJSON(game.trade());
		this.turnTracker = new TurnTrackerJSON(game.turnTracker());
		this.version = game.version();
		this.winner = game.winner().value();
	}

	/**
	 * Creates a ClientModel object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New ClientModel object
	 */
	public static ClientModelJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, ClientModelJSON.class);
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the bank
	 */
	public ResourceListJSON getBank()
	{
		return bank;
	}

	/**
	 * @return the chat
	 */
	public MessageListJSON getChat() 
	{
		return chat;
	}

	/**
	 * @return the log
	 */
	public MessageListJSON getLog() 
	{
		return log;
	}

	/**
	 * @return the map
	 */
	public MapJSON getMap()
	{
		return map;
	}

	/**
	 * @return the players
	 */
	public PlayerJSON[] getPlayers()
	{
		return players;
	}

	/**
	 * @return the tradeOffer
	 */
	public TradeOfferJSON getTradeOffer()
	{
		return tradeOffer;
	}

	/**
	 * @return the turnTracker
	 */
	public TurnTrackerJSON getTurnTracker()
	{
		return turnTracker;
	}

	/**
	 * @return the version
	 */
	public int getVersion() 
	{
		return version;
	}

	/**
	 * @return the winner
	 */
	public int getWinner()
	{
		return winner;
	}
	
	public Game getGameObject()
	{
		Game g = new Game();
		g.setTurnTracker(turnTracker.getModelTurnTracker());
		g.setBank(bank.getModelResourceList());
		g.setVersion(version);
		g.setWinner(winner);
		System.out.println("Index of winner coming from server: " + winner);
		g.setRobber(map.getModelRobber());
		g.setBoard(map.getModelBoard());
		g.setChat(this.getModelChat());
		g.setLog(this.getModelLog());
		if(tradeOffer != null)
		{
			g.setTrade(tradeOffer.getModel());
		}
		for(PlayerJSON p : players)
		{
			if(p != null)
				g.addPlayer(p.getModel(g.board()));
		}
		
		return g;
	}
	
	public List<Message> getModelChat() 
	{
		return this.chat.getMessages();
	}
	public List<Message> getModelLog() 
	{
		return this.log.getMessages();
	}
}
