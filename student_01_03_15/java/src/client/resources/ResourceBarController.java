package client.resources;

import java.util.*;

import client.base.*;
import client.map.IMapView;
import client.map.MapController;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, Observer 
{

	private Map<ResourceBarElement, IAction> elementActions;
	private MasterManager master;
//	private IMapView mapView;
	
	public ResourceBarController(IResourceBarView view) 
	{

		super(view);
		
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
		if (master.canAffordCity(master.getPlayerIndex()))
		{
			executeElementAction(ResourceBarElement.ROAD);
			
		}
		
	}

	@Override
	public void buildSettlement() 
	{
		if (master.canAffordSettlement(master.getPlayerIndex()))
		{
			executeElementAction(ResourceBarElement.SETTLEMENT);
		}
		
	}

	@Override
	public void buildCity() 
	{
		if (master.canAffordCity(master.getPlayerIndex())) 
		{
			executeElementAction(ResourceBarElement.CITY);	
		}
		
	}

	@Override
	public void buyCard() 
	{
		if (master.canBuyDevCard(master.getPlayerIndex()))
		{
			executeElementAction(ResourceBarElement.BUY_CARD);			
		}
		
	}

	@Override
	public void playCard() 
	{
		if (master.canPlayDevCard(master.getPlayerIndex()))
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

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
		if(master.hasJoinedGame)
		{
			System.out.println("WE HIT THE RESOURCE CONTROLLER'S OBSERVER UPDATE METHOD");
			
			// SETTING BUILD BUTTONS ENABLED OR NOT, DEPENDING ON IF PLAYER CAN AFFORD THEM
			if (master.canAffordRoad(master.getPlayerIndex()))
			{
				this.getView().setElementEnabled(ResourceBarElement.ROAD, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.ROAD, false);
			}
			//----------------------------------------------
			if (master.canAffordSettlement(master.getPlayerIndex()))
			{
				this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
			}
			//----------------------------------------------
			if (master.canAffordCity(master.getPlayerIndex()))
			{
				this.getView().setElementEnabled(ResourceBarElement.CITY, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.CITY, false);
			}
			//-----------------------------------------------
			if (master.canBuyDevCard(master.getPlayerIndex()))
			{
				this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, true);
			}
			else
			{
				this.getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
			}
			
		}
	}

}

