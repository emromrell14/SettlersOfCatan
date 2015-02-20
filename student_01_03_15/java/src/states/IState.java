package states;

public interface IState 
{
	public boolean isPlayingFree() throws Exception;
	
	public boolean isDisconnectedPlayingAllowed() throws Exception;
	
	public boolean isCancelAllowed() throws Exception;
}
