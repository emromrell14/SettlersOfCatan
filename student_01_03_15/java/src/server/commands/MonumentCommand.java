package server.commands;

import models.IGame;
import models.Index;

public class MonumentCommand implements ICommand
{
	private IGame game;
	private Index playerIndex;
	
	public MonumentCommand(IGame game, Index playerIndex)
	{
		this.game = game;
		this.playerIndex = playerIndex;
	}

	@Override
	public void execute()
	{
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + " played a monument";
		this.game.addActionToLog(playerIndex, message);
		game.playMonument(playerIndex);
	}
}
