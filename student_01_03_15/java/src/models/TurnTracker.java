package models;

public class TurnTracker implements ITurnTracker
{
	private Index mCurrentTurn; //Whose turn it is (0-3).
	private Status mStatus; //What's happening now.
	private Index mLongestRoad; //The index of who has the longest road.
	private Index mLargestArmy; //The index of who has the largest army. (Has to be 3 or more).
	
	public TurnTracker()
	{
		try 
		{
			mCurrentTurn = new Index(0);
			mStatus = Status.FIRSTROUND;
			mLongestRoad = new Index(-1);
			mLargestArmy = new Index(-1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public TurnTracker(Index currentTurn, Status status, Index longestRoad, Index largestArmy)
	{
		this.mCurrentTurn = currentTurn;
		this.mStatus = status;
		this.mLongestRoad = longestRoad;
		this.mLargestArmy = largestArmy;
	}
	
	public Index currentTurn()
	{
		return mCurrentTurn;
	}
	public void setCurrentTurn(Index i)
	{
		mCurrentTurn = i;
	}
	public Status status()
	{
		return mStatus;
	}
	public Index longestRoad()
	{
		return mLongestRoad;
	}
	public Index largestArmy()
	{
		return mLargestArmy;
	}
	
	public boolean isPlayersTurn(Index playerIndex)
	{
		return playerIndex.index() == mCurrentTurn.index();
	}
	
	public boolean canRollDice(Index playerIndex)
	{
		return isPlayersTurn(playerIndex) && mStatus == Status.ROLLING;
	}
	
	public boolean hasRolled()
	{
		return mStatus != Status.ROLLING;
	}
	
	public void setStatus(Status newStatus)
	{
		mStatus = newStatus;
	}
	
	public void endTurn()
	{
		mCurrentTurn.setIndex(mCurrentTurn.getIndex()+1);
		if(mCurrentTurn.getIndex() > 3)
		{
			mCurrentTurn.setIndex(0);
		}
	}
}
