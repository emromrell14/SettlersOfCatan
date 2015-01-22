package models;

import shared.locations.EdgeLocation;
import classes.Index;

public class Road 
{
	private Index mOwner; //The index (not ID) of the player who owns this piece (0-3).
	private EdgeLocation mLocation; //The location of this road.
	
	public Road(Index owner, EdgeLocation location)
	{
		this.mOwner = owner;
		this.mLocation = location;
	}
	
	public Index owner()
	{
		return mOwner;
	}
	public EdgeLocation location()
	{
		return mLocation;
	}
}
