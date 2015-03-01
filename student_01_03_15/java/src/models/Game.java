package models;

import java.util.ArrayList;
import java.util.List;

import JSONmodels.MessageListJSON;
import shared.definitions.CatanColor;
import shared.locations.HexLocation;

public class Game implements IGame
{
	private Board mBoard;
	private List<Player> mPlayers;
	private TurnTracker mTurnTracker;
	private ResourceList mBank;
	private List<DevCard> mDevCards;
	private int mVersion;
	private Index mWinner;
	private MessageListJSON mChat; //All the chat messages.
	private MessageListJSON mLog; //All the log messages.
	private Robber mRobber;
	private Trade mCurrentTrade = null;
	
	public Game()
	{
		mBoard = new Board();
		mPlayers = new ArrayList<Player>();
		mTurnTracker = new TurnTracker();
		mBank = new ResourceList();
		mDevCards = new ArrayList<DevCard>();
		for(int i=0; i<2; i++)
		{
			mDevCards.add(new Monopoly());
			mDevCards.add(new Monument());
			mDevCards.add(new YearOfPlenty());
		}
		for(int i=0; i<5; i++)
		{
			mDevCards.add(new RoadBuild());
		}
		for(int i=0; i<14; i++)
		{
			mDevCards.add(new Soldier());
		}
	}
	
	public CatanColor getPlayerColor(int playerID)
	{
		for(Player p: mPlayers)
		{
			if(p.playerID() == playerID)
			{
				return p.color();
			}
		}
		return null;
	}
	
	public CatanColor getPlayerColor(String playerName)
	{
		for(Player p: mPlayers)
		{
			if(p.name().equals(playerName))
			{
				return p.color();
			}
		}
		return null;
	}
	
	public void endTurn()
	{
		Index currentPlayer = mTurnTracker.currentTurn();
		for(Player p: mPlayers)
		{
			if(p.playerIndex().value() == currentPlayer.value())
			{
				p.endTurn();
				break;
			}
		}
		mTurnTracker.endTurn();
	}
	
	@Override
	public int rollDice()
	{
		return 0;
	}
	
	public MessageListJSON getLog()
	{
		return mLog;
	}

	public Board board() 
	{
		return mBoard;
	}
	
	public void addPlayer(Player p)
	{
		mPlayers.add(p);
	}
	
	public void setLog(MessageListJSON m)
	{
		mLog = m;
	}
	
	public void setChat(MessageListJSON m)
	{
		mChat = m;
	}
	
	public void setBoard(Board b)
	{
		mBoard = b;
	}
	
	public void setRobber(Robber r)
	{
		mRobber = r;
	}
	
	public void setResourceList(ResourceList r)
	{
		mBank = r;
	}
	
	public void setVersion(int v)
	{
		mVersion = v;
	}
	
	public void setWinner(int w)
	{
		try 
		{
			mWinner = new Index(w);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setTurnTracker(TurnTracker t)
	{
		mTurnTracker = t;
	}

	public List<Player> players() 
	{
		
		return mPlayers;
	}
	public Player getPlayer(int playerID)
	{
		for(Player player : this.mPlayers)
		{
			if(player.playerID() == playerID)
			{
				return player;
			}
		}
		return null;
	}
	public Player getPlayer(Index playerIndex)
	{
		for(Player player : this.mPlayers)
		{
			// this check is for the joinGameController hack, problems with joining player not having an index
			if(player.playerIndex() == null || playerIndex == null)
			{
				return null;
			}
			if(player != null)
			{
				if(player.playerIndex().equals(playerIndex))
				{
					return player;
				}
			}
			
		}
		return null;
	}
	public Index getPlayerIndex(int playerID)
	{
		Index playerIndex = null;
		for(Player p:mPlayers)
		{
			if(p != null)
			{
				if(p.playerID() == playerID)
				{
					playerIndex = p.playerIndex();
					break;
				}
			}
			
		}
		return playerIndex;
	}

	public int getPlayerID(Index playerIndex)
	{
		for(Player player : this.mPlayers)
		{
			if(player.playerIndex().equals(playerIndex))
			{
				return player.playerID();
			}
		}
		return -1;
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

	public MessageListJSON chat()
	{
		return mChat;
	}

	public MessageListJSON log() 
	{
		return mLog;
	}

	public Robber robber() 
	{
		return mRobber;
	}

	public boolean canOfferTrade(Index playerIndex) 
	{
		boolean playerHasCards = false;
		boolean othersHaveCards = false;
		for(Player p: mPlayers)
		{
			ResourceList temp = p.resources();
			if(p.playerIndex().equals(playerIndex)) 
			{
				if(temp.brick() > 0 || temp.ore() > 0 || temp.wheat() > 0 || temp.wood() > 0 || temp.sheep() > 0)
				{
					playerHasCards = true;
				}
			}
			else
			{
				if(temp.brick() > 0 || temp.ore() > 0 || temp.wheat() > 0 || temp.wood() > 0 || temp.sheep() > 0)
				{
					othersHaveCards = true;
				}
			}
		}
		
		return playerHasCards && othersHaveCards;
	}
	
	public Trade trade()
	{
		return mCurrentTrade;
	}

	public void setTrade(Trade model) 
	{
		mCurrentTrade = model;
	}

	public int getPlayerPoints(int playerID) 
	{
		for(Player p : mPlayers)
		{
			if(p.playerID() == playerID)
			{
				return p.victoryPointCount();
			}
		}
		return 0;
	}

	public List<Player> getRobbingVictims(HexLocation hexLoc) 
	{
		List<Player> victims = new ArrayList<Player>();
		for(Player p : mPlayers)
		{
			if(p.playerIndex().value() != mTurnTracker.currentTurn().value())
			{
				if(p.isVictim(hexLoc))
				{
					victims.add(p);
				}
			}
		}
		return victims;
	}

	public int getLargestArmyIndex() 
	{
		return mTurnTracker.largestArmy().value();
	}

	public int getLongestRoadIndex() 
	{
		return mTurnTracker.longestRoad().value();
	}	
}
