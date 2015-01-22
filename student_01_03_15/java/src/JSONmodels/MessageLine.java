package classes;

public class MessageLine {
	private String mMessage;
	private String mSource;
	
	/**
	 * Create a MessageLine object from all the variables
	 * 
	 * @param message
	 * @param source
	 * @return New MessageLine object
	 */
	public MessageLine(String message, String source)
	{
		this.mMessage = message;
		this.mSource = source;
	}
	
	/**
	 * Creates a MessageLine object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New MessageLine object
	 */
	public MessageLine(String JSON)
	{
		
	}
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		
	}
}
