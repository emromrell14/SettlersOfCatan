package states;

public class RollingState implements IState
{

	@Override
	public boolean isPlayingFree() throws Exception 
	{
		throw new Exception("Can't play during a RollingState!");
	}

	@Override
	public boolean isDisconnectedPlayingAllowed() throws Exception 
	{
		throw new Exception("Can't play during a RollingState");
	}

	@Override
	public boolean isCancelAllowed() throws Exception 
	{
		throw new Exception("Can't play during a RollingState");
	}

}
