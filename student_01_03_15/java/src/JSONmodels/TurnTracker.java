package classes;

public class TurnTracker 
{
	private Index mCurrentTurn; //Whose turn it is (0-3).
	private Status mStatus; //What's happening now.
	private Index mLongestRoad; //The index of who has the longest road.
	private Index mLargestArmy; //The index of who has the largest army. (Has to be 3 or more).
	
	/**
	 * Creates a TurnTracker object from all the variables
	 * 
	 * @param currentTurn
	 * @param status
	 * @param longestRoad
	 * @param largestArmy
	 * @return New TurnTracker object
	 */
	public TurnTracker(Index currentTurn, Status status, Index longestRoad, Index largestArmy)
	{
		this.mCurrentTurn = currentTurn;
		this.mStatus = status;
		this.mLongestRoad = longestRoad;
		this.mLargestArmy = largestArmy;
	}
	
	/**
	 * Creates a TurnTracker object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New TurnTracker object
	 */
	public TurnTracker(String JSON)
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
