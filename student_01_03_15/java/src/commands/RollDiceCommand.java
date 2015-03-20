package commands;

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
		game.rollDice(playerIndex, number);
	}
}
