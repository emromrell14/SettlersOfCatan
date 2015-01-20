package classes;

public class MessageList {
	private MessageLine[] mLines;
	
	public MessageList(MessageLine[] lines)
	{
		this.mLines = lines;
	}
	
	public MessageLine[] lines()
	{
		return mLines;
	}
}
