package states;

public class SetupState implements IState
{

	@Override
	public boolean isPlayingFree() 
	{
		return true;
	}

	@Override
	public boolean isDisconnectedPlayingAllowed() 
	{
		return true;
	}

	@Override
	public boolean isCancelAllowed() 
	{
		return false;
	}

}
