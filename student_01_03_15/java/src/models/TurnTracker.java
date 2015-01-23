package models;

public class TurnTracker implements ITurnTracker
{

	private Index mCurrentTurn; //Whose turn it is (0-3).
	private Status mStatus; //What's happening now.
	private Index mLongestRoad; //The index of who has the longest road.
	private Index mLargestArmy; //The index of who has the largest army. (Has to be 3 or more).
	
	public TurnTracker(Index currentTurn, Status status, Index longestRoad, Index largestArmy)
	{
		this.mCurrentTurn = currentTurn;
		this.mStatus = status;
		this.mLongestRoad = longestRoad;
		this.mLargestArmy = largestArmy;
	}
	
	public Index getCurrentTurn()
	{
		return mCurrentTurn;
	}
	public Status getStatus()
	{
		return mStatus;
	}
	public Index getLongestRoad()
	{
		return mLongestRoad;
	}
	public Index getLargestArmy()
	{
		return mLargestArmy;
	}
}
