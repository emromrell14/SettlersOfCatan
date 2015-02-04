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
