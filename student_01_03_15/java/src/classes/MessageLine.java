package classes;

public class MessageLine {
	private String mMessage;
	private String mSource;
	
	public MessageLine(String message, String source)
	{
		this.mMessage = message;
		this.mSource = source;
	}
	
	public String message()
	{
		return mMessage;
	}
	public String source()
	{
		return mSource;
	}
}
