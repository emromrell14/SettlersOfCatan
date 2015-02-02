package JSONmodels;

import com.google.gson.Gson;

/**
 * Represents the location of a hex on a hex map
 */
public class HexLocation
{
	private int x;
	private int y;
	
	/**
	 * Creates a HexLocation object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New HexLocation object
	 */
	public static HexLocation fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, HexLocation.class);
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
}

