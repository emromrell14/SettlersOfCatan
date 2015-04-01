package server.handlers;

import java.util.List;

import models.Index;
import server.IServer;
import server.IServerFacade;
import server.ServerFacade;
import server.JSON.AcceptTradeRequest;
import server.JSON.BuildCityRequest;
import server.JSON.BuildRoadRequest;
import server.JSON.BuildSettlementRequest;
import server.JSON.BuyDevCardRequest;
import server.JSON.CommandList;
import server.JSON.DiscardRequest;
import server.JSON.FinishTurnRequest;
import server.JSON.MaritimeTradeRequest;
import server.JSON.MonopolyRequest;
import server.JSON.MonumentRequest;
import server.JSON.OfferTradeRequest;
import server.JSON.RoadBuildingRequest;
import server.JSON.RobPlayerRequest;
import server.JSON.RollNumberRequest;
import server.JSON.SendChatRequest;
import server.JSON.SoldierRequest;
import server.JSON.YearOfPlentyRequest;
import shared.definitions.ResourceType;

public class CommandsHandler extends Handler
{
	IServerFacade serverFacade = new ServerFacade();
	
	public CommandsHandler(IServer server) 
	{
		super.server = server;
	}

	@Override
	public Response processRequest(Request req) 
	{
		int gameID = req.getCookie().getGameID();
		int userID = req.getCookie().getPlayerID();
		
		if(userID == -1 || gameID == -1)
		{
			return new Response(400,"Failed - Missing user and/or game cookie");
		}
		
		if(req.getMethod().equalsIgnoreCase("POST"))
		{
			return processPost(req);
		}
		return processGet(req);
	}
	
