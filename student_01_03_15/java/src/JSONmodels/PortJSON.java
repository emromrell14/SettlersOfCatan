package JSONmodels;

import models.Port;
import shared.definitions.PortType;

import com.google.gson.Gson;

public class PortJSON 
{
	private String resource; //What type resource this port trades for. If it's omitted, then it's for any resource.
	private HexLocationJSON location; //What hex this port is on. This shows the ocean/non-existent hex to draw the port on.
	private String direction; //Which edge this port is on.
	private int ratio; //The ratio for trade in (ie., if this is 2, then it's a 2:1 port)
	
	public PortJSON(Port port) 
	{
		this.resource = port.resource().toString().toLowerCase();
		this.location = new HexLocationJSON(port.location());
		this.direction = port.direction().getShortenedDirection().toString();
		this.ratio = port.calculateRatio();
	}

	/**
	 * Creates a Port object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Port object
	 */
	public static PortJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, PortJSON.class);
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @return the location
	 */
	public HexLocationJSON getLocation() {
		return location;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @return the ratio
	 */
	public int getRatio() {
		return ratio;
	}
	
	public models.Port getModelPort()
	{
		String portType = (resource == null) ? "THREE" : resource.toUpperCase();
		return new models.Port(PortType.valueOf(portType), location.getModelHexLocation(), 
				shared.locations.EdgeDirection.valueOf(direction));
	}
}
