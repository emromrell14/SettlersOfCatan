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
	 * @post see return
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
	 * @post robber is moved to location
	 */
	public HexLocation location()
	{
		return mLocation;
	}
	
}
