package models;

import java.util.List;

import proxy.IProxy;

public class Game implements IGame
{
	private Board mBoard;
	private List<Player> mPlayers;
	private TurnTracker mTurnTracker;
	
	@Override
	public int rollDice()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