	/**
	 * @pre Request != null
	 * @post Executes the specified command list in the current game.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return	The Response for the given Request.
	 */
	private Response processPost(Request req)
	{
		int gameID = req.getCookie().getGameID();
		serverFacade.setGame(server.getGame(gameID));
		CommandList commandList = CommandList.fromJSON(req.getBody());
		try {
			for(String command : commandList.getCommands()) {
				if(command.contains("\"type\": \"rollNumber\"")) {
					RollNumberRequest rn = RollNumberRequest.fromJSON(command);
					serverFacade.executeRollDiceCommand(new Index(rn.getPlayerIndex()), rn.getNumber());
				}
				else if(command.contains("\"type\": \"sendChat\"")) {
					SendChatRequest sc = SendChatRequest.fromJSON(command);
					serverFacade.executeSendChatMessageCommand(new Index(sc.getPlayerIndex()), sc.getContent());
				}
				else if(command.contains("\"type\": \"robPlayer\"")) {
					RobPlayerRequest rp = RobPlayerRequest.fromJSON(command);
					serverFacade.executeRobPlayerCommand(new Index(rp.getPlayerIndex()), new Index(rp.getVictimIndex()), rp.getLocation());
				}
				else if(command.contains("\"type\": \"finishTurn\"")) {
					FinishTurnRequest f = FinishTurnRequest.fromJSON(command);
					serverFacade.executeFinishTurnCommand(new Index(f.getPlayerIndex()));
				}
				else if(command.contains("\"type\": \"buyDevCard\"")) {
					BuyDevCardRequest b = BuyDevCardRequest.fromJSON(command);
					serverFacade.executeBuyDevCardCommand(new Index(b.getPlayerIndex()));
				}
				else if(command.contains("\"type\": \"Year_of_Plenty\"")) {
					YearOfPlentyRequest y = YearOfPlentyRequest.fromJSON(command);
					serverFacade.executePlayYearOfPlentyCommand(new Index(y.getPlayerIndex()), ResourceType.valueOf(y.getResource1().toUpperCase()), ResourceType.valueOf(y.getResource1().toUpperCase()));
				}
				else if(command.contains("\"type\": \"Road_Building\"")) {
					RoadBuildingRequest rb = RoadBuildingRequest.fromJSON(command);
					serverFacade.executePlayRoadBuildingCommand(new Index(rb.getPlayerIndex()), rb.getSpot1(), rb.getSpot2());
				}
				else if(command.contains("\"type\": \"Soldier\"")) {
					SoldierRequest s = SoldierRequest.fromJSON(command);
					serverFacade.executePlaySoldierCommand(new Index(s.getPlayerIndex()), new Index(s.getVictimIndex()), s.getLocation());
				}
				else if(command.contains("\"type\": \"Monopoly\"")) {
					MonopolyRequest m = MonopolyRequest.fromJSON(command);
					serverFacade.executePlayMonopolyCommand(new Index(m.getPlayerIndex()), ResourceType.valueOf(m.getResource().toUpperCase()));
				}
				else if(command.contains("\"type\": \"Monument\"")) {
					MonumentRequest mr = MonumentRequest.fromJSON(command);
					serverFacade.executePlayMonumentCommand(new Index(mr.getPlayerIndex()));
				}
				else if(command.contains("\"type\": \"buildRoad\"")) {
					BuildRoadRequest brr = BuildRoadRequest.fromJSON(command);
					serverFacade.executeBuildRoadCommand(new Index(brr.getPlayerIndex()), brr.getRoadLocation().getModelEdgeLocation(), brr.isFree());
				}
				else if(command.contains("\"type\": \"buildSettlement\"")) {
					BuildSettlementRequest bsr = BuildSettlementRequest.fromJSON(command);
					serverFacade.executeBuildSettlementCommand(new Index(bsr.getPlayerIndex()), bsr.getVertexLocation().getModelVertexLocation(), bsr.isFree());
				}
				else if(command.contains("\"type\": \"buildCity\"")) {
					BuildCityRequest bcr = BuildCityRequest.fromJSON(command);
					serverFacade.executeBuildCityCommand(new Index(bcr.getPlayerIndex()), bcr.getVertexLocation().getModelVertexLocation());
				}
				else if(command.contains("\"type\": \"offerTrade\"")) {
					OfferTradeRequest otr = OfferTradeRequest.fromJSON(command);
					serverFacade.executeOfferTradeCommand(new Index(otr.getPlayerIndex()), new Index(otr.getReceiver()), otr.getOffer().getModelResourceList());
				}
				else if(command.contains("\"type\": \"acceptTrade\"")) {
					AcceptTradeRequest atr = AcceptTradeRequest.fromJSON(command);
					serverFacade.executeAcceptTradeCommand(new Index(atr.getPlayerIndex()), atr.isWillAccept());
				}
				else if(command.contains("\"type\": \"maritimeTrade\"")) {
					MaritimeTradeRequest mtr = MaritimeTradeRequest.fromJSON(command);
					serverFacade.executeMaritimeTradeCommand(new Index(mtr.getPlayerIndex()), mtr.getRatio(), 
					ResourceType.valueOf(mtr.getInputResource().toUpperCase()), ResourceType.valueOf(mtr.getOutputResource().toUpperCase()));
				}
				else if(command.contains("\"type\": \"discardCards\"")) {
					DiscardRequest dr = DiscardRequest.fromJSON(command);
					serverFacade.executeDiscardCommand(new Index(dr.getPlayerIndex()), dr.getDiscardedCards().getModelResourceList());
				}
			}
		} 
		catch(Exception e) 
		{
			return null;
		}
		return new Response(200, server.getGameModelJSON(0, gameID));
	}
	
	/**
	 * @pre Request != null
	 * @post Returns a list of commands that have been executed in the current game.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return	The Response for the given Request.
	 */
	private Response processGet(Request req)
	{
		int gameID = req.getCookie().getGameID();
		List<String> commands = server.getCommands().get(gameID);
		CommandList commandList = new CommandList(commands);
		return new Response(200, commandList.toJSON());
	}
}
