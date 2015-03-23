package server.commands;

import models.IGame;
import models.Index;

public class FinishTurnCommand implements ICommand 
{
	private IGame game;
	private Index playerIndex;
	
	public FinishTurnCommand(IGame game, Index playerIndex)
	{
		this.game = game;
		this.playerIndex = playerIndex;
	}

	@Override
	public void execute()
	{
		game.finishTurn(playerIndex);
	}
}
