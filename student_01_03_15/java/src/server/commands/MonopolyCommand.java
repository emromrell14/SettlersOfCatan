package server.commands;

import shared.definitions.ResourceType;
import models.IGame;
import models.Index;

public class MonopolyCommand implements ICommand
{
	private IGame game;
	private Index playerIndex;
	private ResourceType resource;
	
	public MonopolyCommand(IGame game, Index playerIndex, ResourceType resource)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.resource = resource;
	}

	@Override
	public void execute()
	{
		game.playMonopoly(playerIndex, resource);
	}
}
