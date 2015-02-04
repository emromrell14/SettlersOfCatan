package models;

import java.util.List;

import JSONmodels.MessageList;

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

	public Board board() 
	{
		return mBoard;
	}

	public List<Player> players() 
	{
		return mPlayers;
	}

	public TurnTracker turnTracker() 
	{
		return mTurnTracker;
	}

	public ResourceList bank() 
	{
		return mBank;
	}

	public List<DevCard> devCards() 
	{
		return mDevCards;
	}

	public int version() 
	{
		return mVersion;
	}

	public Index winner() 
	{
		return mWinner;
	}

	public MessageList chat()
	{
		return mChat;
	}

	public MessageList log() 
	{
		return mLog;
	}

	public Robber robber() 
	{
		return mRobber;
	}	
}
