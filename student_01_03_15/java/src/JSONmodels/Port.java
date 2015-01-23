package JSONmodels;

import shared.definitions.PortType;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;

public class Port 
{
	private PortType mResource; //What type resource this port trades for. If it's omitted, then it's for any resource.
	private HexLocation mLocation; //What hex this port is on. This shows the ocean/non-existent hex to draw the port on.
	private VertexDirection mDirection; //Which edge this port is on.
	private int mRatio; //The ratio for trade in (ie., if this is 2, then it's a 2:1 port)
	
	/**
	 * Creates a new Port object from all the variables
	 * 
	 * @param resource
	 * @param location
	 * @param direction
	 * @param ratio
	 * @return New Port object
	 */
	public Port(PortType resource, HexLocation location, VertexDirection direction, int ratio)
	{
		this.mResource = resource;
		this.mLocation = location;
		this.mDirection = direction;
		this.mRatio = ratio;
	}
	
	/**
	 * Creates a Port object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Port object
	 */
	public Port(String JSON)
	{
		
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		return "";
	}
}
