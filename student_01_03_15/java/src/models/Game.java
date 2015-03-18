package models;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class Game implements IGame
{
	private Board mBoard;
	private List<Player> mPlayers;
	private TurnTracker mTurnTracker;
	private ResourceList mBank;
	private List<DevCard> mDevCards;
	private int mVersion;
	private Index mWinner;
	private List<Message> mChat; //All the chat messages.
	private List<Message> mLog; //All the log messages.
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

	//GETTERS AND SETTERS
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
	
	public void setPlayers(List<Player> players)
	{
		this.mPlayers = players;
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
	public List<Message> chat() {
		return mChat;
	}

	@Override
	public void setChat(List<Message> m) {
		mChat = m;
	}

	@Override
	public List<Message> log() {
		return mLog;
	}

	@Override
	public void setLog(List<Message> m) {
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
	
	public int id() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String name() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}
	//END GETTERS AND SETTERS
	
	//EXTENDED GETTERS AND SETTERS
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
	public int getLargestArmyIndex() 
	{
		return mTurnTracker.largestArmy().value();
	}

	@Override
	public int getLongestRoadIndex() 
	{
		return mTurnTracker.longestRoad().value();
	}
	//END EXTENDED GETTERS AND SETTERS
	
	//API FUNCTIONS
	public boolean canSendChat() 
	{
		return true;
	}
	@Override
	public void sendChat(String message, Index playerIndex) throws IllegalStateException 
	{
		if(!this.canSendChat())
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		String name = this.getPlayer(playerIndex).name();
		
		// add chat to message list
		this.mChat.add(new Message(message,name));
	}
	
	public boolean canRollDice(Index playerIndex)
	{
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (this.mTurnTracker.status() != Status.ROLLING)
		{
			return false;
		}
		return true;
	}
	@Override
	public void rollDice(Index playerIndex, int number) throws IllegalStateException
	{
		if(!this.canRollDice(playerIndex))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		// Allocate resources
		for (Hex h : mBoard.hexes())
		{
			if (h.number().value() == number)
			{
				// give resources to players with settlements or cities on this hex
				giveResourcesToPlayers(h);
			}
		}
		
		// Change Turn Tracker status
		if (number == 7)
		{
			this.mTurnTracker.setStatus(Status.DISCARDING);
		}
		else
		{
			this.mTurnTracker.setStatus(Status.PLAYING);
		}
	}

	public boolean canRobPlayer(Player player, Player victim, HexLocation loc)
	{
		if (!this.mTurnTracker.status().equals(Status.ROBBING))
		{
			return false;
		}
		if (this.mRobber.location().equals(loc))
		{
			return false;
		}
		
		if (victim.resources().isEmpty())
		{
			return false;
		}
		return true;
	}
	@Override
	public void robPlayer(Index playerIndex, Index victimIndex, HexLocation loc) throws IllegalStateException
	{
		Player player = this.getPlayer(playerIndex);		
		Player victim = this.getPlayer(victimIndex);
		
		if(!this.canRobPlayer(player, victim, loc))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		
		// move robber
		this.mRobber.setLocation(loc);
		
		// steal from victim
		List<ResourceType> hand = victim.getHand();
		ResourceType stolenCard = hand.get((int)(Math.random() * hand.size()));
		victim.resources().addResource(stolenCard, -1);
		
		// give to player
		player.resources().addResource(stolenCard, 1);
		
		// change turn tracker status
		this.mTurnTracker.setStatus(Status.PLAYING);
	}
	
	public boolean canFinishTurn(Index playerIndex)
	{
		if (this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		return true;
	}
	@Override
	public void finishTurn(Index playerIndex) throws IllegalStateException
	{
		if(!canFinishTurn(playerIndex))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		// cycle turntracker
		this.mTurnTracker.endTurn();
		
		// change status to rolling
		this.mTurnTracker.setStatus(Status.ROLLING);

		// set new dev cards to be old		
		for (DevCard d : this.getPlayer(playerIndex).devCards())
		{
			if (d.isNew())
			{
				d.setNew(false);
			}
		}
		
	}

	public boolean canBuyDevCard(Index playerIndex)
	{
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		if (this.devCards().isEmpty())
		{
			return false;
		}
		if(!this.getPlayer(playerIndex).canAffordDevCard())
		{
			return false;
		}
		return true;
	}
	@Override
	public void buyDevCard(Index playerIndex) throws IllegalStateException
	{
		if(!this.canBuyDevCard(playerIndex))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		Player player = this.getPlayer(playerIndex);
		
		// Player gains a new card, if monument it will be old, new otherwise
		DevCard d = this.mDevCards.get((int)(Math.random() * this.mDevCards.size()));
		this.mDevCards.remove(d);
		player.addDevCard(d);
		
		// Decrement 1 Wheat, 1 Ore, 1 Sheep from player's resources
		player.addResourcesToList(0, -1, -1, -1, 0);
		
	}

	public boolean canPlayYearOfPlenty(Index playerIndex) 
	{
		if (this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		
		//Check for resources being in the bank
		
		return true;
	}
	@Override
	public void playYearOfPlenty(Index playerIndex, ResourceType resource1, ResourceType resource2) throws IllegalStateException 
	{
		if(!this.canPlayYearOfPlenty(playerIndex))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		Player player = this.getPlayer(playerIndex);
				
		//Subtract resources from bank
		
		//Give resources to player
		
		//Remove their year of plenty card
		
	}
	
	public boolean canPlayRoadBuilding(Index playerIndex, EdgeLocation spot1, EdgeLocation spot2)
	{
		//Check if spot1 and spot2 are connected to your roads
		
		//Check if you have two roads to give
		
		//Check if either of the roads are in the water
		
		return true;
	}
	@Override
	public void playRoadBuilding(Index playerIndex, EdgeLocation spot1, EdgeLocation spot2) throws IllegalStateException
	{
		if(!this.canPlayRoadBuilding(playerIndex, spot1, spot2))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		Player player = this.getPlayer(playerIndex);
		
		//
	}
	
	public boolean canPlaySoldier(Index playerIndex, Index victimIndex, HexLocation location)
	{
		return true;
	}
	@Override
	public void playSoldier(Index playerIndex, Index victimIndex, HexLocation location) throws IllegalStateException 
	{
		if(!this.canPlaySoldier(playerIndex, victimIndex, location))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}

	public boolean canPlayMonopoly(Index playerIndex, ResourceType resource)
	{
		return true;
	}
	@Override
	public void playMonopoly(Index playerIndex, ResourceType resource) throws IllegalStateException
	{
		if(!this.canPlayMonopoly(playerIndex, resource))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}

	public boolean canPlayMonument(Index playerIndex)
	{
		return true;
	}
	@Override
	public void playMonument(Index playerIndex) throws IllegalStateException
	{
		if(!this.canPlayMonument(playerIndex))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}

	public boolean canBuildRoad(Index playerIndex, EdgeLocation roadLocation, boolean free)
	{
		return true;
	}
	@Override
	public void buildRoad(Index playerIndex, EdgeLocation roadLocation,	boolean free) throws IllegalStateException
	{
		if(!this.canBuildRoad(playerIndex, roadLocation, free))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}

	public boolean canBuildSettlement(Index playerIndex, VertexLocation vertexLocation, boolean free)
	{
		return true;
	}
	@Override
	public void buildSettlement(Index playerIndex, VertexLocation vertexLocation, boolean free) throws IllegalStateException
	{
		if(!this.canBuildSettlement(playerIndex, vertexLocation, free))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}

	public boolean canBuildCity(Index playerIndex, VertexLocation vertexLocation)
	{
		return true;
	}
	@Override
	public void buildCity(Index playerIndex, VertexLocation vertexLocation) throws IllegalStateException
	{
		if(!this.canBuildCity(playerIndex, vertexLocation))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}

	public boolean canOfferTrade(Index playerIndex, Index receiverIndex, ResourceList offer)
	{
		return true;
	}
	@Override
	public void offerTrade(Index playerIndex, Index receiverIndex, ResourceList offer) throws IllegalStateException
	{
		if(!this.canOfferTrade(playerIndex, receiverIndex, offer))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}

	public boolean canAcceptTrade(Index playerIndex, boolean willAccept)
	{
		return true;
	}
	@Override
	public void acceptTrade(Index playerIndex, boolean willAccept) throws IllegalStateException
	{
		if(!this.canAcceptTrade(playerIndex, willAccept))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}

	public boolean canMaritimeTrade(Index playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource)
	{
		return true;
	}
	@Override
	public void maritimeTrade(Index playerIndex, int ratio,	ResourceType inputResource, ResourceType outputResource) throws IllegalStateException
	{
		if(!this.canMaritimeTrade(playerIndex, ratio, inputResource, outputResource))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}

	public boolean canDiscardCards(Index playerIndex, ResourceList discardedCards)
	{
		return true;
	}
	@Override
	public void discardCards(Index playerIndex, ResourceList discardedCards) throws IllegalStateException
	{
		if(!this.canDiscardCards(playerIndex, discardedCards))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
	}
	//END API FUNCTIONS
	
	//MISC FUNCTIONS
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

	private void giveResourcesToPlayers(Hex h) {
		for (Player p : mPlayers)
		{
			for (Building b : p.settlements())
			{
				// give one resource for each settlement on this hex
				if (b.isOnHex(h))
				{
					p.resources().addResource(h.resource().resourceType(), 1);
				}
			}
			for (Building b : p.cities())
			{
				// give two resources for each city on this hex		
				if (b.isOnHex(h))
				{
					p.resources().addResource(h.resource().resourceType(), 2);
				}
			}
		}
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
	//END MISC FUNCTIONS

}
