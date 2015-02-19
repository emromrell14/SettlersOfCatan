package client.resources;

import java.util.*;

import client.base.*;
import client.map.IMapView;
import client.map.MapController;
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
		executeElementAction(ResourceBarElement.ROAD);
		if (master.canAffordCity(master.getPlayerIndex()))
		{
			
		}
		
	}

	@Override
	public void buildSettlement() 
	{
		executeElementAction(ResourceBarElement.SETTLEMENT);
		if (master.canAffordSettlement(master.getPlayerIndex()))
		{
			
		}
		
	}

	@Override
	public void buildCity() 
	{
		executeElementAction(ResourceBarElement.CITY);
		if (master.canAffordCity(master.getPlayerIndex())) 
		{
			
		}
		
	}

	@Override
	public void buyCard() 
	{
		executeElementAction(ResourceBarElement.BUY_CARD);
		if (master.canBuyDevCard(master.getPlayerIndex()))
		{
			
		}
		
	}

	@Override
	public void playCard() 
	{
		executeElementAction(ResourceBarElement.PLAY_CARD);
		
		
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
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		
		
		
	}

}

