package JSONmodels;

public class MessageList {
	private MessageLine[] mLines;
	
	/**
	 * Creates a MessageList object from all the variables
	 * 
	 * @param lines
	 * @return New MessageList
	 */
	public MessageList(MessageLine[] lines)
	{
		this.mLines = lines;
	}
	
	/**
	 * Creates a MessageList object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New MessageList object
	 */
	public MessageList(String JSON)
	{
		
	}
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		return "";
	}
}
