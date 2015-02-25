package states;

import models.Index;
import models.ResourceList;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public interface IState 
{
	public boolean isPlayingFree() throws Exception;
	
	public boolean isDisconnectedPlayingAllowed() throws Exception;
	
	public boolean isCancelAllowed() throws Exception;
	
	public boolean canRegister();

	public boolean canBuyDevCard(Index playerIndex);

	public boolean canPlayDevCard(Index playerIndex);

	public boolean canOfferTrade(Index playerIndex);

	public boolean canAcceptTrade(Index playerIndex, ResourceList tradeOffer);

	public boolean canMaritimeTrade(Index playerIndex);

	public boolean canRollDice(Index playerIndex);

	public boolean canDiscard(Index playerIndex);
	
	public boolean canFinishTurn(Index playerIndex);
	
	public boolean canPlayYearOfPlenty(Index playerIndex);
	
	public boolean canPlayRoadBuilder(Index playerIndex);

	public boolean canPlaySoldier(Index playerIndex);

	public boolean canPlayMonopoly(Index playerIndex);

	public boolean canPlayMonument(Index playerIndex);

	public boolean canPlaceRobber(HexLocation newRobberLocation);
	
	public boolean canAffordRoad(Index playerIndex);
	
	public boolean canPlaceRoad(Index playerIndex, EdgeLocation loc);
	
	public boolean canAffordSettlement(Index playerIndex);
	
	public boolean canPlaceSettlement(Index playerIndex, VertexLocation loc);
	
	public boolean canAffordCity(Index playerIndex);
	
	public boolean canPlaceCity(Index playerIndex, VertexLocation loc);
}
