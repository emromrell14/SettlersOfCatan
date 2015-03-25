package server.commands;

import shared.definitions.ResourceType;
import models.IGame;
import models.Index;

public class MaritimeTradeCommand implements ICommand 
{
	private IGame game;
	private Index playerIndex;
	private int ratio;
	private ResourceType inputResource;
	private ResourceType outputResource;
	
	public MaritimeTradeCommand(IGame game, Index playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.inputResource = inputResource;
		this.outputResource = outputResource;
	}

	@Override
	public void execute()
	{
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + " maritime traded for " + outputResource.toString().toLowerCase();
		this.game.addActionToLog(playerIndex, message);
		game.maritimeTrade(playerIndex, ratio, inputResource, outputResource);
	}
}
