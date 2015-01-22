package models;

import shared.definitions.PortType;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;

public class Port 
{
	private PortType mResource; //What type resource this port trades for. If it's omitted, then it's for any resource.
	private HexLocation mLocation; //What hex this port is on. This shows the ocean/non-existent hex to draw the port on.
	private VertexDirection mDirection; //Which edge this port is on.
	
	public Port(PortType resource, HexLocation location, VertexDirection direction, int ratio)
	{
		this.mResource = resource;
		this.mLocation = location;
		this.mDirection = direction;
	}
	
	public PortType resource()
	{
		return mResource;
	}
	public HexLocation location()
	{
		return mLocation;
	}
	public VertexDirection direction()
	{
		return mDirection;
	}
}

