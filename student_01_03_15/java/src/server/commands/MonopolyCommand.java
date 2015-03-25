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
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + " played a monopoly";
		this.game.addActionToLog(playerIndex, message);
		game.playMonopoly(playerIndex, resource);
	}
}
