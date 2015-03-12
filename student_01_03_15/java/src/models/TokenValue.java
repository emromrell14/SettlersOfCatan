package models;

public class TokenValue 
{
	private int mValue;
	
	/**
	 * Creates a TokenValue
	 * 
	 * @param value an integer representing the number to be associated with a hex
	 * @return a new TokenValue object
	 */
	public TokenValue(int value)
	{
		mValue = value;
	}
	
	/**
	 * Gets an integer representing the number(chip) to be associated with a hex
	 * @return an integer 2-12
	 */
	public int value()
	{
		return mValue;
	}
}

