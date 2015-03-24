package models;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.HexType;
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
	
	public Game(boolean randomTiles, boolean randomNumbers, boolean randomPorts)
	{
		mBoard = new Board(randomTiles,randomNumbers,randomPorts);
		mPlayers = new ArrayList<Player>();
		mTurnTracker = new TurnTracker();
		mTurnTracker.setStatus(Status.FIRSTROUND);
		mBank = new ResourceList();
		mDevCards = new ArrayList<DevCard>();
		mRobber = new Robber(mBoard.getDesertLocation());
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
		try 
		{
			mCurrentTrade = new Trade(new Index(-1),new Index(-1),new ResourceList());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
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
	public void sendChat(Index playerIndex, String message) throws IllegalStateException 
	{
		if(!this.canSendChat())
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		String name = this.getPlayer(playerIndex).name();
		
		// add chat to message list
		this.mChat.add(new Message(message,name));
	}
	
	public boolean canRollDice(Index playerIndex, int number)
	{
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (this.mTurnTracker.status() != Status.ROLLING)
		{
			return false;
		}
		// if ratio, inputResource, and outputResource are all null don't even check the bank.  
		// This is being called from the client to check if the button should be enabled				
		
		
		// I don't think that not having resouces in the bank should prevent a player from rolling
		
//		if (number != 0)
//		{
//			ResourceList rl = this.getAllResourcesToBeGiven(number);
//			
//			// check if the bank has enough to give everyone their resources
//			for (ResourceType r : ResourceType.values())
//			{
//				if (this.mBank.getResource(r) < rl.getResource(r))
//				{
//					return false;
//				}
//			}
//		}
		return true;
	}
	@Override
	public void rollDice(Index playerIndex, int number) throws IllegalStateException
	{
		if(!this.canRollDice(playerIndex, number))
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
		if (player != null && !this.mTurnTracker.currentTurn().equals(player.playerIndex()))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.ROBBING))
		{
			return false;
		}
		if (this.mRobber.location().equals(loc))
		{
			return false;
		}
		
		if (victim != null && victim.resources().isEmpty())
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
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING) && !this.mTurnTracker.status().equals(Status.FIRSTROUND) && !this.mTurnTracker.status().equals(Status.SECONDROUND))
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
		
		Player player = this.getPlayer(playerIndex);
		
		// cycle turntracker
		this.mTurnTracker.endTurn();
		
		if(this.mTurnTracker.status() == Status.SECONDROUND && playerIndex.value() == 0)
		{
			this.giveResourcesToPlayersAfterSetup();
		}
		
		// change status to rolling
		//ignore the previous comment because that was stupid
