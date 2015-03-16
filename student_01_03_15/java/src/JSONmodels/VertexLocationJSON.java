package JSONmodels;

import models.Building;
import models.Index;

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
}
