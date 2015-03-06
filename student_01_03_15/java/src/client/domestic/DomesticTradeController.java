package client.domestic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import models.Index;
import models.Player;
import models.ResourceList;
import models.Trade;
import shared.definitions.*;
import client.base.*;
import client.catan.TradePanel;
import client.data.PlayerInfo;
import client.misc.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, Observer {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private IMasterManager master;
	private TradePanel waitingPanel;
	
	private boolean playersInitialized = false;
	private Index sendOfferTo;
	private Map<ResourceType, Integer> resources = new HashMap<ResourceType, Integer>();
	private Map<ResourceType, ResourceOption> options = new HashMap<ResourceType, ResourceOption>();	

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) 
	{

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
//		this.waitingPanel = new TradePanel();
	}
	
	public IDomesticTradeView getTradeView() 
	{
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() 
	{
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) 
	{
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() 
	{
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) 
	{
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() 
	{
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) 
	{
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() 
	{		
		this.tradeOverlay.showModal();
		
		
		//Set all resources to disabled (since they are in the "none" stage)
		this.tradeOverlay.initializeVisibility();
		
		//If this is the first trade, then initialize the players
		if(!this.playersInitialized)
		{
			this.initializePlayers();
			this.playersInitialized = true;
		}
		//Set the resources all to 0 and option to NONE
		this.resetAllResources();
		this.tradeOverlay.reset();
	}
	
	private void initializePlayers()
	{
		//Set all active players (excluding yourself) in the list
		List<Player> players = master.getCurrentModel().players();
		List<PlayerInfo> information = new ArrayList<PlayerInfo>();
		for(Player player : players) 
		{
			if(player.playerID() != master.getPlayerID())
			{
				PlayerInfo info = new PlayerInfo();
				info.setId(player.playerID());
				info.setPlayerIndex(player.playerIndex().value());
				info.setName(player.name());
				info.setColorCatan(player.color());
				information.add(info);
			}
		}
		
		PlayerInfo[] infoArray = information.toArray(new PlayerInfo[information.size()]);
		this.tradeOverlay.setPlayers(infoArray);
	}
	
	private void resetAllResources() 
	{
		//Clear all of the resources
		for(ResourceType resource : ResourceType.values())
		{
			this.resources.put(resource, 0);
			this.options.put(resource, ResourceOption.NONE);
//			this.unsetResource(resource);
		}
	}
	
	private int getResourceCount(Player player, ResourceType resource)
	{
		switch (resource) {
			case BRICK:
				return player.resources().brick();
			case WOOD:
				return player.resources().wood();
			case WHEAT:
				return player.resources().wheat();
			case SHEEP:
				return player.resources().sheep();
			case ORE:
				return player.resources().ore();
			default:
				return 0;
		}
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) 
	{		
		//Decrement the map that we have
		int new_amount = this.resources.get(resource) - 1;
		this.resources.put(resource, new_amount);
		
		//Set the view with the correct value (as a string)
		this.tradeOverlay.setResourceAmount(resource, new_amount + "");
		
		//Set the overlay with enabled/disabled arrows
		this.tradeOverlay.setResourceAmountChangeEnabled(resource, true, new_amount > 0);
		
		//Update the button
		this.tradeOverlay.setTradeEnabled(areWeReadyToTrade());
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) 
	{
		//Increment the map that we have
		int new_amount = this.resources.get(resource) + 1;
		this.resources.put(resource, new_amount);
		
		//Set the view with the correct value (as a string)
		this.tradeOverlay.setResourceAmount(resource, new_amount + "");
		
		//Figure out if they should be able to increment this resource
		switch(options.get(resource))
		{
			case RECEIVE:
				//When requesting to receive something, you can always ask for more, and because we just increased, we can always decrease as well
				this.tradeOverlay.setResourceAmountChangeEnabled(resource, true, true);
				break;
			case SEND:
				//When requesting to send something, you can only send as much as you have.
				int resourceAmount = this.getResourceCount(master.getPlayer(), resource);
				this.tradeOverlay.setResourceAmountChangeEnabled(resource, resourceAmount > new_amount, true);
				break;
			default:
				//Should never get here, because the buttons shouldn't be visible	
		}
		
		//Update the button
		this.tradeOverlay.setTradeEnabled(areWeReadyToTrade());
	}

	@Override
	public void sendTradeOffer() 
	{
		//Create the resourceList to offer
		ResourceList resourceList = createResourceList();
		//System.out.println("Sending offer to playerIndex "+ this.sendOfferTo.value() + " for "+ resourceList.brick() + " brick, " + resourceList.ore() + " ore, " + resourceList.sheep() + " sheep, " + resourceList.wheat() + " wheat, and " + resourceList.wood() + " wood.");
		master.offerTrade(master.getPlayerIndex(), resourceList, this.sendOfferTo);
		
		// Reset properties of tradeOverlay and who you are sending too
		sendOfferTo = null;
		getTradeOverlay().reset();
		getTradeOverlay().closeModal();
		getWaitOverlay().showModal();
	}
	
	private ResourceList createResourceList()
	{
		int brick = resources.get(ResourceType.BRICK);
		if(options.get(ResourceType.BRICK) == ResourceOption.RECEIVE)
		{
			brick = -brick;
		}
		int ore = resources.get(ResourceType.ORE);
		if(options.get(ResourceType.ORE) == ResourceOption.RECEIVE) 
		{
			ore = -ore;
		}
		int sheep = resources.get(ResourceType.SHEEP);
		if(options.get(ResourceType.SHEEP) == ResourceOption.RECEIVE) 
		{
			sheep = -sheep;
		}
		int wheat = resources.get(ResourceType.WHEAT);
		if(options.get(ResourceType.WHEAT) == ResourceOption.RECEIVE) 
		{
			wheat = -wheat;
		}
		int wood = resources.get(ResourceType.WOOD);
		if(options.get(ResourceType.WOOD) == ResourceOption.RECEIVE)
		{
			wood = -wood;
		}
		
		return new ResourceList(brick, ore, sheep, wheat, wood);
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) 
	{
		try 
		{
			this.sendOfferTo = new Index(playerIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		//Update the button
		this.tradeOverlay.setTradeEnabled(areWeReadyToTrade());
	}

	@Override
	public void setResourceToReceive(ResourceType resource) 
	{
		//Set the overlay to allow them to edit
		this.tradeOverlay.setResourceSelectionEnabled(true);
		
		//Reset the resource count in the resource map
		this.resources.put(resource, 0);
		this.options.put(resource, ResourceOption.RECEIVE);
		this.tradeOverlay.setResourceAmount(resource, 0 + "");
		
		//Set the overlay. They should always be able to increase, but never decrease to start off with
		this.tradeOverlay.setResourceAmountChangeEnabled(resource, true, false);
	}

	@Override
	public void setResourceToSend(ResourceType resource) 
	{
		//Set the overlay to allow them to edit
		this.tradeOverlay.setResourceSelectionEnabled(true);
		
		//Reset the resource count in the resource map
		this.resources.put(resource, 0);
		this.options.put(resource, ResourceOption.SEND);
		this.tradeOverlay.setResourceAmount(resource, 0 + "");
		
		//Set the overlay. They should only be able to increase if they have the resource
		int resourceAmount = this.getResourceCount(master.getPlayer(), resource);
		this.tradeOverlay.setResourceAmountChangeEnabled(resource, resourceAmount > 0, false);
	}

	@Override
	public void unsetResource(ResourceType resource) 
	{	
		//Set the overlay to not allow them to increase or decrease
		this.tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
		
		//Reset the resource count and option in the maps
		this.resources.put(resource, 0);
		this.options.put(resource, ResourceOption.NONE);

		//Update the button
		this.tradeOverlay.setTradeEnabled(areWeReadyToTrade());
	}
	
	private boolean areWeReadyToTrade()
	{
		//If they haven't selected who to send the offer to, return false
		if(this.sendOfferTo == null || this.sendOfferTo.value() == -1)
		{
			this.tradeOverlay.setStateMessage("Please select someone who you'd like to trade with");
			return false;
		}
		
		//Check if they've selected resource to both send and receive
		boolean sending = false;
		boolean receiving = false;
		for(ResourceType resource : ResourceType.values())
		{
			if(options.get(resource) == ResourceOption.SEND)
			{
				if(resources.get(resource) != 0)
				{
					sending = true;					
				}
			}
			else if(options.get(resource) == ResourceOption.RECEIVE)
			{
				if(resources.get(resource) != 0)
				{
					receiving = true;
				}
			}
		}
		if(!sending)
		{
			this.tradeOverlay.setStateMessage("Please select a resource to give");
			return false;
		}
		if(!receiving)
		{
			this.tradeOverlay.setStateMessage("Please select a resource to receive");
			return false;
		}
		
		//If they passed all of these conditions, return true
		this.tradeOverlay.setStateMessage("TRADE!");
		return true;
	}

	@Override
	public void cancelTrade() 
	{
		//Clear all resources
		this.resetAllResources();
		
		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) 
	{
		//System.out.println("Was the trade accepted? --> "+ willAccept);
		master.acceptTrade(master.getPlayerIndex(), willAccept);
		
		// Need to check here for that receiver has sufficient resources:
		
		getAcceptOverlay().closeModal();
		//Clear all the resources
		this.resetAllResources();
		
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		Trade trade = this.master.getCurrentModel().trade();
		if(trade != null)
		{
			if(trade.receiver().equals(this.master.getPlayerIndex()))
			{
				//this.acceptOverlay.clearAllResources();
				this.acceptOverlay.reset();
				this.updateAcceptOverlayResourceList(trade);
				this.acceptOverlay.showModal();
				this.acceptOverlay.setAcceptEnabled(this.master.canAcceptTrade(master.getPlayerIndex()));
				this.acceptOverlay.setPlayerName(master.getCurrentModel().players().get(trade.sender().value()).name());
			}
		}
		else if(this.waitOverlay.isModalShowing())
		{
			this.waitOverlay.closeModal();
		}
	}
	
	private void updateAcceptOverlayResourceList(Trade trade)
	{
		//BRICK
		int brick = trade.offer().brick();
		if(brick < 0)
		{
			this.acceptOverlay.addGetResource(ResourceType.BRICK, -brick);
		}
		else if(brick > 0)
		{
			this.acceptOverlay.addGiveResource(ResourceType.BRICK, brick);
		}
		
		//ORE
		int ore = trade.offer().ore();
		if(ore < 0)
		{
			this.acceptOverlay.addGetResource(ResourceType.ORE, -ore);
		}
		else if(ore > 0)
		{
			this.acceptOverlay.addGiveResource(ResourceType.ORE, ore);
		}
		
		//SHEEP
		int sheep = trade.offer().sheep();
		if(sheep < 0)
		{
			this.acceptOverlay.addGetResource(ResourceType.SHEEP, -sheep);
		}
		else if(sheep > 0)
		{
			this.acceptOverlay.addGiveResource(ResourceType.SHEEP, sheep);
		}

		//WHEAT
		int wheat = trade.offer().wheat();
		if(wheat < 0)
		{
			this.acceptOverlay.addGetResource(ResourceType.WHEAT, -wheat);
		}
		else if(wheat > 0)
		{
			this.acceptOverlay.addGiveResource(ResourceType.WHEAT, wheat);
		}

		//WOOD
		int wood = trade.offer().wood();
		if(wood < 0)
		{
			this.acceptOverlay.addGetResource(ResourceType.WOOD, -wood);
		}
		else if(wood > 0)
		{
			this.acceptOverlay.addGiveResource(ResourceType.WOOD, wood);
		}
	}

}