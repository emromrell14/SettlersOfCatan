package server.commands;

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
		String playerName = game.getPlayer(playerIndex).name();
		String receiverName = game.getPlayer(receiverIndex).name();
		String message = playerName + " offered a trade to " + receiverName;
		this.game.addActionToLog(playerIndex, message);
		game.offerTrade(playerIndex, receiverIndex, offer);
	}
}
