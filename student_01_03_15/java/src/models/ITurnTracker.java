package models;

public interface ITurnTracker
{
	
	/**
	 * Gets Index of player who has the current turn
	 * 
	 * @return the Index object (with a value between 0 and 3 inclusive) of the player whose turn it is
	 */
	public Index currentTurn();
	/** 
	 * Gets the status of current player
	 * 
	 * @return one of the following Status enum types: ROLLING, ROBBING, PLAYING, DISCARDING, FIRSTROUND, SECONDROUND
	 */
	public Status status();
	/**
	 * Gets Index of player who has the longest road
	 * 
	 * @return the Index object (with a value between 0 and 3 inclusive) of the player with the longest road
	 */
	public Index longestRoad();
	/**
	 * Gets Index of player who has the largest army
	 * 
	 * @return the Index object (with a value between 0 and 3 inclusive) of the player with the largest army
	 */
	public Index largestArmy();

	/**
	 * Ends the current player's turn and advances the current turn index.
	 * 
	 * @pre the current player has rolled and has clicked finish turn
	 * @post will advance the current turn index 
	 */
	public void endTurn();

	/**
	 * Sets the status of the turn
	 * @param a new status
	 * @pre newStatus != null
	 * @post sets the new status of the game
	 */
	public void setStatus(Status newStatus);

	/**
	 * @pre none
	 * @post return a boolean of whether the current player has rolled yet
	 * @return a boolean of whether the current player has rolled yet
	 */
	public boolean hasRolled();

	/**
	 * @param a player's index
	 * @pre playerIndex != null
	 * @post return a boolean of whether the player can roll the dice
	 * @return true playerIndex can roll the dice, false otherwise
	 */
	public boolean canRollDice(Index playerIndex);

	/**
	 * @param a player's index
	 * @pre playerIndex != null
	 * @post return a boolean of whether it is playerIndex's turn
	 * @return true if it is playerIndex's turn, false otherwise
	 */
	public boolean isPlayersTurn(Index playerIndex);

	/**
	 * @param a player's index
	 * @pre i != null
	 * @post sets i to be the new current turn
	 */
	public void setCurrentTurn(Index i);

}
