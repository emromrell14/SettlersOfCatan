package models;

import java.util.List;

import shared.definitions.CatanColor;

public interface IPlayer
{
	/** 
	 * Discard a card
	 * 
	 * @return true if the player was able to discard, false otherwise.
	 */
	public boolean discard();
	/** 
	 * Gets the number of soldiers this player has
	 * 
	 * @return the number of soldiers this player has
	 */
	public Number getSoldierCount();
	/** 
	 * Gets the number of roads this player has left to build
	 * 
	 * @return a number between 0 and 15 inclusive
	 */
	public Number getRoadCount();
	/** 
	 * Gets the number of settlements this player has left to build
	 * 
	 * @return a number between 0 and 5 inclusive
	 */
	public Number getSettlementCount();
	/** 
	 * Gets the number of cities this player has left to build
	 * 
	 * @return a number between 0 and 4 inclusive
	 */
	public Number getCityCount();
	/** 
	 * Gets the number of points this player has earned so far
	 * 
	 * @return a number between 0 and 10 inclusive
	 */
	public Number getVictoryPoints();
	/** 
	 * Gets the number of points this player has earned so far
	 * 
	 * @return a number between 0 and 10 inclusive
	 */
	public Number getMonuments();
	/** 
	 * Gets the color associated with this player
	 * 
	 * @return one of the following CatanColor enum types: RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN
	 */
	public CatanColor getColor();
	/** 
	 * Checks to see if a player has discarded
	 * 
	 * @return true if the player has already discarded, false otherwise.
	 */
	public boolean hasDiscarded();
	/** 
	 * Gets the name associated with this player
	 * 
	 * @return a string representing the name of the player
	 */
	public String getName();
	
	public List<DevCard> getNewDevCards();
	public List<DevCard> getOldDevCards();
	/** 
	 * Gets the index associated with this player
	 * This is used to determine the player's turn order.
	 * 
	 * @return an Index object with a value between 0 and 3 inclusive
	 */
	public Index getPlayerIndex();
	/** 
	 * Checks to see if a player has played a development card
	 * 
	 * @return true if the player has played a development card, false otherwise.
	 */
	public boolean hasPlayedDevCard();
	/** 
	 * Gets the index associated with this player
	 * The unique playerID. This is used to pick the client player apart from the others. 
	 * This is used in the cookie.
	 * 
	 * @return an Index object with a value between 0 and 3 inclusive
	 */
	public int getPlayerID();
	/** 
	 * Gets the total amounts of each type of Resource Cards this player has
	 * 
	 * @return a ResourceList object containing quantities of each resource type
	 */
	public ResourceList getResources();
	/** 
	 * Gets a list of all Road objects this player has built
	 * 
	 * @return a List<Road> containing all the Road objects built by this player
	 */
	public List<Road> getRoads();
	/** 
	 * Gets a list of all Settlement objects this player has built
	 * 
	 * @return a List<Building> containing all the Settlement objects built by this player
	 */
	public List<Building> getSettlements();
	/** 
	 * Gets a list of all City objects this player has built
	 * 
	 * @return a List<Building> containing all the City objects built by this player
	 */
	public List<Building> getCities();
}
