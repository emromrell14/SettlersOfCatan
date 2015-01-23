package JSONmodels;

public class ResourceList 
{
	private int mBrick;
	private int mOre;
	private int mSheep;
	private int mWheat;
	private int mWood;
	
	/**
	 * Creates a ResourceList object from all the variables
	 * 
	 * @param brick
	 * @param ore
	 * @param sheep
	 * @param wheat
	 * @param wood
	 * @return New ResourceList object
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
	 * Creates a ResourceList object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New ResourceList object
	 */
	public ResourceList(String JSON)
	{
		
	}
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		return "";
	}
}
