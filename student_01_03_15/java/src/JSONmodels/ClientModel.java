package JSONmodels;

import models.Game;
import com.google.gson.Gson;

public class ClientModel 
{
	private ResourceList bank; //The cards available to be distributed to the players.
	private MessageList chat; //All the chat messages.
	private MessageList log; //All the log messages.
	private Map map;
	private Player[] players;
	private TradeOffer tradeOffer; //OPTIONAL. Current trade offer, if there is one.
	private TurnTracker turnTracker; //This tracks whose turn it is and what action's being done.
	private int version; //The version of the model. This is incremented whenever anyone makes a move.
	private int winner; //This is -1 when nobody's won yet. When they have, it's their order index [0-3].
	
	/**
	 * Creates a ClientModel object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New ClientModel object
	 */
	public static ClientModel fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, ClientModel.class);
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
	public ResourceList getBank()
	{
		return bank;
	}

	/**
	 * @return the chat
	 */
	public MessageList getChat() 
	{
		return chat;
	}

	/**
	 * @return the log
	 */
	public MessageList getLog() 
	{
		return log;
	}

	/**
	 * @return the map
	 */
	public Map getMap()
	{
		return map;
	}

	/**
	 * @return the players
	 */
	public Player[] getPlayers()
	{
		return players;
	}

	/**
	 * @return the tradeOffer
	 */
	public TradeOffer getTradeOffer()
	{
		return tradeOffer;
	}

	/**
	 * @return the turnTracker
	 */
	public TurnTracker getTurnTracker()
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
		g.setResourceList(bank.getModelResourceList());
		g.setVersion(version);
		g.setWinner(winner);
		g.setRobber(map.getModelRobber());
		g.setBoard(map.getModelBoard());
		g.setChat(chat);
		g.setLog(log);
		
		for(Player p : players)
		{
			g.addPlayer(p.getModel(g.board()));
		}
		
		return g;
	}
}
