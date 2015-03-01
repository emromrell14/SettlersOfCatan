package client.discard;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import models.Player;
import models.ResourceList;
import models.Status;
import shared.definitions.*;
import client.base.*;
import client.misc.*;
import facade.MasterManager;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, Observer {

	private IWaitView waitView;
	private MasterManager master;
	private Player player;
	
	private int totalDiscarded;
	private int totalResourcesToDiscard;
	
	private Map<ResourceType, Integer> totalResources = new HashMap<ResourceType, Integer>();
	private Map<ResourceType, Integer> discardedResources = new HashMap<ResourceType, Integer>();

	/**
	 * DiscardController constructor	
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView)
	{		
		super(view);
		this.waitView = waitView;
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}
	
	private void initialize()
	{
		//Load up all of the players resources
		this.player = master.getPlayer();
		for(ResourceType resource : ResourceType.values())
		{
			this.discardedResources.put(resource, 0);
			this.totalResources.put(resource, player.resources().getResource(resource));
			this.getDiscardView().setResourceDiscardAmount(resource, 0);
			this.getDiscardView().setResourceMaxAmount(resource, this.totalResources.get(resource));
			this.getDiscardView().setResourceAmountChangeEnabled(resource, player.resources().getResource(resource) != 0, false);
		}
		
		//Set the total amount of resources that they need to discard
		this.totalResourcesToDiscard = player.resources().getTotal() / 2;
		
		//Set the button to say that they have discarded 0
		this.totalDiscarded = 0;
		
		//Update the button text
		this.updateStateMessage();
	}
	
	private void updateStateMessage()
	{
		//If they have reached the total, allow them to click "DISCARD", otherwise show them how many more they need to discard
		if(this.totalDiscarded == this.totalResourcesToDiscard)
		{
			this.getDiscardView().setStateMessage("DISCARD");
			this.getDiscardView().setDiscardButtonEnabled(true);
		}
		else
		{
			this.getDiscardView().setStateMessage("Choose more cards to discard (" + this.totalDiscarded + "/" + this.totalResourcesToDiscard + ")");
			this.getDiscardView().setDiscardButtonEnabled(false);
		}
	}
	
	private void updateAllIncreaseAndDecreaseButtons()
	{
		for(ResourceType resource : ResourceType.values())
		{
			int amountDiscarded = this.discardedResources.get(resource);
			
			boolean canIncrease = this.totalDiscarded != this.totalResourcesToDiscard && 
					amountDiscarded < this.player.resources().getResource(resource);
			boolean canDecrease = amountDiscarded != 0;
			
			this.getDiscardView().setResourceAmountChangeEnabled(resource, canIncrease, canDecrease);
		}
	}

	@Override
	public void increaseAmount(ResourceType resource) 
	{
		//Update the map of discarded resources
		int new_amount = this.discardedResources.get(resource) + 1;
		this.discardedResources.put(resource, new_amount);

		//Update the number discarded and update the view
		this.totalDiscarded++;
		this.updateStateMessage();
		this.getDiscardView().setResourceDiscardAmount(resource, new_amount);
		this.updateAllIncreaseAndDecreaseButtons();
	}

	@Override
	public void decreaseAmount(ResourceType resource) 
	{
		//Update the map of discarded resources
		int new_amount = this.discardedResources.get(resource) - 1;
		this.discardedResources.put(resource, new_amount);
		
		//Update the number discarded and update the view
		this.totalDiscarded--;
		this.updateStateMessage();
		this.getDiscardView().setResourceDiscardAmount(resource, new_amount);
		this.updateAllIncreaseAndDecreaseButtons();
	}

	@Override
	public void discard() {
		//Get the amount of each resource
		int brick = this.discardedResources.get(ResourceType.BRICK);
		int ore = this.discardedResources.get(ResourceType.ORE);
		int sheep = this.discardedResources.get(ResourceType.SHEEP);
		int wheat = this.discardedResources.get(ResourceType.WHEAT);
		int wood = this.discardedResources.get(ResourceType.WOOD);
		
		//Discard them
		ResourceList resources = new ResourceList(brick, ore, sheep, wheat, wood);
		this.master.discardCards(this.player.playerIndex(), resources);
		
		//Close the modal
		getDiscardView().closeModal();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//If this player is in this game, and if they just hit the discarding state, show the modal and initialize
		if(master.hasJoinedGame && master.getCurrentModel().turnTracker().status().equals(Status.DISCARDING))
		{
			if(!this.getDiscardView().isModalShowing())
			{
				this.getDiscardView().showModal();				
				this.initialize();
			}
		}
		else
		{
			if(this.getDiscardView().isModalShowing())
			{
				this.getDiscardView().closeModal();
			}
		}
	}

}

