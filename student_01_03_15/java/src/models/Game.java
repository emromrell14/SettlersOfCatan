package models;

import java.util.List;

import JSONmodels.MessageList;
import proxy.IProxy;

public class Game implements IGame
{
	private Board mBoard;
	private List<Player> mPlayers;
	private TurnTracker mTurnTracker;
	private ResourceList mBank;
	private List<DevCard> mDevCards;
	private int mVersion;
	private Index mWinner;
	private MessageList mChat; //All the chat messages.
	private MessageList mLog; //All the log messages.
	private Robber mRobber;
	
	@Override
	public int rollDice()
	{
		return 0;
	}

	public Board getmBoard() 
	{
		return mBoard;
	}

	public List<Player> getmPlayers() 
	{
		return mPlayers;
	}

	public TurnTracker getmTurnTracker() 
	{
		return mTurnTracker;
	}

	public ResourceList getmBank() 
	{
		return mBank;
	}

	public List<DevCard> getmDevCards() 
	{
		return mDevCards;
	}

	public int getmVersion() 
	{
		return mVersion;
	}

	public Index getmWinner() 
	{
		return mWinner;
	}

	public MessageList getmChat()
	{
		return mChat;
	}

	public MessageList getmLog() 
	{
		return mLog;
	}

	public Robber getmRobber() 
	{
		return mRobber;
	}

	
	
	
	
	
}
