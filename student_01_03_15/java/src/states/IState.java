package states;

import models.ResourceList;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public interface IState 
{
	public boolean isPlayingFree();
	
	public boolean isDisconnectedPlayingAllowed();
	
	public boolean isCancelAllowed();
	
	public boolean canRegister();

	public boolean canBuyDevCard();

	public boolean canPlayDevCard();

	public boolean canOfferTrade();

	public boolean canAcceptTrade();

	public boolean canMaritimeTrade();

	public boolean canRollDice();

	public boolean canDiscard();
	
	public boolean canFinishTurn();
	
	public boolean canPlayYearOfPlenty();
	
	public boolean canPlayRoadBuilder();

	public boolean canPlaySoldier();

	public boolean canPlayMonopoly();

	public boolean canPlayMonument();

	public boolean canPlaceRobber(HexLocation newRobberLocation);
	
	public boolean canAffordRoad();
	
	public boolean canPlaceRoad(EdgeLocation loc);
	
	public boolean canAffordSettlement();
	
	public boolean canPlaceSettlement(VertexLocation loc);
	
	public boolean canAffordCity();
	
	public boolean canPlaceCity(VertexLocation loc);
}
