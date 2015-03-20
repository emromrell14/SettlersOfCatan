package commands;

import models.IGame;
import models.Index;
import models.ResourceList;

public class OfferTradeCommand implements ICommand
{
	private IGame game;
	private Index playerIndex;
	private Index receiverIndex;
	private ResourceList offer;
	
	public OfferTradeCommand(IGame game, Index playerIndex, Index receiverIndex, ResourceList offer)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.receiverIndex = receiverIndex;
		this.offer = offer;
	}

	@Override
	public void execute()
	{
		game.offerTrade(playerIndex, receiverIndex, offer);
	}
}
