package JSONmodels;

import models.Index;
import shared.locations.EdgeLocation;

public class EdgeValue 
{
	private Index mOwner; //The index (not ID) of the player who owns this piece (0-3).
	private EdgeLocation mLocation; //The location of this road.
	
	/**
	 * Creates an EdgeValue object from all the variables
	 * 
	 * @param owner
	 * @param location
	 * @return New EdgeValue object
	 */
	public EdgeValue(Index owner, EdgeLocation location)
	{
		this.mOwner = owner;
		this.mLocation = location;
	}
	
	/**
	 * Creates an EdgeValue object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New EdgeValue object
	 */
	public EdgeValue(String JSON)
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
