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
			mStatus = Status.WAITINGJOIN;
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
	
	public void setLongestRoad(Index playerIndex)
	{
		this.mLongestRoad = playerIndex;
	}
	
	public Index largestArmy()
	{
		return mLargestArmy;
	}
	
	public void setLargestArmy(Index playerIndex)
	{
		this.mLargestArmy = playerIndex;
	}
	
	public boolean isPlayersTurn(Index playerIndex)
	{
		return playerIndex.value() == mCurrentTurn.value();
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
		if(mStatus.equals(Status.FIRSTROUND))
		{
			if(mCurrentTurn.value() == 3)
			{
//				mCurrentTurn.setIndex(3);
				mStatus = Status.SECONDROUND;
			}
			else
			{
				mCurrentTurn.setIndex(mCurrentTurn.value()+1);
			}
		}
		else if(mStatus.equals(Status.SECONDROUND))
		{
			if(mCurrentTurn.value() == 0)
			{
				mStatus = Status.ROLLING;
			}
			else
			{
				mCurrentTurn.setIndex(mCurrentTurn.value()-1);
			}
		}
		else
		{
			mCurrentTurn.setIndex(mCurrentTurn.value()+1);
			if(mCurrentTurn.value() > 3)
			{
				mCurrentTurn.setIndex(0);
			}
			mStatus = Status.ROLLING;
		}
		System.out.println("currentTurn Index:"+mCurrentTurn.value());
	}

}