//		this.mTurnTracker.setStatus(Status.ROLLING);

		// set new dev cards to be old		
		for (DevCard d : player.devCards())
		{
			if (d.isNew())
			{
				d.setNew(false);
			}
		}
		// resetting has played dev card for next turn
		player.setHasPlayedDevCard(false);
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

	public boolean canPlayYearOfPlenty(Index playerIndex, ResourceType resource1, ResourceType resource2) 
	{
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		
		
		if (resource1 != null && resource2 != null)
		{
			//Check for resources being in the bank
			if (resource1 != resource2)
			{
				if (this.mBank.getResource(resource1) < 1 || this.mBank.getResource(resource2) < 1)
				{
					return false;
				}
			}
			else
			{
				if (this.mBank.getResource(resource1) < 2)
				{
					return false;
				}
			}
		}
		
		
		// Checking if the player has a YOP dev card that can be played
		if (!this.getPlayer(playerIndex).canPlayYearOfPlenty())
		{
			return false;
		}
		
		return true;
	}
	@Override
	public void playYearOfPlenty(Index playerIndex, ResourceType resource1, ResourceType resource2) throws IllegalStateException 
	{
		if(!this.canPlayYearOfPlenty(playerIndex, resource1, resource2))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		Player player = this.getPlayer(playerIndex);
				
		//Subtract resources from bank
		this.mBank.addResource(resource1, -1);
		this.mBank.addResource(resource2, -1);
		
		//Give resources to player
		player.resources().addResource(resource1, 1);
		player.resources().addResource(resource2, 1);
		
		//Remove their year of plenty card
		player.removeDevCard(DevCardType.YEAR_OF_PLENTY);

		// setting played dev card to true
		this.getPlayer(playerIndex).setHasPlayedDevCard(true);

	}
	
	public boolean canPlayRoadBuilding(Index playerIndex, EdgeLocation spot1, EdgeLocation spot2)
	{
		Player player = this.getPlayer(playerIndex);
		if (spot1 != null && spot2 != null)
		{
			//Check if spot1 and spot2 are connected to your roads
			if (!player.canPlaceRoad(spot1) || !player.canPlaceRoad(spot2))
			{
				return false;
			}
			//Check if either of the roads are in the water
			if (spot1.isInSea() || spot2.isInSea())
			{
				return false;
			}
		}
		// Check if player can play it
		if (!player.canPlayRoadBuilder())
		{
			return false;
		}
		
		return true;
	}
	@Override
	public void playRoadBuilding(Index playerIndex, EdgeLocation spot1, EdgeLocation spot2) throws IllegalStateException
	{
		if(!this.canPlayRoadBuilding(playerIndex, spot1, spot2))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
			
		// build both roads
		this.buildRoad(playerIndex, spot1, true);
		this.buildRoad(playerIndex, spot2, true);
		
		// setting played dev card to true
		this.getPlayer(playerIndex).setHasPlayedDevCard(true);	}
	
	public boolean canPlaySoldier(Index playerIndex)
	{
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		
		Player player = this.getPlayer(playerIndex);
		
		if (!player.canPlaySoldier())
		{
			return false;
		}
		
		return true;
	}
	@Override
	public void playSoldier(Index playerIndex, Index victimIndex, HexLocation location) throws IllegalStateException 
	{
		if(!this.canPlaySoldier(playerIndex))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		try
		{
			this.robPlayer(playerIndex, victimIndex, location);			
			
			// setting played dev card to true
			this.getPlayer(playerIndex).setHasPlayedDevCard(true);
			
			this.validateLargestArmy(playerIndex);
		}
		catch (IllegalStateException e)
		{
			throw e;
		}
	
	}

	public boolean canPlayMonopoly(Index playerIndex)
	{
		Player player = this.getPlayer(playerIndex);
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		// Check if player can play it
		if (!player.canPlayMonopoly())
		{
			return false;
		}
		
		return true;
	}
	@Override
	public void playMonopoly(Index playerIndex, ResourceType resource) throws IllegalStateException
	{
		if(!this.canPlayMonopoly(playerIndex))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		Player currentPlayer = this.getPlayer(playerIndex);
		// steal all of a particular resource from every player
		for (Player p : this.players())
		{
			if (p.playerID() != currentPlayer.playerID())
			{
				int toAdd = p.resources().getResource(resource);
				currentPlayer.resources().addResource(resource, toAdd);
				p.resources().addResource(resource, (-1)*toAdd);
			}
		}
	}

	public boolean canPlayMonument(Index playerIndex)
	{
		Player player = this.getPlayer(playerIndex);
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		// Check if player can play it
		if (!player.canPlayMonument())
		{
			return false;
		}
		return true;
	}
	@Override
	public void playMonument(Index playerIndex) throws IllegalStateException
	{
		if(!this.canPlayMonument(playerIndex))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		this.getPlayer(playerIndex).addVictoryPoint(1);
	}

	public boolean canBuildRoad(Index playerIndex, EdgeLocation roadLocation, boolean free)
	{
		Player player = this.getPlayer(playerIndex);
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			if (this.mTurnTracker.status().equals(Status.FIRSTROUND) || this.mTurnTracker.status().equals(Status.SECONDROUND))
			{
				// if no settlements return false
				for (Building b : player.settlements())
				{
					if (!player.doesSettlementHaveRoadAttached(b))
					{
						if (!this.board().canPlaceRoad(roadLocation))
						{
							return false;
						}
						if (!player.canPlaceRoad(roadLocation) && !free)
						{
							return false;
						}
						return true;
					}
				}
			}
			return false;
		}
		
		// Check if player can play it
		if (!this.board().canPlaceRoad(roadLocation) || !player.canAffordRoad() || !player.canPlaceRoad(roadLocation))
		{
			return false;
		}
		return true;
	}
	@Override
	public void buildRoad(Index playerIndex, EdgeLocation roadLocation,	boolean free) throws IllegalStateException
	{
		if(!this.canBuildRoad(playerIndex, roadLocation, free))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		// resources are decremented and added to bank
		Player player = this.getPlayer(playerIndex);
		if(!free)
		{
			player.addResourcesToList(-1, 0, 0, 0, -1);
			this.mBank.addBrick(1);
			this.mBank.addWood(1);
		}
		
		// decrement number of roads player can build
		player.addToRoadCount(-1);
		
		// Adds road to player's list of roads and the board
		Road road = new Road(playerIndex, roadLocation);
		player.addRoad(road);
		this.board().addRoad(road);
		
		
		// Check for longest road
		this.validateLongestRoad(playerIndex);
	}


	public boolean canBuildSettlement(Index playerIndex, VertexLocation vertexLocation, boolean free)
	{
		Player player = this.getPlayer(playerIndex);
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			if (this.mTurnTracker.status().equals(Status.FIRSTROUND) || this.mTurnTracker.status().equals(Status.SECONDROUND))
			{
				// if first or second round don't check afford method
				if (!this.board().canPlaceSettlement(vertexLocation))
				{
					return false;
				}
				if (!player.canPlaceSettlement(vertexLocation) && !free)
				{
					return false;
				}
				return true;
			}
			return false;
		}
		
		// Check if player can play it
		if (!this.board().canPlaceSettlement(vertexLocation) || !player.canAffordSettlement()|| !player.canPlaceSettlement(vertexLocation))
		{
			return false;
		}
		return true;
	}
	@Override
	public void buildSettlement(Index playerIndex, VertexLocation vertexLocation, boolean free) throws IllegalStateException
	{
		if(!this.canBuildSettlement(playerIndex, vertexLocation, free))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		// resources are decremented and added to bank
		Player player = this.getPlayer(playerIndex);
		if(!free)
		{
			player.addResourcesToList(-1, 0, -1, -1, -1);
			this.mBank.addBrick(1);
			this.mBank.addWood(1);
			this.mBank.addSheep(1);
			this.mBank.addWheat(1);
		}

		// decrement number of settlements player can build
		player.addToSettlementCount(-1);
		
		// Adds settlement to player's list of settlements and the board
		Building settlement = new Building(playerIndex, vertexLocation);
		player.addSettlement(settlement);
		this.board().addSettlement(settlement);
		
		// add a victory point
		player.addVictoryPoint(1);
	}

	public boolean canBuildCity(Index playerIndex, VertexLocation vertexLocation)
	{
		Player player = this.getPlayer(playerIndex);
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		
		if (!player.canAffordCity() || !player.canPlaceCity(vertexLocation))
		{
			return false;
		}

		return true;
	}
	@Override
	public void buildCity(Index playerIndex, VertexLocation vertexLocation) throws IllegalStateException
	{
		if(!this.canBuildCity(playerIndex, vertexLocation))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		// resources are decremented and added to bank
		Player player = this.getPlayer(playerIndex);
		player.addResourcesToList(0, -3, 0, -2, 0);
		this.mBank.addOre(3);
		this.mBank.addWheat(2);

		// decrement number of cities player can build
		// increment number of settlements they can build
		player.addToSettlementCount(1);
		player.addToCityCount(-1);
		
		// Adds city to player's list of cities and the board
		// and Takes a settlement off of the board and adds to player
		Building city = new Building(playerIndex, vertexLocation);
		player.removeSettlement(vertexLocation);
		this.board().removeSettlement(vertexLocation);
		player.addCity(city);
		this.board().addCity(city);
		
		// add a victory point
		player.addVictoryPoint(1);		
	}

	public boolean canOfferTrade(Index playerIndex, ResourceList offer)
	{
		Player player = this.getPlayer(playerIndex);
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		
		if(offer != null)
		{
			// checking to see if player has all resources they are offering
			for (ResourceType r : ResourceType.values())
			{
				if (player.resources().getResource(r) < (-1)*offer.getResource(r))
				{
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public void offerTrade(Index playerIndex, Index receiverIndex, ResourceList offer) throws IllegalStateException
	{
		if(!this.canOfferTrade(playerIndex, offer))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		// Store trade in turn tracker
		this.setTrade(new Trade(playerIndex, receiverIndex, offer));		
	}

	public boolean canAcceptTrade(Index playerIndex)
	{
		// check if the trade offer is empty
		if (this.trade().offer().isEmpty())
		{
			return false;
		}
		
		// check if the trade is for this player
		if (!this.trade().receiver().equals(playerIndex))
		{
			return false;
		}
		
		Player player = this.getPlayer(playerIndex);
		// must have requested resources
		for (ResourceType r : ResourceType.values())
		{
			if (player.resources().getResource(r) < this.trade().offer().getResource(r))
			{
				return false;
			}
		}
		
		return true;
	}
	@Override
	public void acceptTrade(Index playerIndex, boolean willAccept) throws IllegalStateException
	{
		if(!this.canAcceptTrade(playerIndex))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		Player acceptingPlayer = this.getPlayer(playerIndex);
		Player offeringPlayer = this.getPlayer(this.mCurrentTrade.sender());
		ResourceList offer = this.mCurrentTrade.offer();
		acceptingPlayer.addResourcesToList(-offer.brick(), -offer.ore(), -offer.sheep(), -offer.wheat(), -offer.wood());
		offeringPlayer.addResourcesToList(offer.brick(), offer.ore(), offer.sheep(), offer.wheat(), offer.wood());
		
		this.mCurrentTrade = null;
	}

	public boolean canMaritimeTrade(Index playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource)
	{
		Player player = this.getPlayer(playerIndex);
		if (!this.mTurnTracker.currentTurn().equals(playerIndex))
		{
			return false;
		}
		if (!this.mTurnTracker.status().equals(Status.PLAYING))
		{
			return false;
		}
		
		// if ratio, inputResource, and outputResource are all null don't even check the bank.  
		// This is being called from the client to check if the button should be enabled
		if (ratio != 0 && inputResource != null && outputResource != null)
		{
			if (this.mBank.getResource(outputResource) < 1)
			{
				return false;
			}
		}
		
		// Check that a player can maritime trade
		if (!player.canMaritimeTrade(this.board().ports()))
		{
			return false;
		}
		
		return true;
	}
	@Override
	public void maritimeTrade(Index playerIndex, int ratio,	ResourceType inputResource, ResourceType outputResource) throws IllegalStateException
	{
		if(!this.canMaritimeTrade(playerIndex, ratio, inputResource, outputResource))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		Player player = this.getPlayer(playerIndex);

		// Give resources to the bank
		player.resources().addResource(inputResource, -ratio);
		this.mBank.addResource(inputResource, ratio);
		
		// Get resource back from the bank
		player.resources().addResource(outputResource, 1);
		this.mBank.addResource(outputResource, -1);
	}

	public boolean canDiscardCards(Index playerIndex, ResourceList discardedCards)
	{
		if (!this.mTurnTracker.status().equals(Status.DISCARDING))
		{
			return false;
		}
		Player player = this.getPlayer(playerIndex);
		
		// Check that a player has more than 7 cards
		if (!player.canDiscard())
		{
			return false;
		}
		
		if(discardedCards != null)
		{
			// Check that you have the cards you're trying to discard
			for (ResourceType r : ResourceType.values())
			{
				if (player.resources().getResource(r) < discardedCards.getResource(r))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	@Override
	public void discardCards(Index playerIndex, ResourceList discardedCards) throws IllegalStateException
	{
		if(!this.canDiscardCards(playerIndex, discardedCards))
		{
			throw new IllegalStateException("Failed pre-conditions");
		}
		
		Player player = this.getPlayer(playerIndex);

		for (ResourceType r : ResourceType.values())
		{
			player.resources().addResource(r, -discardedCards.getResource(r));
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

	private void giveResourcesToPlayersAfterSetup() {
		for (Player p : mPlayers)
		{
			Building b = p.settlements().get(1);
			for (Hex h : mBoard.hexes())
			{
				// give one resource for each settlement on this hex
				if (b.isOnHex(h) && h.resource() != HexType.DESERT)
				{
					p.resources().addResource(h.resource().resourceType(), 1);
					switch(h.resource())
					{
					case BRICK:
						mBank.addBrick(-1);
						break;
					case ORE:
						mBank.addOre(-1);
						break;
					case WHEAT:
						mBank.addWheat(-1);
						break;
					case SHEEP:
						mBank.addSheep(-1);
						break;
					case WOOD:
						mBank.addWood(-1);
						break;
					case DESERT:
						break;
					default:
						System.out.println("Game.giveResourcesToPlayersAfterSetup() should never get here");
					}
				}
			}
		}
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
	
	private ResourceList getAllResourcesToBeGiven(int number)
	{
		ResourceList rl = new ResourceList();
		for (Hex h : mBoard.hexes())
		{
			if (h.number().value() == number)
			{
				for (Player p : mPlayers)
				{
					for (Building b : p.settlements())
					{
						// give one resource for each settlement on this hex
						if (b.isOnHex(h))
						{
							rl.addResource(h.resource().resourceType(), 1);
						}
					}
					for (Building b : p.cities())
					{
						// give two resources for each city on this hex		
						if (b.isOnHex(h))
						{
							rl.addResource(h.resource().resourceType(), 2);
						}
					}
				}
			}
		}
		return rl;
	}
	
//	@Override
//	public boolean canOfferTrade(Index playerIndex) 
//	{
//		boolean playerHasCards = false;
//		boolean othersHaveCards = false;
//		for(Player p: mPlayers)
//		{
//			ResourceList temp = p.resources();
//			if(p.playerIndex().equals(playerIndex)) 
//			{
//				if(temp.brick() > 0 || temp.ore() > 0 || temp.wheat() > 0 || temp.wood() > 0 || temp.sheep() > 0)
//				{
//					playerHasCards = true;
//				}
//			}
//			else
//			{
//				if(temp.brick() > 0 || temp.ore() > 0 || temp.wheat() > 0 || temp.wood() > 0 || temp.sheep() > 0)
//				{
//					othersHaveCards = true;
//				}
//			}
//		}
//		
//		return playerHasCards && othersHaveCards;
//	}

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
	
	private void validateLargestArmy(Index playerIndex) 
	{
		Player contendingPlayer = this.getPlayer(playerIndex);
		Player largestArmyPlayer = this.getPlayer(this.getLargestArmyIndex());
		if (contendingPlayer.soldierCount() > largestArmyPlayer.soldierCount())
		{
			this.mTurnTracker.setLargestArmy(playerIndex);
			largestArmyPlayer.addVictoryPoint(-2);
			contendingPlayer.addVictoryPoint(2);
		}
		
	}
	
	private void validateLongestRoad(Index playerIndex) 
	{
		Player contendingPlayer = this.getPlayer(playerIndex);
		Player longestRoadPlayer = this.getPlayer(this.getLongestRoadIndex());
		if(longestRoadPlayer == null)
		{
			if(contendingPlayer.roads().size() > 4)
			{
				contendingPlayer.addVictoryPoint(2);
				this.mTurnTracker.setLongestRoad(playerIndex);
			}
			return;
		}
		if (contendingPlayer.roads().size() > longestRoadPlayer.roads().size())
		{
			this.mTurnTracker.setLongestRoad(playerIndex);
			longestRoadPlayer.addVictoryPoint(-2);
			contendingPlayer.addVictoryPoint(2);
		}	
	}

	@Override
	public void incrementVersion()
	{
		mVersion += 1;
	}
	
	//END MISC FUNCTIONS

}
