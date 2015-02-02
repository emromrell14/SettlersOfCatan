package JSONmodels;

import com.google.gson.Gson;

public class MessageLine 
{
	private String message;
	private String source;
	
	/**
	 * Creates a MessageLine object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New MessageLine object
	 */
	public static MessageLine fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, MessageLine.class);
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
}
