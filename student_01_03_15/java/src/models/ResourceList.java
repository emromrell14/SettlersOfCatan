package models;

import shared.definitions.ResourceType;

public class ResourceList 
{
	private int mBrick = 0;
	private int mOre = 0;
	private int mSheep = 0;
	private int mWheat = 0;
	private int mWood = 0;
	
	
	public ResourceList()
	{
		
	}
	
	/**
	 * Creates a ResourceList object
	 * 
	 * @param brick the amount of brick in the resource pile
	 * @param ore the amount of ore in the resource pile
	 * @param sheep the amount of sheep in the resource pile
	 * @param wheat the amount of wheat in the resource pile
	 * @param wood the amount of wood in the resource pile
	 * @return a new ResourceList object
	 */
	public ResourceList(int brick, int ore, int sheep, int wheat, int wood)
	{
		this.mBrick = brick;
		this.mOre = ore;
		this.mSheep = sheep;
		this.mWheat = wheat;
		this.mWood = wood;
	}
	
	/**
	 * Adjusts all resources by their given amounts
	 * Pre: all values must be integers.
	 * Post: values will be added to the resource list
	 * @param brick number of brick to add
	 * @param ore number of ore to add
	 * @param sheep number of sheep to add
	 * @param wheat number of wheat to add
	 * @param wood number of wood to add
	 * @return the updated resource list
	 */
	public ResourceList updateResourceList(int brick, int ore, int sheep, int wheat, int wood)
	{
		this.mBrick+=brick;
		this.mOre+=ore;
		this.mSheep+=sheep;
		this.mWheat+=wheat;
		this.mWood+=wood;
		
		return this;
	}
	
	
	
	/**
	 * Gets the total number of resources (resource cards)
	 * 
	 * @return an integer representing the total quantity of resources (resource cards)
	 */
	public int getTotal()
	{
		return this.mBrick + this.mOre + this.mSheep + this.mWheat + this.mWood;
	}
	/**
	 * Gets the amount of brick in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of brick in the resource pile
	 */
	public int brick()
	{
		return mBrick;
	}

	/**
	 * Gets the amount of ore in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of ore in the resource pile
	 */
	public int ore()
	{
		return mOre;
	}

	/**
	 * Gets the amount of sheep in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of sheep in the resource pile
	 */
	public int sheep()
	{
		return mSheep;
	}

	/**
	 * Gets the amount of wheat in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of wheat in the resource pile
	 */
	public int wheat()
	{
		return mWheat;
	}

	/**
	 * Gets the amount of wood in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of wood in the resource pile
	 */
	public int wood()
	{
		return mWood;
	}

	/**
	 * Adjusts the amount of Brick by the given value (increases if value is positive, 
	 * decreases if value is negative).
	 * 
	 * @param value the amount to be added to/subtracted from the brick stock pile
	 */
	public void addBrick(int value)
	{
		mBrick += value;
	}
	
	/**
	 * Adjusts the amount of Ore by the given value (increases if value is positive, 
	 * decreases if value is negative).
	 * 
	 * @param value the amount to be added to/subtracted from the ore stock pile
	 */
	public void addOre(int value)
	{
		mOre += value;
	}
	
	/**
	 * Adjusts the amount of Sheep by the given value (increases if value is positive, 
	 * decreases if value is negative).
	 * 
	 * @param value the amount to be added to/subtracted from the sheep stock pile
	 */
	public void addSheep(int value)
	{
		mSheep += value;
	}
	
	/**
	 * Adjusts the amount of Wheat by the given value (increases if value is positive, 
	 * decreases if value is negative).
	 * 
	 * @param value the amount to be added to/subtracted from the wheat stock pile
	 */
	public void addWheat(int value)
	{
		mWheat += value;
	}
	
	/**
	 * Adjusts the amount of Wood by the given value (increases if value is positive, 
	 * decreases if value is negative).
	 * 
	 * @param value the amount to be added to/subtracted from the wood stock pile
	 */
	public void addWood(int value)
	{
		mWood += value;
	}
	
	/**
	 * Gets the specified resource's amount
	 * @param type the type of resource (Brick, Wood, Sheep, Wheat, Ore)
	 * @return
	 */
	public int getResource(ResourceType type)
	{
		switch (type)
		{
		case BRICK:
			return this.mBrick;
		case WOOD:
			return this.mWood;
		case SHEEP:
			return this.mSheep;
		case WHEAT:
			return this.mWheat;
		case ORE:
			return this.mOre;
		default:
			return -1;
		}
			
	}
	
	
	/**
	 * Checks if there are no resources
	 * 
	 * @return true if there are no resources, false otherwise
	 */
	public boolean isEmpty() 
	{
		if (this.brick() == 0 && this.ore() == 0 && this.wheat() == 0 && this.wood() == 0 && this.sheep() == 0)
		{
			return true;
		}
		return false;
	}
}
