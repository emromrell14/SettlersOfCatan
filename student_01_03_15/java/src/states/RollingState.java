package states;

import models.Index;
import models.ResourceList;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class RollingState implements IState
{
	@Override
	public boolean isPlayingFree() throws Exception 
	{
		throw new Exception("Can't play during a RollingState!");
	}

	@Override
	public boolean isDisconnectedPlayingAllowed() throws Exception 
	{
		throw new Exception("Can't play during a RollingState");
	}

	@Override
	public boolean isCancelAllowed() throws Exception 
	{
		throw new Exception("Can't play during a RollingState");
	}

	@Override
	public boolean canRegister() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuyDevCard(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayDevCard(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canOfferTrade(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAcceptTrade(Index playerIndex, ResourceList tradeOffer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMaritimeTrade(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRollDice(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDiscard(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFinishTurn(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayRoadBuilder(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaySoldier(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonopoly(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonument(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceRobber(HexLocation newRobberLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAffordRoad(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceRoad(Index playerIndex, EdgeLocation loc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAffordSettlement(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceSettlement(Index playerIndex, VertexLocation loc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAffordCity(Index playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceCity(Index playerIndex, VertexLocation loc) {
		// TODO Auto-generated method stub
		return false;
	}

}
