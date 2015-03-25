package server.commands;

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
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + " built a road";
		this.game.addActionToLog(playerIndex, message);
		game.buildRoad(playerIndex, edgeLocation, free);
	}
}
