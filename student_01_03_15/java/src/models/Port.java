package models;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class Port 
{
	private PortType mResource; //What type resource this port trades for. If it's omitted, then it's for any resource.
	private HexLocation mLocation; //What hex this port is on. This shows the ocean/non-existent hex to draw the port on.
	private EdgeDirection mDirection; //Which edge this port is on.
	
	private List<VertexLocation> mVertices;
	
	/**
	 * Creates a Port
	 * 
	 * @param resource the type of resource this port trades for. If this is null, the port trades for any resource.
	 * @param location the location of the hex that this port is on
	 * @param direction which edge of the hex this port is on
	 * @param ratio the trade ratio for this port (ex: 3:1, 4:1) represented by an integer
	 * @return a new Port object
	 */
	public Port(PortType resource, HexLocation location, EdgeDirection direction)
	{
		this.mResource = resource;
		this.mLocation = location;
		this.mDirection = direction;
		this.calculateVertices();
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
	 * Sets the type of the resource for which this port trades
	 * 
	 * @param one of the following PortType enum types: WOOD, BRICK, SHEEP, WHEAT, ORE, THREE
	 */
	public void setResource(PortType type)
	{
		this.mResource = type;
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
	public EdgeDirection direction()
	{
		return mDirection;
	}
	
	/**
	 * Gets the vertices adjacent to this port
	 * @return A list of vertices adjacent to this port
	 */
	public List<VertexLocation> vertices()
	{
		return mVertices;
	}
	
	/**
	 * Adds all vertices adjacent to this port to the list of vertices
	 */
	private void calculateVertices()
	{
		this.mVertices = new ArrayList<VertexLocation>();
		switch(this.mDirection.getLengthenedDirection())
		{
		case South:
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthEast));
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthWest));
			break;
		case SouthEast:
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest));
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.West));
			break;
		case SouthWest:
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.SouthWest), VertexDirection.NorthEast));
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.SouthWest), VertexDirection.East));
			break;
		case NorthEast:
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.NorthEast), VertexDirection.West));
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.NorthEast), VertexDirection.SouthWest));
			break;
		case NorthWest:
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.NorthWest), VertexDirection.East));
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.NorthWest), VertexDirection.SouthEast));
			break;
		case North:
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.North), VertexDirection.SouthEast));
			mVertices.add(new VertexLocation(this.mLocation.getNeighborLoc(EdgeDirection.North), VertexDirection.SouthWest));
			break;
		default:
			////System.out.println("Should never get to this. Port.calculateVertices");
		}
	}
	
	public int calculateRatio()
	{
		if(this.mResource == PortType.THREE)
		{
			return 3;
		}
		else
		{
			return 2;
		}
	}
}

