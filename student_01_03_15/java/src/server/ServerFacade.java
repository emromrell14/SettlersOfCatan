package server;

import models.Game;
import models.IGame;
import models.Index;
import models.ResourceList;
import server.commands.ICommand;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class ServerFacade implements IServerFacade 
{	
	private IGame game = new Game();
	
	@Override
	public void executeRollDiceCommand(Index playerIndex, int rollNum)
	{

	}

	@Override
	public void executeSendChatMessageCommand(Index playerIndex, String message) 
	{

	}

	@Override
	public void executeRobPlayerCommand(Index playerIndex, int victimIndex,HexLocation location) 
	{

	}

	@Override
	public void executeFinishTurnCommand(Index playerIndex) 
	{

	}

	@Override
	public void executeBuyDevCardCommand(Index playerIndex) 
	{

	}

	@Override
	public void executePlayYearOfPlentyCommand(Index playerIndex,ResourceType resourceType1, ResourceType resourceType2) 
	{

	}

	@Override
	public void executePlayMonopolyCommand(Index playerIndex,ResourceType resource)
	{

	}

	@Override
	public void executePlayMonumentCommand(Index playerIndex) 
	{
	
	}

	@Override
	public void executePlayRoadBuildingCommand(Index playerIndex,EdgeLocation loc1, EdgeLocation loc2) 
	{

	}

	@Override
	public void executePlaySoldierCommand(Index playerIndex, int victimIndex,HexLocation loc) 
	{

	}

	@Override
	public void executeBuildRoadCommand(Index playerIndex, EdgeLocation loc,boolean free) 
	{

	}

	@Override
	public void executeBuildSettlementCommand(Index playerIndex,VertexLocation loc, boolean free)
	{

	}

	@Override
	public void executeBuildCityCommand(Index playerIndex, VertexLocation loc,boolean free) 
	{

	}

	@Override
	public void executeOfferTradeCommand(Index playerIndex, Index receiveIndex,ResourceList offer)
	{

	}

	@Override
	public void executeAcceptTradeCommand(Index playerIndex, boolean willAccept) 
	{

	}

	@Override
	public void executeMaritimeTradeCommand(Index playerIndex, int ratio,ResourceType inputResource, ResourceType outputResource)
	{

	}

	@Override
	public void executeDiscardCommand(Index playerIndex, ResourceList cards) 
	{

	}

	@Override
	public void setGame(IGame game)
	{
		this.game = game;
	}


}
