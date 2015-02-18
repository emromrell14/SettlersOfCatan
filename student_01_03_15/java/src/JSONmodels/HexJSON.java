package JSONmodels;

import models.TokenValue;
import shared.definitions.HexType;

import com.google.gson.Gson;

public class HexJSON 
{
	private HexLocationJSON location;
	private String resource; //OPTIONAL. What resource this title gives- it's only here if the tile is not the desert.
	private int number; //OPTIONAL. What number is on this tile. It's omitted if this is a desert hex.
	
	/**
	 * Creates a Hex object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Hex object
	 */
	public static HexJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, HexJSON.class);
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
	 * @return the location
	 */
	public HexLocationJSON getLocation() {
		return location;
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	
	public models.Hex getModelHex()
	{
		String resourceName = (resource == null) ? "DESERT" : resource.toUpperCase();
		return new models.Hex(location.getModelHexLocation(), HexType.valueOf(resourceName), new TokenValue(number));
	}
}
