package commands;

import models.IGame;
import models.Index;

public class BuyDevCardCommand implements ICommand 
{
	private IGame game;
	private Index playerIndex;
	
	public BuyDevCardCommand(IGame game, Index playerIndex)
	{
		this.game = game;
		this.playerIndex = playerIndex;
	}

	@Override
	public void execute()
	{
		game.buyDevCard(playerIndex);
	}
}
