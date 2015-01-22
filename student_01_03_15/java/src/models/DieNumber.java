package models;

public class DieNumber 
{
	private int mValue;
	public DieNumber(int value) throw Exception
	{
		if(index > 6 || index < 1)
		{
			throw new Exception("Not a valid die number.");
		}
		mIndex = index;
	}
	
	public int index()
	{
		return mValue;
	}
}
