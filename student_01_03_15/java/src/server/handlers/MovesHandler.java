package server.handlers;

import JSONmodels.ResourceListJSON;
import models.IGame;
import models.Index;
import server.IServer;
import server.IServerFacade;
import server.ServerFacade;
import server.JSON.*;
import shared.definitions.ResourceType;

public class MovesHandler extends Handler
{
	IServerFacade serverFacade = new ServerFacade();
	public MovesHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Depending on the requested move, the move will be executed. All of the moves are handled by one class because
	 * they all require the same response in return.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	@Override
	public Response processRequest(Request req) 
	{
		Response res = new Response();
		int gameID = req.getCookie().getGameID();
		int userID = req.getCookie().getPlayerID();
		
		if(gameID == -1 || userID == -1)
		{
			res.setBody("Failed - missing cookie");
			res.setStatusCode(400);
			return res;
		}
		
		serverFacade.setGame(server.getGame(gameID));
		try 
		{
			parseBody(req.getRequestURI(), req.getBody());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			res.setBody("Failed - stupid message error thing::::::" + e.getMessage());
			res.setStatusCode(400);
			return res;
		}
		
		res.setStatusCode(200);
		res.setBody(server.getGameModelJSON(0, gameID));
		server.updateVersion(gameID);
		return res;
	}
	
	public void parseBody(String url, String jsonBody) throws Exception
	{
		switch(url)
		{
		case "moves/rollNumber":
			RollNumberRequest rn = RollNumberRequest.fromJSON(jsonBody);
			serverFacade.executeRollDiceCommand(new Index(rn.getPlayerIndex()), rn.getNumber());
			break;
		case "moves/sendChat":
			SendChatRequest sc = SendChatRequest.fromJSON(jsonBody);
			serverFacade.executeSendChatMessageCommand(new Index(sc.getPlayerIndex()), sc.getContent());
			break;
		case "moves/robPlayer":
			RobPlayerRequest rp = RobPlayerRequest.fromJSON(jsonBody);
			serverFacade.executeRobPlayerCommand(new Index(rp.getPlayerIndex()), rp.getVictimIndex(), rp.getLocation());
			break;
		case "moves/finishTurn":
			FinishTurnRequest f = FinishTurnRequest.fromJSON(jsonBody);
			serverFacade.executeFinishTurnCommand(new Index(f.getPlayerIndex()));
			break;
		case "moves/buyDevCard":
			BuyDevCardRequest b = BuyDevCardRequest.fromJSON(jsonBody);
			serverFacade.executeBuyDevCardCommand(new Index(b.getPlayerIndex()));
			break;
		case "moves/Year_of_Plenty":
			YearOfPlentyRequest y = YearOfPlentyRequest.fromJSON(jsonBody);
			serverFacade.executePlayYearOfPlentyCommand(new Index(y.getPlayerIndex()), ResourceType.valueOf(y.getResource1().toUpperCase()), ResourceType.valueOf(y.getResource1().toUpperCase()));
			break;
		case "moves/Road_Building":
			RoadBuildingRequest rb = RoadBuildingRequest.fromJSON(jsonBody);
			serverFacade.executePlayRoadBuildingCommand(new Index(rb.getPlayerIndex()), rb.getSpot1(), rb.getSpot2());
			break;
		case "moves/Soldier":
			SoldierRequest s = SoldierRequest.fromJSON(jsonBody);
			serverFacade.executePlaySoldierCommand(new Index(s.getPlayerIndex()), s.getVictimIndex(), s.getLocation());
			break;
		case "moves/Monopoly":
			MonopolyRequest m = MonopolyRequest.fromJSON(jsonBody);
			serverFacade.executePlayMonopolyCommand(new Index(m.getPlayerIndex()), ResourceType.valueOf(m.getResource()));
			break;
		case "moves/Monument":
			MonumentRequest mr = MonumentRequest.fromJSON(jsonBody);
			serverFacade.executePlayMonumentCommand(new Index(mr.getPlayerIndex()));
			break;
		case "moves/buildRoad":
			BuildRoadRequest brr = new BuildRoadRequest().fromJSON(jsonBody);
			serverFacade.executeBuildRoadCommand(new Index(brr.getPlayerIndex()), brr.getRoadLocation().getModelEdgeLocation(), brr.isFree());
			break;
		case "moves/buildSettlement":
			BuildSettlementRequest bsr = new BuildSettlementRequest().fromJSON(jsonBody);
			serverFacade.executeBuildSettlementCommand(new Index(bsr.getPlayerIndex()), bsr.getVertexLocation().getModelVertexLocation(), bsr.isFree());
			break;
		case "moves/buildCity":
			BuildCityRequest bcr = new BuildCityRequest().fromJSON(jsonBody);
			serverFacade.executeBuildCityCommand(new Index(bcr.getPlayerIndex()), bcr.getVertexLocation().getModelVertexLocation(), false);
			break;
		case "moves/offerTrade":
			OfferTradeRequest otr = new OfferTradeRequest().fromJSON(jsonBody);
			serverFacade.executeOfferTradeCommand(new Index(otr.getPlayerIndex()), new Index(otr.getReceiver()), otr.getOffer().getModelResourceList());
			break;
		case "moves/acceptTrade":
			AcceptTradeRequest atr = new AcceptTradeRequest().fromJSON(jsonBody);
			serverFacade.executeAcceptTradeCommand(new Index(atr.getPlayerIndex()), atr.isWillAccept());
			break;
		case "moves/maritimeTrade":
			MaritimeTradeRequest mtr = new MaritimeTradeRequest().fromJSON(jsonBody);
			serverFacade.executeMaritimeTradeCommand(new Index(mtr.getPlayerIndex()), mtr.getRatio(), 
					ResourceType.valueOf(mtr.getInputResource()), ResourceType.valueOf(mtr.getOutputResource()));
			break;
		case "moves/discardCards":
			DiscardRequest dr = new DiscardRequest().fromJSON(jsonBody);
			serverFacade.executeDiscardCommand(new Index(dr.getPlayerIndex()), dr.getDiscardedCards().getModelResourceList());
			break;
		default:
			System.out.println("MovesHandler - should never get here. url=" + url);
		}
	}
}
