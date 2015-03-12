package models;

import shared.definitions.HexType;
import shared.locations.HexLocation;

/** Hex class
*
* @author Team 2
*/
public class Hex 
{
	/** Location of this hex on the grid */
	private HexLocation mLocation;
	/** What resource this hex gives. If it is the desert tile this will return DESERT. */
	private HexType mResource;
	/** The dice roll number associated with this hex.  It has no value if this hex is the desert hex */
	private TokenValue mNumber; //OPTIONAL. What number is on this tile. It's omitted if this is a desert hex.
	
	/** 
	 * Creates a Hex model object
	 * 
	 * @param location	the location of this piece.
	 * @param resource	the type of resource this hex gives when corresponding number is rolled.
	 * @param number	the number associated with this hex.
	 * 
	 * @return a new Hex object
	 */
	public Hex(HexLocation location, HexType resource, TokenValue number)
	{
		this.mLocation = location;
		this.mResource = resource;
		this.mNumber = number;
	}
	
	/** 
	 * Gets hex location
	 * @pre none
	 * @return the location of this hex on the grid
	 */
	public HexLocation location()
	{
		return mLocation;
	}
	/** 
	 * Gets hex resource type
	 * @pre none
	 * @return the type of resource this hex gives.
	 */
	public HexType resource()
	{
		return mResource;
	}
	/** 
	 * Gets hex token number
	 * @pre none
	 * @return the number associated with this hex
	 */
	public TokenValue number()
	{
		return mNumber;
	}
	
	public HexLocation getHexLocation()
	{
		return mLocation;
	}
}
