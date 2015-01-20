package classes;

import shared.locations.VertexLocation;

public class VertexObject 
{
	private Index mOwner; //The index (not id) of the player who owns the piece (0-3).
	private VertexLocation mLocation; //The location of this object.
	
	public VertexObject(Index owner, VertexLocation location)
	{
		this.mOwner = owner;
		this.mLocation = location;
	}
	
	public Index owner()
	{
		return mOwner;
	}
	public VertexLocation location()
	{
		return mLocation;
	}
}
