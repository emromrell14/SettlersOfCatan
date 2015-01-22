package classes;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class Hex 
{
	private HexLocation mLocation;
	private HexType mResource; //OPTIONAL. What resource this title gives- it's only here if the tile is not the desert.
	private int mNumber; //OPTIONAL. What number is on this tile. It's omitted if this is a desert hex.
	
	/**
	 * Creates a Hex object from all of the variables
	 * 
	 * @param location
	 * @param resource
	 * @param number
	 * @return New Hex object
	 */
	public Hex(HexLocation location, HexType resource, int number)
	{
		
	}
	
	/**
	 * Creates a Hex object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Hex object
	 */
	public Hex(String JSON)
	{
		
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		
	}
}
