package models;

public class Message 
{
	private String mMessage;
	private String mPlayerName;
	
	/**
	 * Creates a new Message given all of the values
	 * @param message The text message to be stored
	 * @param name The name of the player who sent the message
	 */
	public Message(String message, String name)
	{
		mMessage = message;
		mPlayerName = name;
	}

	/**
	 * Gets the text message
	 * @return Saved message
	 */
	public String message() {
		return mMessage;
	}

	/**
	 * Gets the player who sent the message
	 * @return Player's name
	 */
	public String playerName() {
		return mPlayerName;
	}	
}
