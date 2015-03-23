package server.commands;

import shared.definitions.ResourceType;
import models.IGame;
import models.Index;

public class YearOfPlentyCommand implements ICommand 
{
	private IGame game;
	private Index playerIndex;
	private ResourceType resource1;
	private ResourceType resource2;
	
	public YearOfPlentyCommand(IGame game, Index playerIndex, ResourceType resource1, ResourceType resource2)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	@Override
	public void execute()
	{
		game.playYearOfPlenty(playerIndex, resource1, resource2);
	}
}
