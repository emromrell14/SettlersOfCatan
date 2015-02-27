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
import shared.definitions.*;
import client.base.*;
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
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {		
		this.tradeOverlay.showModal();
		
		//Set the resources all to 0 and option to NONE
		this.resetAllResources();
		
		//Set all resources to disabled (since they are in the "none" stage)
		this.tradeOverlay.initializeVisibility();
		
		//If this is the first trade, then initialize the players
		if(!this.playersInitialized)
		{
			this.initializePlayers();
			this.playersInitialized = true;
		}
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
			case SEND:
				//When requesting to send something, you can only send as much as you have.
				int resourceAmount = this.getResourceCount(master.getPlayer(), resource);
				this.tradeOverlay.setResourceAmountChangeEnabled(resource, resourceAmount < new_amount, true);
			default:
						
		}
	}

	@Override
	public void sendTradeOffer() 
	{
		//Create the resourceList to offer
		ResourceList resourceList = createResourceList();
		System.out.println("Sending offer for " + resourceList.brick() + " brick, " + resourceList.ore() + " ore, " + resourceList.sheep() + " sheep, " + resourceList.wheat() + " wheat, and " + resourceList.wood() + " wood.");
		master.offerTrade(master.getPlayerIndex(), resourceList, this.sendOfferTo);

		getTradeOverlay().closeModal();
//		getWaitOverlay().showModal();
	}
	
	private ResourceList createResourceList()
	{
		int brick = resources.get(ResourceType.BRICK);
		if(options.get(ResourceType.BRICK) == ResourceOption.SEND)
		{
			brick = -brick;
		}
		int ore = resources.get(ResourceType.ORE);
		if(options.get(ResourceType.ORE) == ResourceOption.SEND) 
		{
			ore = -ore;
		}
		int sheep = resources.get(ResourceType.SHEEP);
		if(options.get(ResourceType.SHEEP) == ResourceOption.SEND) 
		{
			sheep = -sheep;
		}
		int wheat = resources.get(ResourceType.WHEAT);
		if(options.get(ResourceType.WHEAT) == ResourceOption.SEND) 
		{
			wheat = -wheat;
		}
		int wood = resources.get(ResourceType.WOOD);
		if(options.get(ResourceType.WOOD) == ResourceOption.SEND)
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
	}

	@Override
	public void setResourceToReceive(ResourceType resource) 
	{
		//Set the overlay to allow them to edit
		this.tradeOverlay.setResourceSelectionEnabled(true);
		
		//Reset the resource count in the resource map
		this.resources.put(resource, 0);
		this.options.put(resource, ResourceOption.RECEIVE);
		
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
	}

	@Override
	public void cancelTrade() {
		//Clear all resources
		this.resetAllResources();
		
		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		//Clear all the resources
		this.resetAllResources();

		getAcceptOverlay().closeModal();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}