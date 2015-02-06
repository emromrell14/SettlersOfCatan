package models;

public class Index 
{
	private int mValue;
	
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
		mValue = index;
	}
	
	/** 
	 * Gets the Index value
	 * 
	 * @return an integer value between 0 and 3 inclusive
	 */
	public int value()
	{
		return mValue;
	}

	public void setIndex(int mValue) 
	{
		this.mValue = mValue;
	}
	
	public boolean equals(Index index)
	{
		if (this.mValue == index.value())
		{
			return true;
		}
		
		return false;
	}
	
}
