package client.resources;

import java.util.*;

import models.Player;
import models.Status;
import states.*;
import client.base.*;
import facade.MasterManager;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, Observer 
{

	private Map<ResourceBarElement, IAction> elementActions;
	private MasterManager master;
	private IState state;
//	private IMapView mapView;

	public ResourceBarController(IResourceBarView view) 
	{

		super(view);
		state = new SetupState();
		elementActions = new HashMap<ResourceBarElement, IAction>();
		master = MasterManager.getInstance();
		master.getModelManager().addObserver(this);
	}

	@Override
	public IResourceBarView getView() 
	{
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) 
	{

		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() 
	{
		if (state.canAffordRoad())
		{
			executeElementAction(ResourceBarElement.ROAD);
			
		}
		
	}

	@Override
	public void buildSettlement() 
	{
		if (state.canAffordSettlement())
		{
			executeElementAction(ResourceBarElement.SETTLEMENT);
		}
		
	}

	@Override
	public void buildCity() 
	{
		if (state.canAffordCity()) 
		{
			executeElementAction(ResourceBarElement.CITY);	
		}
		
	}

	@Override
	public void buyCard() 
	{
		if (state.canBuyDevCard())
		{
			executeElementAction(ResourceBarElement.BUY_CARD);			
		}
		
	}

	@Override
	public void playCard() 
	{
		if (state.canPlayDevCard())
		{
			executeElementAction(ResourceBarElement.PLAY_CARD);
		}
	}
	
	private void executeElementAction(ResourceBarElement element) 
	{
		
		if (elementActions.containsKey(element)) 
		{
			IAction action = elementActions.get(element);
			action.execute();
		}
	}
	
	private void setResourceAmounts()
	{
	
		Player p = master.getPlayer();
		if (p != null)
		{
			getView().setElementAmount(ResourceBarElement.WOOD, p.resources().wood());
			getView().setElementAmount(ResourceBarElement.SHEEP, p.resources().sheep());
			getView().setElementAmount(ResourceBarElement.ORE, p.resources().ore());
			getView().setElementAmount(ResourceBarElement.WHEAT, p.resources().wheat());
			getView().setElementAmount(ResourceBarElement.BRICK, p.resources().brick());
			getView().setElementAmount(ResourceBarElement.SOLDIERS, p.soldierCount());
			getView().setElementAmount(ResourceBarElement.CITY, p.cityCount());
			getView().setElementAmount(ResourceBarElement.SETTLEMENT, p.settlementCount());
			getView().setElementAmount(ResourceBarElement.ROAD, p.roadCount());
		}
	}

	@Override
	public void update(Observable o, Object arg)
	{
			
		// TODO Auto-generated method stub
		
		Status status = master.getCurrentModel().turnTracker().status();
		switch(status)
		{
			case ROBBING:
				state = new RobbingState();
				break;
			case PLAYING:
				state = new PlayingState();
				break;
			case DISCARDING:
				state = new DiscardingState();
				break;
			case ROLLING:
				state = new RollingState();
				break;
			case FIRSTROUND:
				state = new SetupState();
				break;
			case SECONDROUND:
				state = new SetupState();
				break;
			default:
				System.out.println("MapController update() should never get here.");
		}
		
		// SETTING BUILD BUTTONS ENABLED OR NOT, DEPENDING ON IF PLAYER CAN AFFORD THEM
		if (state.canAffordRoad())
		{
			this.getView().setElementEnabled(ResourceBarElement.ROAD, true);
		}
		else
		{
			this.getView().setElementEnabled(ResourceBarElement.ROAD, false);
		}
		//----------------------------------------------
		if (state.canAffordSettlement())
		{
			this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, true);
		}
		else
		{
			this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
		}
		//----------------------------------------------
		if (state.canAffordCity())
		{
			this.getView().setElementEnabled(ResourceBarElement.CITY, true);
		}
		else
		{
			this.getView().setElementEnabled(ResourceBarElement.CITY, false);
		}
		//-----------------------------------------------
		if (state.canBuyDevCard())
		{
			this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, true);
		}
		else
		{
			this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
		}
			
			// SETTING BUILD BUTTONS ENABLED OR NOT, DEPENDING ON IF PLAYER CAN AFFORD THEM
			if (state.canAffordRoad())
			{
				this.getView().setElementEnabled(ResourceBarElement.ROAD, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.ROAD, false);
			}
			//----------------------------------------------
			if (state.canAffordSettlement())
			{
				this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
			}
			//----------------------------------------------
			if (state.canAffordCity())
			{
				this.getView().setElementEnabled(ResourceBarElement.CITY, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.CITY, false);
			}
			//-----------------------------------------------
			if (state.canBuyDevCard())
			{
				this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
			}
			setResourceAmounts();
	}
}

