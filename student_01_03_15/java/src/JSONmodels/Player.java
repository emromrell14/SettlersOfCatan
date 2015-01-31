package JSONmodels;

import com.google.gson.Gson;

public class Player 
{
	private int cities; //How many cities this player has left to play.
	private String color; //The color of this player
	private boolean discarded; //Whether this player has discarded or not already this discard phase
	private Number monuments; //How many monuments this player has played.
	private String name;
	private DevCardList newDevCards; //The dev cards the player bought this turn
	private DevCardList oldDevCards; //The dev cards the player had when the turn started
	private int playerIndex; //What place in the array is this player? 0-3. It determines their turn order. This is used often everywhere
	private boolean playedDevCard; //Whether the player has played a dev card this turn
	private int playerID; //The unique playerID. This is used to pick the client player apart from the others. This is only used here and in your cookie.
	private ResourceList resources; //The resource cards this player has.
	private int roads;
	private int settlements; //How many settlements this player has left to play.
	private int soldiers;
	private int victoryPoints;
	
	/**
	 * Creates a Player object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Player object
	 */
	public static Player fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, Player.class);
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return if they discarded this round
	 */
	public boolean hasDiscarded() {
		return discarded;
	}

	/**
	 * @return the monuments
	 */
	public Number getMonuments() {
		return monuments;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the newDevCards
	 */
	public DevCardList getNewDevCards() {
		return newDevCards;
	}

	/**
	 * @return the oldDevCards
	 */
	public DevCardList getOldDevCards() {
		return oldDevCards;
	}

	/**
	 * @return the playerIndex
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * @return the playerDevCard
	 */
	public boolean hasPlayedDevCard() {
		return playedDevCard;
	}

	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * @return the resources
	 */
	public ResourceList getResources() {
		return resources;
	}

	/**
	 * @return the roads
	 */
	public int getRoads() {
		return roads;
	}

	/**
	 * @return the settlements
	 */
	public int getSettlements() {
		return settlements;
	}

	/**
	 * @return the cities
	 */
	public int getCities() {
		return cities;
	}

	/**
	 * @return the soldiers
	 */
	public int getSoldiers() {
		return soldiers;
	}

	/**
	 * @return the victoryPoints
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}
}
