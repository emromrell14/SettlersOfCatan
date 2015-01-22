package models;

import java.util.List;

import classes.Index;
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
	public boolean discard()
	{
		return true;
	}
	public int getSoldierCount()
	{
		return mSoldiers;
	}
	public int getVictoryPoints()
	{
		return mVictoryPoints;
	}
	
	
	
	
	public CatanColor color()
	{
		return mColor;
	}
	public boolean discarded()
	{
		return mDiscarded;
	}
	public Number monuments()
	{
		return mMonuments;
	}
	public String name()
	{
		return mName;
	}
	public List<DevCard> newDevCards()
	{
		return mNewDevCards;
	}
	public List<DevCard> oldDevCards()
	{
		return mOldDevCards;
	}
	public Index playerIndex()
	{
		return mPlayerIndex;
	}
	public boolean playerDevCard()
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
	public int roads()
	{
		return mNumRoads;
	}
	public int settlements()
	{
		return mNumSettlements;
	}
	public Number cities()
	{
		return mNumCities;
	}
	public int soldiers()
	{
		return mSoldiers;
	}

}
