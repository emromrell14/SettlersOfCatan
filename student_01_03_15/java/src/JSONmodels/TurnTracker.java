package JSONmodels;

import models.Index;
import models.Status;

import com.google.gson.Gson;

public class TurnTracker 
{
	private int currentTurn; //Whose turn it is (0-3).
	private String status; //What's happening now.
	private int longestRoad; //The index of who has the longest road.
	private int largestArmy; //The index of who has the largest army. (Has to be 3 or more).
	
	/**
	 * Creates a TurnTracker object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New TurnTracker object
	 */
	public static TurnTracker fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, TurnTracker.class);
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
	 * @return the currentTurn
	 */
	public int getCurrentTurn()
	{
		return currentTurn;
	}

	/**
	 * @return the status
	 */
	public String getStatus() 
	{
		return status;
	}

	/**
	 * @return the longestRoad
	 */
	public int getLongestRoad() 
	{
		return longestRoad;
	}

	/**
	 * @return the largestArmy
	 */
	public int getLargestArmy() 
	{
		return largestArmy;
	}
	
	public models.TurnTracker getModelTurnTracker()
	{
		models.TurnTracker t = null;
		try 
		{
			t = new models.TurnTracker(new Index(currentTurn), Status.valueOf(status), new Index(longestRoad), new Index(largestArmy));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return t;
	}
}
