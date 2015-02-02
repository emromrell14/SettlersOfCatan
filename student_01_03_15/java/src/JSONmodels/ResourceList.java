package JSONmodels;

import com.google.gson.Gson;

public class ResourceList 
{
	private int brick;
	private int ore;
	private int sheep;
	private int wheat;
	private int wood;
	
	/**
	 * Creates a ResourceList object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New ResourceList object
	 */
	public static ResourceList fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, ResourceList.class);
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

	public int getBrick() {
		return brick;
	}

	/**
	 * @return the ore
	 */
	public int getOre() {
		return ore;
	}

	/**
	 * @return the sheep
	 */
	public int getSheep() {
		return sheep;
	}

	/**
	 * @return the wheat
	 */
	public int getWheat() {
		return wheat;
	}

	/**
	 * @return the wood
	 */
	public int getWood() {
		return wood;
	}

}
