package models;

public interface ITurnTracker
{
	/**
	 * Gets Index of player who has the current turn
	 * 
	 * @return the Index object (with a value between 0 and 3 inclusive) of the player whose turn it is
	 */
	public Index getCurrentTurn();
	/** 
	 * Gets the status of current player
	 * 
	 * @return one of the following Status enum types: ROLLING, ROBBING, PLAYING, DISCARDING, FIRSTROUND, SECONDROUND
	 */
	public Status getStatus();
	/**
	 * Gets Index of player who has the longest road
	 * 
	 * @return the Index object (with a value between 0 and 3 inclusive) of the player with the longest road
	 */
	public Index getLongestRoad();
	/**
	 * Gets Index of player who has the largest army
	 * 
	 * @return the Index object (with a value between 0 and 3 inclusive) of the player with the largest army
	 */
	public Index getLargestArmy();

}
