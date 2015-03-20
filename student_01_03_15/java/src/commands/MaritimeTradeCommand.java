package commands;

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
		game.maritimeTrade(playerIndex, ratio, inputResource, outputResource);
	}
}
