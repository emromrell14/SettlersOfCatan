package commands;

import shared.locations.HexLocation;
import models.IGame;
import models.Index;

public class RobPlayerCommand implements ICommand 
{
	private IGame game;
	private Index playerIndex;
	private Index victimIndex;
	private HexLocation hexLocation;
	
	public RobPlayerCommand(IGame game, Index playerIndex, Index victimIndex, HexLocation hexLocation)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.hexLocation = hexLocation;
	}

	@Override
	public void execute()
	{
		game.robPlayer(playerIndex, victimIndex, hexLocation);
	}
}
