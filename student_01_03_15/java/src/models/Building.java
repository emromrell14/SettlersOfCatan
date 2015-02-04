package models;

import shared.definitions.BuildingType;
import shared.locations.VertexLocation;

/** Building class (for settlements or cities)
*
* @author Team 2
*/
public class Building 
{
	/** Index of the player who owns the building piece */
	private Index mOwner; //The index (not id) of the player who owns the piece (0-3).
	/** The location of this piece */
	private VertexLocation mLocation;	//The location of this object.
	/** Holds the three hexes that will give it resources */
	private Hex[] neighborHexes;	//Holds the three hexes that will give it resources
	/** Tells whether this is a settlement or a city */
	private BuildingType mBuildingType = BuildingType.SETTLEMENT;
	
	/** 
	 * Creates a Building model object
	 * 
	 * @param owner 	index of player who owns this piece
	 * @param location	the location of this piece
	 * 
	 * @return a new Building object
	 */
	public Building(Index owner, VertexLocation location)
	{
		this.mOwner = owner;
		this.mLocation = location;
	}
	
	/** 
	 * Gets index of player that owns this piece.
	 * 
	 * @return an integer id corresponding to the player that owns this piece
	 */
	public Index getOwner()
	{
		return mOwner;
	}
	
	/** 
	 * Gets building location.
	 * 
	 * @return the location of this building on the grid
	 */
	public VertexLocation getLocation()
	{
		return mLocation;
	}
	
	public Hex[] getNeighborsHexes() 
	{
		return neighborHexes;
	}
	
	public void setBuildingTypeToCity() //they should start as type settlement, can be changed to city only
	{
		this.mBuildingType = BuildingType.CITY;
	}
	public BuildingType getBuildingType()
	{
		return mBuildingType;
	}
}
