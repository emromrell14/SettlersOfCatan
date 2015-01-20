package classes;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class Hex 
{
	private HexLocation mLocation;
	private HexType mResource; //OPTIONAL. What resource this title gives- it's only here if the tile is not the desert.
	private TokenValue mNumber; //OPTIONAL. What number is on this tile. It's omitted if this is a desert hex.
	
	public Hex(HexLocation location, HexType resource, TokenValue number)
	{
		this.mLocation = location;
		this.mResource = resource;
		this.mNumber = number;
	}
	
	public HexLocation location()
	{
		return mLocation;
	}
	public HexType resource()
	{
		return mResource;
	}
	public TokenValue number()
	{
		return mNumber;
	}
}
