package JSONmodels;

import models.Message;

import com.google.gson.Gson;

public class MessageLineJSON 
{
	private String message;
	private String source;
	
	/**
	 * Creates a MessageLine object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New MessageLine object
	 */
	public static MessageLineJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, MessageLineJSON.class);
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	
	public Message getModel()
	{
		return new Message(message, source);
	}
}
