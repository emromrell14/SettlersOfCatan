package server;

import models.Game;
import models.IGame;
import models.Index;
import models.ResourceList;
import server.commands.*;
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
		RollDiceCommand command = new RollDiceCommand(game, playerIndex, rollNum);
		command.execute();
	}

	@Override
	public void executeSendChatMessageCommand(Index playerIndex, String message) 
	{
		SendChatCommand command = new SendChatCommand(game, playerIndex, message);
		command.execute();
	}

	@Override
	public void executeRobPlayerCommand(Index playerIndex, Index victimIndex,HexLocation location) 
	{
		RobPlayerCommand command = new RobPlayerCommand(game, playerIndex, victimIndex, location);
		command.execute();
	}

	@Override
	public void executeFinishTurnCommand(Index playerIndex) 
	{
		FinishTurnCommand command = new FinishTurnCommand(game, playerIndex);
		command.execute();
	}

	@Override
	public void executeBuyDevCardCommand(Index playerIndex) 
	{
		BuyDevCardCommand command = new BuyDevCardCommand(game, playerIndex);
		command.execute();
	}

	@Override
	public void executePlayYearOfPlentyCommand(Index playerIndex,ResourceType resourceType1, ResourceType resourceType2) 
	{
		YearOfPlentyCommand command = new YearOfPlentyCommand(game, playerIndex, resourceType1, resourceType2);
		command.execute();
	}

	@Override
	public void executePlayMonopolyCommand(Index playerIndex, ResourceType resource)
	{
		MonopolyCommand command = new MonopolyCommand(game, playerIndex, resource);
		command.execute();
	}

	@Override
	public void executePlayMonumentCommand(Index playerIndex) 
	{
		MonumentCommand command = new MonumentCommand(game, playerIndex);
		command.execute();
	}

	@Override
	public void executePlayRoadBuildingCommand(Index playerIndex, EdgeLocation loc1, EdgeLocation loc2) 
	{
		RoadBuildingCommand command = new RoadBuildingCommand(game, playerIndex, loc1, loc2);
		command.execute();
	}

	@Override
	public void executePlaySoldierCommand(Index playerIndex, Index victimIndex, HexLocation loc) 
	{
		SoldierCommand command = new SoldierCommand(game, playerIndex, victimIndex, loc);
		command.execute();
	}

	@Override
	public void executeBuildRoadCommand(Index playerIndex, EdgeLocation loc, boolean free) 
	{
		BuildRoadCommand command = new BuildRoadCommand(game, playerIndex, loc, free);
		command.execute();
	}

	@Override
	public void executeBuildSettlementCommand(Index playerIndex,VertexLocation loc, boolean free)
	{
		BuildSettlementCommand command = new BuildSettlementCommand(game, playerIndex, loc, free);
		command.execute();
	}

	@Override
	public void executeBuildCityCommand(Index playerIndex, VertexLocation loc) 
	{
		BuildCityCommand command = new BuildCityCommand(game, playerIndex, loc);
		command.execute();
	}

	@Override
	public void executeOfferTradeCommand(Index playerIndex, Index receiveIndex,ResourceList offer)
	{
		OfferTradeCommand command = new OfferTradeCommand(game, playerIndex, receiveIndex, offer);
		command.execute();
	}

	@Override
	public void executeAcceptTradeCommand(Index playerIndex, boolean willAccept) 
	{
		AcceptTradeCommand command = new AcceptTradeCommand(game, playerIndex, willAccept);
		command.execute();
	}

	@Override
	public void executeMaritimeTradeCommand(Index playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource)
	{
		MaritimeTradeCommand command = new MaritimeTradeCommand(game, playerIndex, ratio, inputResource, outputResource);
		command.execute();
	}

	@Override
	public void executeDiscardCommand(Index playerIndex, ResourceList cards) 
	{
		DiscardCommand command = new DiscardCommand(game, playerIndex, cards);
		command.execute();
	}

	@Override
	public void setGame(IGame game)
	{
		this.game = game;
	}


}
