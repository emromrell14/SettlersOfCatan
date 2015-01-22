package models;

public class Index 
{
	private int mIndex;
	
	/** 
	 * Creates an Index model object
	 * 
	 * @return a new Index object
	 */
	public Index(int index) throws Exception
	{
		if(index > 3 || index < -1)
		{
			throw new Exception("The index was out of range.");
		}
		mIndex = index;
	}
	
	/** 
	 * Getter for Index value
	 * 
	 * @return an integer value between 0 and 3 inclusive
	 */
	public int getIndex()
	{
		return mIndex;
	}
}
