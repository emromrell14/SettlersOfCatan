package states;

import facade.IMasterManager;
import facade.MasterManager;
import models.Index;
import models.ResourceList;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class PlayingState implements IState
{
	IMasterManager mMaster;
	
	public PlayingState()
	{
		mMaster = MasterManager.getInstance();
	}
	
	@Override
	public boolean isPlayingFree() 
	{
		return false;
	}

	@Override
	public boolean isDisconnectedPlayingAllowed() 
	{
		return false;
	}

	@Override
	public boolean isCancelAllowed() 
	{
		return true;
	}

	@Override
	public boolean canRegister() 
	{
		return false;
	}

	@Override
	public boolean canBuyDevCard() 
	{
		Index playerIndex = mMaster.getPlayerIndex();
		return mMaster.canBuyDevCard(playerIndex);
	}

	@Override
	public boolean canPlayDevCard() 
	{
		Index playerIndex = mMaster.getPlayerIndex();
		return mMaster.canPlayDevCard(playerIndex);
	}

	@Override
	public boolean canOfferTrade()
	{
		Index playerIndex = mMaster.getPlayerIndex();
		return mMaster.canOfferTrade(playerIndex);
	}

	@Override
	public boolean canAcceptTrade()
	{
		Index playerIndex = mMaster.getPlayerIndex();
		return mMaster.canAcceptTrade(playerIndex);
	}

	@Override
	public boolean canMaritimeTrade() {
		Index playerIndex = mMaster.getPlayerIndex();
		return mMaster.canMaritimeTrade(playerIndex);
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
