package JSONmodels;

import models.ResourceList;

import com.google.gson.Gson;

public class ResourceListJSON 
{
	private int brick;
	private int ore;
	private int sheep;
	private int wheat;
	private int wood;
	
	public ResourceListJSON(ResourceList cards) {
		this.brick = cards.brick();
		this.ore = cards.ore();
		this.sheep = cards.sheep();
		this.wheat = cards.wheat();
		this.wood = cards.wood();
	}

	/**
	 * Creates a ResourceList object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New ResourceList object
	 */
	public static ResourceListJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, ResourceListJSON.class);
	}
	
	public models.ResourceList getModel()
	{
		return new models.ResourceList(this.brick, this.ore, this.sheep, this.wheat, this.wood);
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
	
	public boolean isEmpty()
	{
		return brick == 0 && ore == 0 && wheat == 0 && sheep == 0 && wood == 0;
	}

	public int getBrick() 
	{
		return brick;
	}

	/**
	 * @return the ore
	 */
	public int getOre()
	{
		return ore;
	}

	/**
	 * @return the sheep
	 */
	public int getSheep()
	{
		return sheep;
	}

	/**
	 * @return the wheat
	 */
	public int getWheat()
	{
		return wheat;
	}

	/**
	 * @return the wood
	 */
	public int getWood() 
	{
		return wood;
	}

	public models.ResourceList getModelResourceList()
	{
		return new models.ResourceList(brick, ore, sheep, wheat, wood);
	}
}
