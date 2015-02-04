package models;

import java.util.List;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class Player implements IPlayer
{	
	private CatanColor mColor; //The color of this player
	private String mName;
	private Index mPlayerIndex; //What place in the array is this player? 0-3. It determines their turn order. This is used often everywhere
	private int mPlayerID; //The unique playerID. This is used to pick the client player apart from the others. This is only used here and in your cookie.
	private ResourceList mResources; //The resource cards this player has.
	private int mNumRoads;
	private int mNumSettlements; //How many settlements this player has left to play.
	private int mNumCities; //How many cities this player has left to play.
	private int mSoldiers;
	private int mVictoryPoints;
	private boolean mDiscarded = true;
	private boolean mHasPlayedDevCard = true;
	private int mMonuments = 0;
	
	private List<Road> mRoads;
	private List<Building> mSettlements;
	private List<Building> mCities;
	private List<DevCard> mDevCards;
	
	
	public Player(CatanColor color, boolean discarded, Number monuments, String name, List<DevCard> newDevCards, List<DevCard> oldDevCards, Index playerIndex, boolean playerDevCard, int playerID, ResourceList resources, List<Road> roads, List<Building> settlements, List<Building> cities, int soldiers, int victoryPoints)
	{
		this.mColor = color;
		this.mName = name;
		this.mPlayerIndex = playerIndex;
		this.mPlayerID = playerID;
		this.mResources = resources;
		this.mRoads = roads;
		this.mSettlements = settlements;
		this.mCities = cities;
		this.mSoldiers = soldiers;
		this.mVictoryPoints = victoryPoints;
		this.mNumRoads = 15;
		this.mNumSettlements = 5;
		this.mNumCities = 4;
	}

	public int soldierCount()
	{
		return mSoldiers;
	}
	
	public int victoryPointCount()
	{
		return mVictoryPoints;
	}
	public int roadCount()
	{
		return mNumRoads;
	}
	public int settlementCount()
	{
		return mNumSettlements;
	}
	public int cityCount()
	{
		return mNumCities;
	}
	public CatanColor color()
	{
		return mColor;
	}
	public boolean hasDiscarded()
	{
		return mDiscarded;
	}
	public int monumentCount()
	{
		return mMonuments;
	}
	public String name()
	{
		return mName;
	}
	public List<DevCard> devCards()
	{
		return mDevCards;
	}
	public Index playerIndex()
	{
		return mPlayerIndex;
	}
	public boolean hasPlayedDevCard()
	{
		return mHasPlayedDevCard;
	}
	public int playerID()
	{
		return mPlayerID;
	}
	public ResourceList resources()
	{
		return mResources;
	}
	public List<Road> roads()
	{
		return mRoads;
	}
	public List<Building> settlements()
	{
		return mSettlements;
	}
	public List<Building> cities()
	{
		return mCities;
	}
	
	
	
	//Functions
	/**
	 * Checks all preconditions for road building
	 * @pre Player is logged in, has joined a game, has a road, has 1 wood 1 brick, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a road can be built, false otherwise
	 */
	public boolean canAffordRoad() 
	{
		ResourceList r = this.resources();
		if (
				this.roadCount() < 1 || // Checks that the player has roads left to build
				r.brick() < 1 || r.wood() < 1) // Checks that player has sufficient resources
		{
			return false;
		}
		return true;
	}

	public boolean canPlaceRoad(EdgeLocation loc) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Checks all preconditions for building a new settlement
	 * @pre Player is logged in, has joined a game, has a settlement, 1 wood 1 brick 1 wheat 1 sheep, a valid place to build, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a settlement can be built, false otherwise
	 */
	public boolean canAffordSettlement() 
	{
		ResourceList r = this.resources();
		if (
				this.settlementCount() < 1 || // Checks that the player has settlements left to build
				r.brick() < 1 || r.wood() < 1 || r.sheep() < 1 || r.wheat() < 1) // Checks that player has sufficient resources
		{
			return false;
		}
		return true;
	}
	
	public boolean canPlaceSettlement(VertexLocation loc) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Checks all preconditions for building a city.
	 * @pre Player is logged in, has joined a game, has a city, 3 ore 2 wheat, a settlement to build on, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return	true if a city can be built, false otherwise
	 */
	public boolean canAffordCity() 
	{
		ResourceList r = this.resources();
		if (
				this.cityCount() < 1 || // Checks that you have cities left to build
				this.settlements().isEmpty() || // Checks that there is a settlement to build on
				r.ore() < 3 || r.wheat() < 2) // Checks that there are enough resources
		{
			return false;
		}
		return true;
	}

	public boolean canPlaceCity(VertexLocation loc) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Checks all preconditions for buying a development card.
	 * @pre Player is logged in, joined a game, has 1 sheep 1 wheat 1 ore, it is their turn, and there are development cards in bank. 
	 * 		Dice have also already been rolled.
	 * @post none 
	 * @return true if a card can be bought, false otherwise
	 */
	public boolean canBuyDevCard()
	{
		ResourceList r = this.resources();
		if (
				r.sheep() < 1 || r.wheat() < 1 || r.ore() < 1) // Checks that there are enough resources
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks all preconditions for playing a development card.
	 * @pre Player is logged in, in a game, it is their turn, they own a dev card, they have not played a dev card this turn
	 * 		other than victory points, they didn't buy the dev card this turn. Dice have also already been rolled.
	 * @post none
	 * @return true if a card can be played, false otherwise
	 */
	
	public boolean canPlayDevCard() 
	{
		ResourceList r = this.resources();
		if (
				this.devCards().isEmpty() || // Checks that this player has a dev card
				this.hasPlayedDevCard() || // Checks that player hasn't already played a dev card
				r.sheep() < 1 || r.wheat() < 1 || r.ore() < 1) // Checks that there are enough resources
		{
			return false;
		}
		boolean hasPlayableCard = false;
		for (DevCard d : this.devCards())
		{
			if (!d.isNew())
			{
				hasPlayableCard = true;
			}
		}
		if (!hasPlayableCard)
		{
			return false;
		}
		return true;
	}

	
	
	/**
	 * Returns whether this player needs to discard when a 7 is rolled
	 * 
	 * @return false if they already have discarded or if they don't have more than 7 cards
	 */
	public boolean canDiscard()
	{
		if(this.hasDiscarded() || this.resources().getTotal() < 7)
		{
			return false;
		}
		return true;
	}
	public boolean discard()
	{
		return true;
	}
	
	/**
	 * Checks if the player is eligible to play a Year of Plenty Card
	 * 
	 * @return False if they have already played a dev card this turn or do not have an old Year of Plenty Card
	 */
	public boolean canPlayYearOfPlenty()
	{
		if(this.hasPlayedDevCard())
		{
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.YEAR_OF_PLENTY && !devCard.isNew())
			{
				return true;
			}
		}
		return false;
	}
	public void playYearOfPlenty()
	{
		
	}
	
	/**
	 * Returns whether a player can play a road builder card
	 * 
	 * @return false if they have already played a dev card this turn, or if they do not have a road builder card
	 */
	public boolean canPlayRoadBuilder()
	{
		if(this.hasPlayedDevCard())
		{
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.ROAD_BUILD && !devCard.isNew())
			{
				return true;
			}
		}
		return false;
	}
	public void playRoadBuilder()
	{
		
	}
	
	/**
	 * Returns whether a player can play a soldier card
	 * 
	 * @return false if they have already played a dev card this turn, or if they do not have a soldier card
	 */
	public boolean canPlaySoldier()
	{
		if(this.hasPlayedDevCard())
		{
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.SOLDIER && !devCard.isNew())
			{
				return true;
			}
		}
		return false;
	}
	public void playSoldier()
	{
		
	}
	
	/**
	 * Returns whether a player can play a monopoly card
	 * 
	 * @return false if they have already played a dev card this turn, or if they do not have a monopoly card
	 */
	public boolean canPlayMonopoly()
	{
		if(this.hasPlayedDevCard())
		{
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.MONOPOLY && !devCard.isNew())
			{
				return true;
			}
		}
		return false;
	}
	public void playMonopoly()
	{
		
	}
	
	/**
	 * Returns whether a player can play a Monument card
	 * 
	 * @return false if they have already played a dev card this turn, or if they do not have a monument card
	 */
	public boolean canPlayMonument()
	{
		if(this.hasPlayedDevCard())
		{
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.MONUMENT && !devCard.isNew());
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean canAcceptTrade(ResourceList tradeOffer)
	{
		return mResources.brick() >= tradeOffer.brick()
				&& mResources.ore() >= tradeOffer.ore()
				&& mResources.sheep() >= tradeOffer.sheep()
				&& mResources.wheat() >= tradeOffer.wheat()
				&& mResources.wood() >= tradeOffer.wood();
	}
	
	public void playMonument()
	{
		
	}
	
	public boolean haveResourceAmount(PortType type)
	{
		boolean toReturn = false;
		if(type != null)
		{
			switch (type)
			{
			case BRICK:
				toReturn = mResources.brick() >= 2;
				break;
			case ORE:
				toReturn = mResources.ore() >= 2;
				break;
			case WOOD:
				toReturn = mResources.wood() >= 2;
				break;
			case SHEEP:
				toReturn = mResources.sheep() >= 2;
				break;
			case WHEAT:
				toReturn = mResources.wheat() >= 2;
				break;
			case THREE:
				toReturn = mResources.brick() >= 3
						|| mResources.ore() >= 3
						|| mResources.sheep() >= 3
						|| mResources.wheat() >= 3
						|| mResources.wood() >= 3;
				break;
			}
		}
		return toReturn;
	}

	public boolean canMaritimeTrade()
	{			
		if(mResources.brick() >= 4
				|| mResources.ore() >= 4
				|| mResources.sheep() >= 4
				|| mResources.wheat() >= 4
				|| mResources.wood() >= 4)
		{
			return true;
		}

		boolean toReturn = false;
		
		for(Building b : mCities)
		{
			if(b.port() != null)
			{
				PortType portType = b.port().resource();
				toReturn = haveResourceAmount(portType);
				if(toReturn)
				{
					return toReturn;
				}
			}
		}
		
		for(Building b : mSettlements)
		{
			if(b.port() != null)
			{
				PortType portType = b.port().resource();
				toReturn = haveResourceAmount(portType);
				if(toReturn)
				{
					return toReturn;
				}
			}
		}
			
		return toReturn;
	}
}
