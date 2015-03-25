package server.commands;

import shared.locations.VertexLocation;
import models.IGame;
import models.Index;

public class BuildCityCommand implements ICommand 
{
	private IGame game;
	private Index playerIndex;
	private VertexLocation vertexLocation;
	
	public BuildCityCommand(IGame game, Index playerIndex, VertexLocation vertexLocation)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
	}

	@Override
	public void execute()
	{
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + " built a city";
		this.game.addActionToLog(playerIndex, message);
		game.buildCity(playerIndex, vertexLocation);
	}
}
