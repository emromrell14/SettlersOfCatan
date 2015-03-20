package JSONmodels;

import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import com.google.gson.Gson;

public class VertexLocationJSON 
{
	private int x;
	private int y;
	private String direction;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public VertexLocationJSON() {
		
	}
	
	public VertexLocationJSON(VertexLocation location) 
	{
		this.x = location.getHexLoc().getX();
		this.y = location.getHexLoc().getY();
		this.direction = location.getDir().getShortenedDirection().toString();
	}

	/**
	 * Creates a VertexObject object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New VertexObject object
	 */
	public static VertexLocationJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, VertexLocationJSON.class);
	}
	
	
	public VertexLocation getModelVertexLocation()
	{
		return new VertexLocation(new shared.locations.HexLocation(x, y), VertexDirection.valueOf(direction));
	}
}
