package states;

import facade.IMasterManager;
import facade.MasterManager;
import models.Index;
import models.ResourceList;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class SetupState implements IState
{
	IMasterManager mMaster;
	Index playerIndex;
	
	public SetupState()
	{
		mMaster = MasterManager.getInstance();
	}

	@Override
	public boolean isPlayingFree() 
	{
		return true;
	}

	@Override
	public boolean isDisconnectedPlayingAllowed() 
	{
		return true;
	}

	@Override
	public boolean isCancelAllowed() 
	{
		return false;
	}

	@Override
	public boolean canRegister() 
	{
		return false;
	}

	@Override
	public boolean canBuyDevCard()
	{
		return false;
	}

	@Override
	public boolean canPlayDevCard()
	{
		return false;
	}

	@Override
	public boolean canOfferTrade() 
	{
		return false;
	}

	@Override
	public boolean canAcceptTrade() 
	{
		return false;
	}

	@Override
	public boolean canMaritimeTrade()
	{
		return false;
	}

	@Override
	public boolean canRollDice() 
	{
		return false;
	}

	@Override
	public boolean canDiscard() 
	{
		return false;
	}

	@Override
	public boolean canFinishTurn() 
	{
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty()
	{
		return false;
	}

	@Override
	public boolean canPlayRoadBuilder()
	{
		return false;
	}

	@Override
	public boolean canPlaySoldier() 
	{
		return false;
	}

	@Override
	public boolean canPlayMonopoly() 
	{
		return false;
	}

	@Override
	public boolean canPlayMonument()
	{
		return false;
	}

	@Override
	public boolean canPlaceRobber(HexLocation newRobberLocation) 
	{
		return false;
	}

	@Override
	public boolean canAffordRoad()
	{
		return true;
	}

	@Override
	public boolean canPlaceRoad(EdgeLocation loc) 
	{
		Index playerIndex = mMaster.getPlayerIndex();
		return mMaster.canPlaceRoad(playerIndex, loc);
	}

	@Override
	public boolean canAffordSettlement() 
	{
		return true;
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation loc) 
	{
		Index playerIndex = mMaster.getPlayerIndex();
		return mMaster.canPlaceSettlement(playerIndex, loc);
	}

	@Override
	public boolean canAffordCity() 
	{
		return false;
	}

	@Override
	public boolean canPlaceCity(VertexLocation loc) 
	{
		return false;
	}
}
