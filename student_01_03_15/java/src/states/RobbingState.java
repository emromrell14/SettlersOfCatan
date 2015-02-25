package states;

import facade.IMasterManager;
import facade.MasterManager;
import models.Index;
import models.ResourceList;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class RobbingState implements IState
{
	IMasterManager mMaster;
	
	public RobbingState()
	{
		mMaster = MasterManager.getInstance();
	}
	
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
		return mMaster.canPlaceRobber(newRobberLocation);
	}

	@Override
	public boolean canAffordRoad() 
	{
		return false;
	}

	@Override
	public boolean canPlaceRoad(EdgeLocation loc)
	{
		return false;
	}

	@Override
	public boolean canAffordSettlement() 
	{
		return false;
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation loc)
	{
		return false;
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
