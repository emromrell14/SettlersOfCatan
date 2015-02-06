package models;

import shared.locations.HexLocation;

public class Robber 
{
	private HexLocation mLocation;
	
	public Robber(HexLocation l) 
	{
		mLocation = l;
	}
	
	public HexLocation location()
	{
		return mLocation;
	}
	
}
