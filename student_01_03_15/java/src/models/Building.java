package models;

import java.util.List;

import shared.definitions.BuildingType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
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
	 * @pre none
	 * @return an integer id corresponding to the player that owns this piece
	 */
	public Index owner()
	{
		return mOwner;
	}
	
	/** 
	 * Gets building location.
	 * @pre none
	 * @return the location of this building on the grid
	 */
	public VertexLocation location()
	{
		return mLocation;
	}
	
	public Hex[] neighborsHexes() 
	{
		return neighborHexes;
	}
	
	public void setBuildingTypeToCity() //they should start as type settlement, can be changed to city only
	{
		this.mBuildingType = BuildingType.CITY;
	}
	public BuildingType buildingType()
	{
		return mBuildingType;
	}
	/**
	 * 
	 * @param ports
	 * @pre building has a location
	 * @post none
	 * @return List of ports that the building touches
	 */
	public Port getAttachedPort(List<Port> ports) {
		VertexLocation buildingLocation = this.location().getNormalizedLocation();
		for(Port port : ports)
		{
			//Each port should have 2 vertexes
			for(VertexLocation vertex : port.vertices())
			{
				//If the building and vertex are the same hex and direction, return the port
				vertex = vertex.getNormalizedLocation();
				if(buildingLocation.equals(vertex))
				{
					return port;
				}
			}
		}
		return null;
	}

	public boolean isOnHex(Hex h) {
		VertexLocation loc = this.mLocation.getNormalizedLocation();
		switch (loc.getDir().getLengthenedDirection())
		{
		case NorthWest:
		case NorthEast:
			return h.getHexLocation().equals(loc.getHexLoc());
			
		case SouthWest:			
		case SouthEast:
			return h.getHexLocation().equals(loc.getHexLoc().getNeighborLoc(EdgeDirection.North));
				
		case West:
			return h.getHexLocation().equals(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast));
		
		case East:
			return h.getHexLocation().equals(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest));
			
		default:
			return false;
		}
	}
}
