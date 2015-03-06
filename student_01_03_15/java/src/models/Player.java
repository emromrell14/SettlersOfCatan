package models;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class Player implements IPlayer
{	
	private CatanColor mColor; //The color of this player
	private String mName;
	private Index mPlayerIndex = null; //What place in the array is this player? 0-3. It determines their turn order. This is used often everywhere
	private int mPlayerID; //The unique playerID. This is used to pick the client player apart from the others. This is only used here and in your cookie.
	private ResourceList mResources; //The resource cards this player has.
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
		
		this.mResources = new ResourceList();
		this.mRoads = new ArrayList<Road>();
		this.mSettlements = new ArrayList<Building>();
		this.mCities = new ArrayList<Building>();
		this.mDevCards = new ArrayList<DevCard>();
	}
	
	public Player(CatanColor color, boolean discarded, Number monuments, 
			String name, List<DevCard> newDevCards, List<DevCard> oldDevCards, Index playerIndex, 
			int playerID, ResourceList resources, int soldiers, int victoryPoints, int numSettlements,int numCities, int numRoads,
			boolean playedDevCard)
	{
		this.mColor = color;
		this.mName = name;
		this.mPlayerIndex = playerIndex;
		this.mPlayerID = playerID;
		this.mResources = resources;
		this.mSoldierCount = soldiers;
		this.mVictoryPointCount = victoryPoints;
		this.mRoadCount = numRoads;
		this.mSettlementCount = numSettlements;
		this.mCityCount = numCities;
		this.mRoads = new ArrayList<Road>();
		this.mSettlements = new ArrayList<Building>();
		this.mCities = new ArrayList<Building>();
		this.mDevCards = new ArrayList<DevCard>();
		this.mDevCards.addAll(newDevCards);
		this.mDevCards.addAll(oldDevCards);
		this.mHasPlayedDevCard = playedDevCard;
	}
	
	public void addRoad(Road r)
	{
		mRoads.add(r);
	}
	
	public void addCity(Building b)
	{
		mCities.add(b);
	}
	
	public void addSettlement(Building b)
	{
		mSettlements.add(b);
	}
	
