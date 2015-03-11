package JSONmodels;

import models.Game;
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
		g.setChat(chat);
		g.setLog(log);
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
}
