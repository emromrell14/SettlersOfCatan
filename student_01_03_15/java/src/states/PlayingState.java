package states;

public class PlayingState implements IState
{
	@Override
	public boolean isPlayingFree() 
	{
		return false;
	}

	@Override
	public boolean isDisconnectedPlayingAllowed() 
	{
		return false;
	}

	@Override
	public boolean isCancelAllowed() 
	{
		return true;
	}
}
