package JSONmodels;

import shared.locations.EdgeDirection;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import com.google.gson.Gson;

public class EdgeLocation 
{
	private int x;
	private int y;
	private String direction;
	
	/**
	 * Creates a EdgeLocation object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New EdgeLocation object
	 */
	public static EdgeLocation fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, EdgeLocation.class);
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

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	
	public shared.locations.EdgeLocation getModelEdgeLocation()
	{
		return new shared.locations.EdgeLocation(new shared.locations.HexLocation(x,y), EdgeDirection.valueOf(direction));
	}
	
	public VertexLocation getModelVertexLocation()
	{
		return new VertexLocation(new shared.locations.HexLocation(x, y), VertexDirection.valueOf(direction));
	}
}
