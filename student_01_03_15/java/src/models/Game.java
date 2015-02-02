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
	private  List<DevCard> mDevCards;
	private int mVersion;
	private Index mWinner;
	private MessageList mChat; //All the chat messages.
	private MessageList mLog; //All the log messages.
	private Robber mRobber;
	
	@Override
	public int rollDice()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
