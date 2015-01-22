package classes;

import shared.definitions.CatanColor;

public class Player 
{
	private CatanColor mColor; //The color of this player
	private boolean mDiscarded; //Whether this player has discarded or not already this discard phase
	private Number mMonuments; //How many monuments this player has played.
	private String mName;
	private DevCardList mNewDevCards; //The dev cards the player bought this turn
	private DevCardList mOldDevCards; //The dev cards the player had when the turn started
	private Index mPlayerIndex; //What place in the array is this player? 0-3. It determines their turn order. This is used often everywhere
	private boolean mPlayerDevCard; //Whether the player has played a dev card this turn
	private int mPlayerID; //The unique playerID. This is used to pick the client player apart from the others. This is only used here and in your cookie.
	private ResourceList mResources; //The resource cards this player has.
	private int mRoads;
	private int mSettlements; //How many settlements this player has left to play.
	private int mCities; //How many cities this player has left to play.
	private int mSoldiers;
	private int mVictoryPoints;
	
	/**
	 * Creates a player from all the variables
	 * 
	 * @param color
	 * @param discarded
	 * @param monuments
	 * @param name
	 * @param newDevCards
	 * @param oldDevCards
	 * @param playerIndex
	 * @param playerDevCard
	 * @param playerID
	 * @param resources
	 * @param roads
	 * @param settlements
	 * @param cities
	 * @param soldiers
	 * @param victoryPoints
	 * @return New Player object
	 */
	public Player(CatanColor color, boolean discarded, Number monuments, String name, DevCardList newDevCards, DevCardList oldDevCards, Index playerIndex, boolean playerDevCard, int playerID, ResourceList resources, int roads, int settlements, int cities, int soldiers, int victoryPoints)
	{
		this.mColor = color;
		this.mDiscarded = discarded;
		this.mMonuments = monuments;
		this.mName = name;
		this.mNewDevCards = newDevCards;
		this.mOldDevCards = oldDevCards;
		this.mPlayerIndex = playerIndex;
		this.mPlayerDevCard = playerDevCard;
		this.mPlayerID = playerID;
		this.mResources = resources;
		this.mRoads = roads;
		this.mSettlements = settlements;
		this.mCities = cities;
		this.mSoldiers = soldiers;
		this.mVictoryPoints = victoryPoints;
	}
	
	/**
	 * Creates a Player object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Player object
	 */
	public Player(String JSON)
	{
		
	}
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		
	}
}
