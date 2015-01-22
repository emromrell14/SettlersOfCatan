package models;

import java.util.List;

import shared.definitions.CatanColor;

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
	private List<DevCard> mOldDevCards;
	private List<DevCard> mNewDevCards;
	
	
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
	
	/*
	public Number getVictoryPoints();
	public Number getMonuments();
	public boolean isDiscarded();
	public String getName();
	public List<DevCard> getNewDevCards();
	public List<DevCard> getOldDevCards();
	public Index getPlayerIndex();
	public boolean getPlayerDevCard();
	public int getPlayerID();
	public ResourceList getResources();
	public List<Road> getRoads();
	public List<Building> getSettlements();
	public List<Building> getCities();
	*/
	
	public boolean discard()
	{
		return true;
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
	public List<DevCard> getNewDevCards()
	{
		return mNewDevCards;
	}
	public List<DevCard> getOldDevCards()
	{
		return mOldDevCards;
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

}
