package models;

public class Message 
{
	private String mMessage;
	private String mPlayerName;
	
	public Message(String message, String name)
	{
		mMessage = message;
		mPlayerName = name;
	}

	public String message() {
		return mMessage;
	}

	public String playerName() {
		return mPlayerName;
	}	
}
