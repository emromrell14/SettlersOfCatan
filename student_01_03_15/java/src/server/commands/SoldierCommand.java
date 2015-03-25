package server.commands;

import shared.locations.HexLocation;
import models.IGame;
import models.Index;

public class SoldierCommand implements ICommand 
{
	private IGame game;
	private Index playerIndex;
	private Index victimIndex;
	private HexLocation hexLocation;
	
	public SoldierCommand(IGame game, Index playerIndex, Index victimIndex, HexLocation hexLocation)
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
		String message = playerName + " played a soldier";
		this.game.addActionToLog(playerIndex, message);
		game.playSoldier(playerIndex, victimIndex, hexLocation);
	}
}
