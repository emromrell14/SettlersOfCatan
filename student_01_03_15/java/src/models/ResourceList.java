package models;

public class ResourceList 
{
	private int mBrick;
	private int mOre;
	private int mSheep;
	private int mWheat;
	private int mWood;
	private int total;
	
	public ResourceList(int brick, int ore, int sheep, int wheat, int wood)
	{
		this.mBrick = brick;
		this.mOre = ore;
		this.mSheep = sheep;
		this.mWheat = wheat;
		this.mWood = wood;
	}
	
	public int getTotal()
	{
		//calculate total here
		return total;
	}
	public int brick()
	{
		return mBrick;
	}
	public int ore()
	{
		return mOre;
	}
	public int sheep()
	{
		return mSheep;
	}
	public int wheat()
	{
		return mWheat;
	}
	public int wood()
	{
		return mWood;
	}
	
}
