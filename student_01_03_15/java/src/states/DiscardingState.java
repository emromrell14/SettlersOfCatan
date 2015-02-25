package states;

import models.Index;
import models.ResourceList;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class DiscardingState implements IState
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
	public boolean canRegister() 
	{
		return false;
	}

	@Override
	public boolean canBuyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canOfferTrade() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAcceptTrade() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMaritimeTrade() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRollDice() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDiscard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFinishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayRoadBuilder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaySoldier() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonopoly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonument() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceRobber(HexLocation newRobberLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAffordRoad() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceRoad(EdgeLocation loc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAffordSettlement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation loc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAffordCity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceCity(VertexLocation loc) {
		// TODO Auto-generated method stub
		return false;
	}
}
