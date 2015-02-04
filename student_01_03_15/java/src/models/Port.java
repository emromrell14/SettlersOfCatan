package models;

import shared.definitions.PortType;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;

public class Port 
{
	private PortType mResource; //What type resource this port trades for. If it's omitted, then it's for any resource.
	private HexLocation mLocation; //What hex this port is on. This shows the ocean/non-existent hex to draw the port on.
	private VertexDirection mDirection; //Which edge this port is on.
	
	/**
	 * Creates a Port
	 * 
	 * @param resource the type of resource this port trades for. If this is null, the port trades for any resource.
	 * @param location the location of the hex that this port is on
	 * @param direction which edge of the hex this port is on
	 * @param ratio the trade ratio for this port (ex: 3:1, 4:1) represented by an integer
	 * @return a new Port object
	 */
	public Port(PortType resource, HexLocation location, VertexDirection direction, int ratio)
	{
		this.mResource = resource;
		this.mLocation = location;
		this.mDirection = direction;
	}
	/**
	 * Gets the type of the resource for which this port trades
	 * 
	 * @return one of the following PortType enum types: WOOD, BRICK, SHEEP, WHEAT, ORE, THREE
	 */
	public PortType resource()
	{
		return mResource;
	}
	/**
	 * Gets the location of the hexagon upon which this port lies
	 * 
	 * @return a HexLocation object
	 */
	public HexLocation location()
	{
		return mLocation;
	}
	/**
	 * Gets the edge of the hexagon this port sits on
	 * 
	 * @return one of the following VertexDirection enum types: West, NorthWest, NorthEast, East, SouthEast, SouthWest
	 */
	public VertexDirection direction()
	{
		return mDirection;
	}
}

