package models;

import shared.locations.EdgeLocation;

public class Road 
{
	private Index mOwner; //The index (not ID) of the player who owns this piece (0-3).
	private EdgeLocation mLocation; //The location of this road.
	
	/**
	 * Creates a Road object
	 * @param owner an Index object corresponding to the player who owns this road
	 * @param location an EdgeLocation object that tells the location of this road
	 * @return a new Road object
	 */
	public Road(Index owner, EdgeLocation location)
	{
		this.mOwner = owner;
		this.mLocation = location;
	}
	
	/**
	 * Gets the index corresponding to the player that owns this road
	 * @return an Index object with a value between 0 and 3 inclusive
	 */
	public Index getOwner()
	{
		return mOwner;
	}
	
	/**
	 * Gets the location of the road on the grid
	 * @return an EdgeLocation object that tells the location of this road
	 */
	public EdgeLocation getLocation()
	{
		return mLocation;
	}
}
