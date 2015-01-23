package models;

public class ResourceList 
{
	private int mBrick;
	private int mOre;
	private int mSheep;
	private int mWheat;
	private int mWood;
	private int total;
	
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
	 * Gets the total number of resources (resource cards)
	 * 
	 * @return an integer representing the total quantity of resources (resource cards)
	 */
	public int getTotal()
	{
		//calculate total here
		return total;
	}
	/**
	 * Gets the amount of brick in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of brick in the resource pile
	 */
	public int getBrick()
	{
		return mBrick;
	}
	/**
	 * Gets the amount of ore in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of ore in the resource pile
	 */
	public int getOre()
	{
		return mOre;
	}
	/**
	 * Gets the amount of sheep in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of sheep in the resource pile
	 */
	public int getSheep()
	{
		return mSheep;
	}
	/**
	 * Gets the amount of wheat in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of wheat in the resource pile
	 */
	public int getWheat()
	{
		return mWheat;
	}
	/**
	 * Gets the amount of wood in the resource pile (resource cards)
	 * 
	 * @return an integer representing the amount of wood in the resource pile
	 */
	public int getWood()
	{
		return mWood;
	}
	
}
