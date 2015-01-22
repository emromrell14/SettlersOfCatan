package models;

import classes.Hex;
import classes.Port;

public class Board 
{
	
	private Hex[] mHexes; //A list of all the hexes on the grid - it's only land tiles.
	private Port[] mPorts;
	
	public Board()
	{
		
	}
	
	public Hex[] hexes()
	{
		return mHexes;
	}
	public Port[] ports()
	{
		return mPorts;
	}
	
}
