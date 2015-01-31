package JSONmodels;

import com.google.gson.Gson;

public class EdgeValue 
{
	private int owner; //The index (not ID) of the player who owns this piece (0-3).
	private EdgeLocation location; //The location of this road.
	
	/**
	 * Creates a EdgeValue object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New EdgeValue object
	 */
	public static EdgeValue fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, EdgeValue.class);
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
