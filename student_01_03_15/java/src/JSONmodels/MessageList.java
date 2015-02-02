package JSONmodels;

import com.google.gson.Gson;

public class MessageList 
{
	private MessageLine[] lines;
	
	/**
	 * Creates a MessageList object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New MessageList object
	 */
	public static MessageList fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, MessageList.class);
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the lines
	 */
	public MessageLine[] getLines() {
		return lines;
	}
}
