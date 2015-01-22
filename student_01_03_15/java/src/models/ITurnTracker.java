package models;

import classes.Index;
import classes.Status;

public interface ITurnTracker
{
	
	public Index currentTurn();
	public Status status();
	public Index longestRoad();
	public Index largestArmy();

}
