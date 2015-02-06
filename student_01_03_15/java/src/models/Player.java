package models;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class Player implements IPlayer
{	
	private CatanColor mColor; //The color of this player
	private String mName;
	private Index mPlayerIndex; //What place in the array is this player? 0-3. It determines their turn order. This is used often everywhere
	private int mPlayerID; //The unique playerID. This is used to pick the client player apart from the others. This is only used here and in your cookie.
	private ResourceList mResources = new ResourceList(); //The resource cards this player has.
	private int mRoadCount = 15;
	private int mSettlementCount = 5; //How many settlements this player has left to play.
	private int mCityCount = 4; //How many cities this player has left to play.
	private int mSoldierCount = 0;
	private int mVictoryPointCount = 0;
	private boolean mDiscarded = false;
	private boolean mHasPlayedDevCard = false;
	private int mMonuments = 0;
	
	private List<Road> mRoads;
	private List<Building> mSettlements;
	private List<Building> mCities;
	private List<DevCard> mDevCards;
	
	
	
	public Player(CatanColor color, String name, Index index, int playerID)
	{
		this.mColor = color;
		this.mName = name;
		this.mPlayerIndex = index;
		this.mPlayerID = playerID;
		
		this.mRoads = new ArrayList<Road>();
		this.mSettlements = new ArrayList<Building>();
		this.mCities = new ArrayList<Building>();
		this.mDevCards = new ArrayList<DevCard>();
	}
	
	public Player(CatanColor color, boolean discarded, Number monuments, 
			String name, List<DevCard> newDevCards, List<DevCard> oldDevCards, Index playerIndex, boolean playerDevCard, 
			int playerID, ResourceList resources, List<Road> roads, 
			List<Building> settlements, List<Building> cities, int soldiers, int victoryPoints)
	{
		this.mColor = color;
		this.mName = name;
		this.mPlayerIndex = playerIndex;
		this.mPlayerID = playerID;
		this.mResources = resources;
		this.mRoads = roads;
		this.mSettlements = settlements;
		this.mCities = cities;
		this.mSoldierCount = soldiers;
		this.mVictoryPointCount = victoryPoints;
		this.mRoadCount = 15;
		this.mSettlementCount = 5;
		this.mCityCount = 4;
	}

	public int soldierCount()
	{
		return mSoldierCount;
	}
	public int victoryPointCount()
	{
		return mVictoryPointCount;
	}
	public int roadCount()
	{
		return mRoadCount;
	}
	public int settlementCount()
	{
		return mSettlementCount;
	}
	public int cityCount()
	{
		return mCityCount;
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

	public boolean canPlaceRoad(EdgeLocation loc) 
	{
		loc = loc.getNormalizedLocation();
		switch (loc.getDir())
		{
		case NorthWest:
			if (
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.SouthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.North)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest), EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest), EdgeDirection.South))
			)
			{
				return true;
			}
			break;
		case North:
			if (
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.NorthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.North), EdgeDirection.SouthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.North), EdgeDirection.SouthEast))
			)
			{
				return true;
			}
			break;
		case NorthEast:
			if (
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.North)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.SouthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast), EdgeDirection.NorthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast), EdgeDirection.South))
			)
			{
				return true;
			}
			break;
		default:
			System.out.println("Invalid Edge Direction");
		}
		return false;
	}
	
	public boolean checkForRoad(EdgeLocation loc)
	{
		loc = loc.getNormalizedLocation();
		for (Road road : this.roads())
		{
			EdgeLocation edge = road.location().getNormalizedLocation();
			if (loc.equals(edge))
			{
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * This is to be called on the first and second rounds of the game (Special Cases)
	 * @param loc
	 * @param settlement
	 * @return
	 */
	public boolean canPlaceRoad(EdgeLocation loc, VertexLocation settlement) 
	{
		// STILL NEED TO CHECK FOR SEA TILES AS NEIGHBOR HEXES
		loc = loc.getNormalizedLocation();
		settlement = settlement.getNormalizedLocation();
		switch (settlement.getDir())
		{
			case NorthWest:
				if (
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.North)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NorthWest)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest),EdgeDirection.NorthEast))
				)
				{
					return true;
				}
				break;
			case NorthEast:
				if (
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.North)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NorthEast)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast),EdgeDirection.NorthWest))
				)
				{
					return true;
				}
				break;
			default:
				break;
		}
			
		return false;
	}
	
	/**
	 * Decreases a player's brick and wood by 1 each
	 * adds a road at specified location to player's list of roads
	 * @param loc
	 */
	public void buildRoad(EdgeLocation loc) 
	{
		ResourceList r = this.resources();
		r.addBrick(-1);
		r.addWood(-1);
		this.mRoadCount -= 1;
		this.mRoads.add(new Road(this.mPlayerIndex, loc));
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
	
	public boolean canPlaceSettlement(VertexLocation loc) 
	{
		loc = loc.getNormalizedLocation();
		switch (loc.getDir())
		{
			case NorthWest:
				if (
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.North))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NorthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest),EdgeDirection.NorthEast))
				)
				{
					return true;
				}
				break;
			case NorthEast:
				if (
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.North))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast),EdgeDirection.NorthWest))
				)
				{
					return true;
				}
				break;
			default:
				break;
		}
			
		return false;	
	}
	
	public void buildSettlement(VertexLocation loc)
	{
		ResourceList r = this.resources();
		r.addBrick(-1);
		r.addWood(-1);
		r.addSheep(-1);
		r.addWheat(-1);
		this.mSettlementCount -= 1;
		this.mSettlements.add(new Building(this.mPlayerIndex, loc));
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
	public boolean canPlaceCity(VertexLocation loc) 
	{
		//Return true for locations where a settlement is already placed
		loc = loc.getNormalizedLocation();
		for(Building settlement : this.mSettlements)
		{
			VertexLocation vertex = settlement.location().getNormalizedLocation();
			if(loc.equals(vertex))
			{
				return true;
			}
		}
		return false;
	}
	
	public void buildCity(VertexLocation loc)
	{
		loc = loc.getNormalizedLocation();
		
		ResourceList r = this.resources();
		r.addWheat(-2);
		r.addOre(-3);
		for(Building settlement : this.settlements())
		{
			VertexLocation vertex = settlement.location().getNormalizedLocation();
			if(loc.equals(vertex))
			{
				//Remove settlement, change to a city, and add to the cities
				this.mSettlements.remove(settlement);
				this.mSettlementCount += 1;
				settlement.setBuildingTypeToCity();
				this.mCityCount -= 1;
				this.mCities.add(settlement);
			}
		}		
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

	public void buyDevCard()		// needs to be filled correctly
	{
		//Takes players resource cards
		//asks DevCard Bank to return 1 DevCard
		//adds returned DevCard to players list
		
		
		//this.addDevCard(m);
	}
	public void addDevCard(DevCard card)
	{
		this.mDevCards.add(card);
	}
	
	/**
	 * Returns whether this player needs to discard when a 7 is rolled
	 * 
	 * @return false if they already have discarded or if they don't have more than 7 cards
	 */
	public boolean canDiscard()		//does this do anything more than check the num of resource cards?
	{
		if(this.resources().getTotal() < 7)
		{
			return false;
		}
		return true;
	}
	public boolean discard()		//what does this dooooooo
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
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.MONUMENT)
			{
				return true;
			}
		}
		return false;
	}
	public void playMonument()
	{
		
	}
	
	public void addResourcesToList(int brick, int ore, int sheep, int wheat, int wood)
	{
		this.mResources = this.resources().updateResourceList(brick, ore, sheep, wheat, wood);
	}
	
	public boolean canAcceptTrade(ResourceList tradeOffer)
	{
		return mResources.brick() >= tradeOffer.brick()
				&& mResources.ore() >= tradeOffer.ore()
				&& mResources.sheep() >= tradeOffer.sheep()
				&& mResources.wheat() >= tradeOffer.wheat()
				&& mResources.wood() >= tradeOffer.wood();
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
