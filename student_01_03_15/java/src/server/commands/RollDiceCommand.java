package server.commands;

import models.IGame;
import models.Index;

public class RollDiceCommand implements ICommand
{
	private IGame game;
	private Index playerIndex;
	private int number;
	
	public RollDiceCommand(IGame game, Index playerIndex, int number)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.number = number;
	}

	@Override
	public void execute()
	{
		try
		{
			game.rollDice(playerIndex, number);
		}
		catch(IllegalStateException i)
		{
			i.printStackTrace();
			return;
		}
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + " rolled a " + number;
		this.game.addActionToLog(playerIndex, message);
	}
}
