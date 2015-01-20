package classes;

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
	
	public ClientModel(ResourceList bank, MessageList chat, MessageList log, Map map, Player[] players, TradeOffer tradeOffer, TurnTracker turnTracker, int version, Index winner)
	{
		this.mBank = bank;
		this.mChat = chat;
		this.mLog = log;
		this.mMap = map;
		this.mPlayers = players;
		this.mTradeOffer = tradeOffer;
		this.mTurnTracker = turnTracker;
		this.mVersion = version;
		this.mWinner = winner;
	}
	
	public ResourceList bank()
	{
		return mBank;
	}
	public MessageList chat()
	{
		return mChat;
	}
	public MessageList log()
	{
		return mLog;
	}
	public Map map()
	{
		return mMap;
	}
	public Player[] players()
	{
		return mPlayers;
	}
	public TradeOffer tradeOffer()
	{
		return mTradeOffer;
	}
	public TurnTracker turnTracker()
	{
		return mTurnTracker;
	}
	public int version() 
	{
		return mVersion;
	}
	public Index winner()
	{
		return mWinner;
	}
}
