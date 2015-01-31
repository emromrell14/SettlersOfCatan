package JSONmodels;

import com.google.gson.Gson;

public class VertexObject 
{
	private int owner; //The index (not id) of the player who owns the piece (0-3).
	private EdgeLocation location; //The location of this object.
	
	/**
	 * Creates a VertexObject object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New VertexObject object
	 */
	public static VertexObject fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, VertexObject.class);
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the owner
	 */
	public int getOwner() {
		return owner;
	}

	/**
	 * @return the location
	 */
	public EdgeLocation getLocation() {
		return location;
	}
}
