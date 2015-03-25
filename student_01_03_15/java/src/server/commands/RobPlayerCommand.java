package server.commands;

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
		String playerName = game.getPlayer(playerIndex).name();
		String message1 = playerName + " moved the robber";
		this.game.addActionToLog(playerIndex, message1);
		if (!victimIndex.equals(-1))
		{
			String victimName = game.getPlayer(victimIndex).name();
			String message2 = playerName + " robbed " + victimName;
			this.game.addActionToLog(playerIndex, message2);		
		}
		
		game.robPlayer(playerIndex, victimIndex, hexLocation);
	}
}