//	public void setRoads(List<Road> roads)
//	{
//		this.mRoads = roads;
//	}
//	public void setSettlements(List<Building> settlements)
//	{
//		this.mSettlements = settlements;
//	}
//	public void setCities(List<Building> cities)
//	{
//		this.mCities = cities;
//	}
	
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
		//Check if we are in the sea first
		if(loc.isInSea())
		{
			return false;
		}

		loc = loc.getNormalizedLocation();
		switch (loc.getDir().getLengthendDirection())
		{
		case NorthWest:
			if (
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.SouthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.North)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest), EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest), EdgeDirection.South)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.SW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.N)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NW), EdgeDirection.NE)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NW), EdgeDirection.S))
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
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.North), EdgeDirection.SouthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.NW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.NE)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.N), EdgeDirection.SW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.N), EdgeDirection.SE))
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
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast), EdgeDirection.South)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.N)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(), EdgeDirection.SE)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NE), EdgeDirection.NW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NE), EdgeDirection.S))
			)
			{
				return true;
			}
			break;
		default:
			//System.out.println("Invalid Edge Direction");
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
		loc = loc.getNormalizedLocation();
		
		//Check if we are in the sea first
		if(loc.isInSea())
		{
			return false;
		}
		
		//Check if we are attached to a settlement
		settlement = settlement.getNormalizedLocation();
		switch (settlement.getDir().getLengthenedDirection())
		{
			case NorthWest:
				if (
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.North)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NorthWest)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest),EdgeDirection.NorthEast)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.N)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NW)) ||
					loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NW),EdgeDirection.NE))
				)
				{
					return true;
				}
				break;
			case NorthEast:
				if (
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.North)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NorthEast)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast),EdgeDirection.NorthWest)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.N)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc(),EdgeDirection.NE)) ||
						loc.equals(new EdgeLocation(settlement.getHexLoc().getNeighborLoc(EdgeDirection.NE),EdgeDirection.NW))
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
		switch (loc.getDir().getLengthenedDirection())
		{
			case NorthWest:
				if 
				(
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.North))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NorthWest)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest),EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.N))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NW)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NW),EdgeDirection.NE))
				)
				{
					return true;
				}
				break;
			case NorthEast:
				if 
				(
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.North))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NorthEast)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast),EdgeDirection.NorthWest)) ||		
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.N))||
					checkForRoad(new EdgeLocation(loc.getHexLoc(),EdgeDirection.NE)) ||
					checkForRoad(new EdgeLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NE),EdgeDirection.NW))
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
		for(Building settlement : mSettlements)
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
				break;
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
		if (this.hasMonument())
		{
			return true;
		}
		if (
				this.devCards().isEmpty() || // Checks that this player has a dev card
				this.hasPlayedDevCard() // Checks that player hasn't already played a dev card
		)
		{
			return false;
		}
		
		for (DevCard d : this.devCards())
		{
			if (!d.isNew())
			{
				return true;
			}
		}
		
		return false;
	}
	public boolean hasMonument()
	{
		for (DevCard d : this.devCards())
		{
			if (d.type() == DevCardType.MONUMENT)
			{
				return true;
			}
		}
		return false;
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
		if(this.resources().getTotal() <= 7)
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
			if(devCard.type() == DevCardType.YEAR_OF_PLENTY 
					&& !devCard.isNew()
					&& !devCard.hasBeenPlayed())
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
			//System.out.println("hasPlayedDevCard");
			return false;
		}
		for(DevCard devCard : this.devCards())
		{
			if(devCard.type() == DevCardType.ROAD_BUILD 
					&& !devCard.isNew()
					&& !devCard.hasBeenPlayed()
					&& mRoadCount > 1)
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
			if(devCard.type() == DevCardType.SOLDIER 
					&& !devCard.isNew()
					&& !devCard.hasBeenPlayed())
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
			if(devCard.type() == DevCardType.MONOPOLY 
					&& !devCard.isNew()
					&& !devCard.hasBeenPlayed())
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
			if(devCard.type() == DevCardType.MONUMENT
					&& !devCard.hasBeenPlayed())
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
	
	public boolean canAcceptTrade(Trade tradeOffer)
	{
		if (tradeOffer.offer().isEmpty())
		{
			return false;
		}
		
		return mResources.brick() >= -tradeOffer.offer().brick()
				&& mResources.ore() >= -tradeOffer.offer().ore()
				&& mResources.sheep() >= -tradeOffer.offer().sheep()
				&& mResources.wheat() >= -tradeOffer.offer().wheat()
				&& mResources.wood() >= -tradeOffer.offer().wood();
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

	public boolean canMaritimeTrade(List<Port> ports)
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
			Port port = b.getAttachedPort(ports);
			if(port != null)
			{
				PortType portType = port.resource();
				toReturn = haveResourceAmount(portType);
				if(toReturn)
				{
					return toReturn;
				}
			}
		}
		
		for(Building b : mSettlements)
		{
			Port port = b.getAttachedPort(ports);
			if(port != null)
			{
				PortType portType = port.resource();
				toReturn = haveResourceAmount(portType);
				if(toReturn)
				{
					return toReturn;
				}
			}
		}
			
		return toReturn;
	}

	public void endTurn()
	{
		for(DevCard d: mDevCards)
		{
			d.setNew(false);
		}
	}

	public boolean doesSettlementHaveRoadAttached(Building settlement) {
		for(Road road : this.roads())
		{
			if(this.canPlaceRoad(road.location(), settlement.location()))
			{
				return true;
			}
		}		
		return false;
	}

	public boolean isVictim(HexLocation robberHexLoc)
	{
		for(Building b : mSettlements)
		{
			HexLocation sLoc = b.location().getNormalizedLocation().getHexLoc();
			if(robberHexLoc.equals(sLoc) || robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.N)))
			{
				return true;
			}
			else if(b.location().getNormalizedLocation().getDir().getLengthenedDirection() == VertexDirection.NorthWest 
					&& robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.NorthWest)))
			{
				return true;
			}
			else if(b.location().getNormalizedLocation().getDir().getLengthenedDirection() == VertexDirection.NorthEast
					&& robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.NE)))
			{
				return true;
			}
			
		}
		for(Building b : mCities)
		{
			HexLocation sLoc = b.location().getNormalizedLocation().getHexLoc();
			if(robberHexLoc.equals(sLoc) || robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.N)))
			{
				return true;
			}
			else if(b.location().getNormalizedLocation().getDir().getLengthenedDirection() == VertexDirection.NorthWest 
					&& robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.NorthWest)))
			{
				return true;
			}
			else if(b.location().getNormalizedLocation().getDir().getLengthenedDirection() == VertexDirection.NorthEast
					&& robberHexLoc.equals(sLoc.getNeighborLoc(EdgeDirection.NE)))
			{
				return true;
			}			
		}
		return false;
	}

	
}
