package commands;

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
		game.playMonument(playerIndex);
	}
}
