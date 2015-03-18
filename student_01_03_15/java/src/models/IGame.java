package models;

import java.util.List;

import JSONmodels.MessageListJSON;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public interface IGame
{	
	/**
	 * Return the color of player with the specified playerID
	 * @pre playerID is valid
	 * @post CatanColor is valid
	 * @param playerID
	 * @return
	 */
	CatanColor getPlayerColor(int playerID);

	/**
	 * Return the color of the player with the specified name
	 * @param playerName
	 * @return
	 */
	CatanColor getPlayerColor(String playerName);

	/**
	 * Calls the end turn method of whatever player is the current turn
	 * @pre Game must have started
	 * @post Turn Tracker will have updated the current turn to the next player
	 */
	void endTurn();

	Board board();

	void setBoard(Board b);

	List<Player> players();

	/**
	 * Returns the player with the specified ID
	 * @pre playerID is valid
	 * @post none
	 * @param playerID
	 * @return
	 */
	Player getPlayer(int playerID);

	/**
	 * Returns the player with the specified index
	 * @pre non null playerIndex
	 * @post none
	 * @param playerIndex
	 * @return
	 */
	Player getPlayer(Index playerIndex);

	/**
	 * Returns the index of a player with a specified ID
	 * @pre playerID is valid
	 * @pre playerID is in this game
	 * @post none
	 * @param playerID
	 * @return
	 */
	Index getPlayerIndex(int playerID);

	/**
	 * Returns the ID of the player with a specified index
	 * @pre playerIndex is valid
	 * @post none
	 * @param playerIndex
	 * @return
	 */
	int getPlayerID(Index playerIndex);

	/**
	 * Adds a player to this game
	 * @pre Game has not already started
	 * @pre Player is non null
	 * @post Player count will be incremented by 1
	 * @post Player will now be in this game
	 * @param p
	 */
	void addPlayer(Player p);

	TurnTracker turnTracker();

	void setTurnTracker(TurnTracker t);
	
	ResourceList bank();
	
	void setBank(ResourceList r);

	List<DevCard> devCards();

	void setDevCards(List<DevCard> devCards);

	int version();

	void setVersion(int v);

	Index winner();

	void setWinner(int w);

	List<Message> chat();

	void setChat(List<Message> m);

	List<Message> log();

	void setLog(List<Message> m);

	Robber robber();

	void setRobber(Robber r);

	Trade trade();

	void setTrade(Trade model);

	/**
	 * Returns whether or not a player can offer a trade
	 * @pre playerIndex is valid
	 * @post none
	 * @param playerIndex
	 * @return
	 */
	boolean canOfferTrade(Index playerIndex);

	/**
	 * Returns all the players who are residing on a specified hex location
	 * @pre Game must have started
	 * @pre Hex location is valid
	 * @post none
	 * @param hexLoc
	 * @return
	 */
	List<Player> getRobbingVictims(HexLocation hexLoc);

	/**
	 * Returns the index value of the player with the largest army
	 * @return
	 */
	int getLargestArmyIndex();

	/**
	 * Returns the index value of the player with the longest road
	 * @return
	 */
	int getLongestRoadIndex();
	
	/**
	 * Adds a chat message to the message board
	 * @param message
	 * @param playerIndex
	 */
	void sendChat(String message, Index playerIndex) throws IllegalStateException;
	
	/**
	 * Allocates resources and changes turn tracker status
	 * @param playerIndex
	 * @param number
	 */
	void rollDice(Index playerIndex, int number) throws IllegalStateException;

	/**
	 * moves robber, take resource from victim, give resource to player
	 * change turn tracker status to PLAYING
	 * @param playerIndex
	 * @param victimIndex
	 * @param loc
	 */
	public void robPlayer(Index playerIndexInt, Index victimIndex, HexLocation loc) throws IllegalStateException;

	
	/**
	 * changes turn tracker's current turn to next player
	 * sets status to rolling
	 * sets new dev cards to old
	 * 
	 * @param playerIndex
	 */
	public void finishTurn(Index playerIndexInt) throws IllegalStateException;

	/**
	 * Player gains a new card, if monument it will be old, new otherwise
	 * Decrement 1 Wheat, 1 Ore, 1 Sheep from player's resources
	 * 
	 * @param playerIndex
	 */
	public void buyDevCard(Index playerIndex);
	
	/**
	 * Player will gain one of each of the specified cards types
	 * Gets rid of their year of plenty card
	 * 
	 * @param playerIndex
	 * @param resource1
	 * @param resource2
	 */
	public void playYearOfPlenty(Index playerIndex, ResourceType resource1, ResourceType resource2);
	
	/**
	 * Plays two of the player's unused roads on the specified location
	 * Adds the two roads to the map
	 * Checks for longest road
	 * 
	 * @param playerIndex
	 * @param spot1
	 * @param spot2
	 */
	public void playRoadBuilding(Index playerIndex, EdgeLocation spot1, EdgeLocation spot2);
	
	public void playSoldier(Index playerIndex, Index victimIndex, HexLocation location);
	
	public void playMonopoly(Index playerIndex, ResourceType resource);
	
	public void playMonument(Index playerIndex);
	
	public void buildRoad(Index playerIndex, EdgeLocation roadLocation, boolean free);
	
	public void buildSettlement(Index playerIndex, VertexLocation vertexLocation, boolean free);
	
	public void buildCity(Index playerIndex, VertexLocation vertexLocation);
	
	public void offerTrade(Index playerIndex, Index receiverIndex, ResourceList offer);
	
	public void acceptTrade(Index playerIndex, boolean willAccept);
	
	public void maritimeTrade(Index playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource);
	
	public void discardCards(Index playerIndex, ResourceList discardedCards);

}
