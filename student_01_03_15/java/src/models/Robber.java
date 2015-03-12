package models;

import shared.locations.HexLocation;

public class Robber 
{
	private HexLocation mLocation;
	
	/**
	 * @pre Game is in RobbingState
	 * 
	 * @param l the HexLocation of where the robber should be
	 * @return a new Robber object
	 */
	
	public Robber(HexLocation l) 
	{
		mLocation = l;
	}
	
	/**
	 * Gets location for the Robber
	 * @pre Game is in RobbingState
	 * 
	 * @return new HexLocation for the robber
	 */
	
	
	public HexLocation location()
	{
		return mLocation;
	}
	
}
