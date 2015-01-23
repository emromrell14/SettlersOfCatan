package JSONmodels;

import models.Index;
import shared.locations.VertexLocation;

public class VertexObject 
{
	private Index mOwner; //The index (not id) of the player who owns the piece (0-3).
	private VertexLocation mLocation; //The location of this object.
	
	/**
	 * Creates a VertexObject object from all the variables
	 * 
	 * @param owner
	 * @param location
	 * @return New VertexObject object
	 */
	public VertexObject(Index owner, VertexLocation location)
	{
		this.mOwner = owner;
		this.mLocation = location;
	}
	
	/**
	 * Creates a VertexObject object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New VertexObject object
	 */
	public VertexObject(String JSON)
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
