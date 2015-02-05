package models;

public class ResourceList 
{
	private int mBrick = 0;
	private int mOre = 0;
	private int mSheep = 0;
	private int mWheat = 0;
	private int mWood = 0;
	
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
	public ResourceList()
	{
		
	}
	
	public ResourceList(int brick, int ore, int sheep, int wheat, int wood)
	{
		this.mBrick = brick;
		this.mOre = ore;
		this.mSheep = sheep;
		this.mWheat = wheat;
		this.mWood = wood;
	}
	
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
	public void addBrick(int brickNum)
	{
		this.mBrick+=brickNum;
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
	public void addOre(int oreNum)
	{
		this.mOre+=oreNum;
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
	public void addSheep(int sheepNum)
	{
		this.mSheep+=sheepNum;
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
	public void addWheat(int wheatNum)
	{
		this.mWheat+=wheatNum;
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
	public void addWood(int woodNum)
	{
		this.mWood+=woodNum;
	}
	
	public void addBrick(int value)
	{
		mBrick += value;
	}
	
	public void addOre(int value)
	{
		mOre += value;
	}
	
	public void addSheep(int value)
	{
		mSheep += value;
	}
	
	public void addWheat(int value)
	{
		mWheat += value;
	}
	
	public void addWood(int value)
	{
		mWood += value;
	}
	
}
