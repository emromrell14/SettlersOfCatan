package models;

public class Index 
{
	private int mIndex;
	
	public Index(int index) throws Exception
	{
		if(index > 3 || index < -1)
		{
			throw new Exception("The index was out of range.");
		}
		mIndex = index;
	}
	
	public int index()
	{
		return mIndex;
	}
}
