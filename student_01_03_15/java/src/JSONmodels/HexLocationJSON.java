package JSONmodels;

import shared.locations.HexLocation;

import com.google.gson.Gson;

/**
 * Represents the location of a hex on a hex map
 */
public class HexLocationJSON
{
	private int x;
	private int y;
	
	public HexLocationJSON(HexLocation location) 
	{
		this.x = location.getX();
		this.y = location.getY();
	}

	/**
	 * Creates a HexLocation object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New HexLocation object
	 */
	public static HexLocationJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, HexLocationJSON.class);
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
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	public shared.locations.HexLocation getModelHexLocation()
	{
		return new shared.locations.HexLocation(x, y);
	}
}

