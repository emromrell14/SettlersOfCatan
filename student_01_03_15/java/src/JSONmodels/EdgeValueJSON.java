package JSONmodels;

import models.Index;
import models.Road;

import com.google.gson.Gson;

public class EdgeValueJSON 
{
	private int owner; //The index (not ID) of the player who owns this piece (0-3).
	private EdgeLocationJSON location; //The location of this road.
	
	public EdgeValueJSON(Road road) 
	{
		this.owner = road.owner().value();
		this.location = new EdgeLocationJSON(road.location());
	}

	/**
	 * Creates a EdgeValue object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New EdgeValue object
	 */
	public static EdgeValueJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, EdgeValueJSON.class);
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
	public EdgeLocationJSON getLocation() {
		return location;
	}
	
	public Road getModelRoad()
	{
		Road r = null;
		try 
		{
			r = new Road(new Index(owner),location.getModelEdgeLocation());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return r;
	}
}
