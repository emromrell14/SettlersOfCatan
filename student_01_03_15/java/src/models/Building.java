package models;

import shared.locations.VertexLocation;
import classes.Hex;
import classes.Index;

public class Building 
{
	private Index mOwner; //The index (not id) of the player who owns the piece (0-3).
	private VertexLocation mLocation;	//The location of this object.
	private Hex[] neighborHexes;	//Holds the three hexes that will give it resources
	private BuildingType mBuildingType;
	
	public Building(Index owner, VertexLocation location)
	{
		this.mOwner = owner;
		this.mLocation = location;
	}
	
	public Index owner()
	{
		return mOwner;
	}
	public VertexLocation location()
	{
		return mLocation;
	}
}
