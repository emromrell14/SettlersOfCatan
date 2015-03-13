package models;

public class TokenValue 
{
	private int mValue;
	
	/**
	 * Creates a TokenValue
	 * 
	 * @param value an integer representing the number to be associated with a hex
	 * @return a new TokenValue object
	 * @post see return
	 */
	public TokenValue(int value)
	{
		mValue = value;
	}
	
	/**
	 * Gets an integer representing the number(chip) to be associated with a hex
	 * @pre must be in set up
	 * @return an integer 2-12
	 * @post apply integer to hex
	 */
	public int value()
	{
		return mValue;
	}
}

