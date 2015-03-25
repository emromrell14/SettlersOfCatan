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
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + "\'s turn just ended";
		this.game.addActionToLog(playerIndex, message);
		game.finishTurn(playerIndex);
	}
}
