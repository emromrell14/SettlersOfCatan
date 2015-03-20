package commands;

import models.IGame;
import models.Index;
import models.ResourceList;

public class DiscardCommand implements ICommand
{
	private IGame game;
	private Index playerIndex;
	private ResourceList discardedCards;
	
	public DiscardCommand(IGame game, Index playerIndex, ResourceList discardedCards)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.discardedCards = discardedCards;
	}

	@Override
	public void execute()
	{
		game.discardCards(playerIndex, discardedCards);
	}
}
