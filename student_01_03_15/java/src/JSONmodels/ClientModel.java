package JSONmodels;

import models.Index;

public class ClientModel 
{
	private ResourceList mBank; //The cards available to be distributed to the players.
	private MessageList mChat; //All the chat messages.
	private MessageList mLog; //All the log messages.
	private Map mMap;
	private Player[] mPlayers;
	private TradeOffer mTradeOffer; //OPTIONAL. Current trade offer, if there is one.
	private TurnTracker mTurnTracker; //This tracks whose turn it is and what action's being done.
	private int mVersion; //The version of the model. This is incremented whenever anyone makes a move.
	private Index mWinner; //This is -1 when nobody's won yet. When they have, it's their order index [0-3].
	
	/**
	 * Creates a ClientModel object from all the variables
	 * 
	 * @param bank
	 * @param chat
	 * @param log
	 * @param map
	 * @param players
	 * @param tradeOffer
	 * @param turnTracker
	 * @param version
	 * @param winner
	 * @return New ClientModel object
	 */
	public ClientModel(ResourceList bank, MessageList chat, MessageList log, Map map, Player[] players, TradeOffer tradeOffer, TurnTracker turnTracker, int version, Index winner)
	{
		
	}
	
	/**
	 * Creates a ClientModel object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New ClientModel object
	 */
	public ClientModel(String JSON)
	{
		
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		return "";
	}
}
