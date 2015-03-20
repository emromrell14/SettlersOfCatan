package commands;

import models.IGame;
import models.Index;
import shared.locations.EdgeLocation;

public class BuildRoadCommand implements ICommand 
{
	private IGame game;
	private Index playerIndex;
	private EdgeLocation edgeLocation;
	private boolean free;
	
	public BuildRoadCommand(IGame game, Index playerIndex, EdgeLocation edgeLocation, boolean free)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.edgeLocation = edgeLocation;
		this.free = free;
	}

	@Override
	public void execute()
	{
		game.buildRoad(playerIndex, edgeLocation, free);
	}
}
