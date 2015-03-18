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
	private int mId;
	private String mName;
	
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
	
	public void setPlayersColor(String name, String color)
	{
		for(Player p: mPlayers)
		{
			if(name.equals(p.name()))
			{
				p.setColor(CatanColor.valueOf(color)); 
			}
		}
	}
	
	@Override
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
	
	@Override
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
	
	@Override
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

	@Override
	public Board board() 
	{
		return mBoard;
	}

	@Override
	public void setBoard(Board b)
	{
		mBoard = b;
	}

	@Override
	public List<Player> players() 
	{
		
		return mPlayers;
	}
	
	@Override
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
	
	@Override
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
	
	@Override
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

	@Override
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
	
	@Override
	public void addPlayer(Player p)
	{
		mPlayers.add(p);
	}

	@Override
	public TurnTracker turnTracker() 
	{
		return mTurnTracker;
	}

	@Override
	public void setTurnTracker(TurnTracker t)
	{
		mTurnTracker = t;
	}

	@Override
	public ResourceList bank() 
	{
		return mBank;
	}

	@Override
	public void setBank(ResourceList r)
	{
		mBank = r;
	}

	@Override
	public List<DevCard> devCards() 
	{
		return mDevCards;
	}
	
	@Override
	public void setDevCards(List<DevCard> devCards) 
	{
		mDevCards = devCards;
	}

	@Override
	public int version() 
	{
		return mVersion;
	}
	
	@Override
	public void setVersion(int v)
	{
		mVersion = v;
	}


	@Override
	public Index winner() 
	{
		return mWinner;
	}

	@Override
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

	@Override
	public MessageListJSON chat()
	{
		return mChat;
	}
	
	@Override
	public void setChat(MessageListJSON m)
	{
		mChat = m;
	}

	@Override
	public MessageListJSON log() 
	{
		return mLog;
	}
	
	@Override
	public void setLog(MessageListJSON m)
	{
		mLog = m;
	}

	@Override
	public Robber robber() 
	{
		return mRobber;
	}
	

	@Override
	public void setRobber(Robber r)
	{
		mRobber = r;
	}

	@Override
	public Trade trade()
	{
		return mCurrentTrade;
	}

	@Override
	public void setTrade(Trade model) 
	{
		mCurrentTrade = model;
	}
	
	@Override
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

	@Override
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

	@Override
	public int getLargestArmyIndex() 
	{
		return mTurnTracker.largestArmy().value();
	}

	@Override
	public int getLongestRoadIndex() 
	{
		return mTurnTracker.longestRoad().value();
	}

	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}
	
}
