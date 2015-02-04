package models;

import java.util.List;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;

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

	public Number getSoldierCount()
	{
		return mSoldiers;
	}
	
	public Number getVictoryPoints()
	{
		return mVictoryPoints;
	}
	public Number getRoadCount()
	{
		return mNumRoads;
	}
	public Number getSettlementCount()
	{
		return mNumSettlements;
	}
	public Number getCityCount()
	{
		return mNumCities;
	}
	public CatanColor getColor()
	{
		return mColor;
	}
	public boolean hasDiscarded()
	{
		return mDiscarded;
	}
	public Number getMonuments()
	{
		return mMonuments;
	}
	public String getName()
	{
		return mName;
	}
	public List<DevCard> getDevCards()
	{
		return mDevCards;
	}
	public Index getPlayerIndex()
	{
		return mPlayerIndex;
	}
	public boolean hasPlayedDevCard()
	{
		return mHasPlayedDevCard;
	}
	public int getPlayerID()
	{
		return mPlayerID;
	}
	public ResourceList getResources()
	{
		return mResources;
	}
	public List<Road> getRoads()
	{
		return mRoads;
	}
	public List<Building> getSettlements()
	{
		return mSettlements;
	}
	public List<Building> getCities()
	{
		return mCities;
	}
	
	
	
	//Functions
	/**
	 * Checks all preconditions for building a new settlement
	 * @pre Player is logged in, has joined a game, has a settlement, 1 wood 1 brick 1 wheat 1 sheep, a valid place to build, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a settlement can be built, false otherwise
	 */
	public boolean canBuildSettlement() 
	{
		ResourceList r = this.getResources();
		if (
				this.getSettlementCount().intValue() < 1 || // Checks that the player has settlements left to build
				r.getBrick() < 1 || r.getWood() < 1 || r.getSheep() < 1 || r.getWheat() < 1) // Checks that player has sufficient resources
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Checks all preconditions for building a city.
	 * @pre Player is logged in, has joined a game, has a city, 3 ore 2 wheat, a settlement to build on, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return	true if a city can be built, false otherwise
	 */
	public boolean canBuildCity() 
	{
		ResourceList r = this.getResources();
		if (
				this.getCityCount().intValue() < 1 || // Checks that you have cities left to build
				this.getSettlements().isEmpty() || // Checks that there is a settlement to build on
				r.getOre() < 3 || r.getWheat() < 2) // Checks that there are enough resources
		{
			return false;
		}
		return true;
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
		ResourceList r = this.getResources();
		if (
				r.getSheep() < 1 || r.getWheat() < 1 || r.getOre() < 1) // Checks that there are enough resources
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
		ResourceList r = this.getResources();
		if (
				this.getDevCards().isEmpty() || // Checks that this player has a dev card
				this.hasPlayedDevCard() || // Checks that player hasn't already played a dev card
				r.getSheep() < 1 || r.getWheat() < 1 || r.getOre() < 1) // Checks that there are enough resources
		{
			return false;
		}
		boolean hasPlayableCard = false;
		for (DevCard d : this.getDevCards())
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
		if(this.hasDiscarded() || this.getResources().getTotal() < 7)
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
		for(DevCard devCard : this.getDevCards())
		{
			if(devCard.getType() == DevCardType.YEAR_OF_PLENTY && !devCard.isNew())
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
		for(DevCard devCard : this.getDevCards())
		{
			if(devCard.getType() == DevCardType.ROAD_BUILD && !devCard.isNew())
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
		for(DevCard devCard : this.getDevCards())
		{
			if(devCard.getType() == DevCardType.SOLDIER && !devCard.isNew())
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
		for(DevCard devCard : this.getDevCards())
		{
			if(devCard.getType() == DevCardType.MONOPOLY && !devCard.isNew())
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
		for(DevCard devCard : this.getDevCards())
		{
			if(devCard.getType() == DevCardType.MONUMENT && !devCard.isNew());
			{
				return true;
			}
		}
		return false;
	}
	public void playMonument()
	{
		
	}
}
