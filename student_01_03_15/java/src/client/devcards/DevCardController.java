package client.devcards;

import shared.definitions.ResourceType;
import client.base.*;
import facade.MasterManager;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	private MasterManager master;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction) {

		super(view);
		
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
		
		this.master = MasterManager.getInstance();
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		if(master.canBuyDevCard(master.getPlayerIndex()))
		{
			getBuyCardView().showModal();
		}	
	}

	@Override
	public void cancelBuyCard() {
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		master.buyDevCard(master.getPlayerIndex());
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		master.canPlayDevCard(master.getPlayerIndex());
		getPlayCardView().showModal();
	}

	@Override
	public void cancelPlayCard() {
		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		master.playMonopoly(resource, master.getPlayerIndex());
	}

	@Override
	public void playMonumentCard() {
		master.playMonument(master.getPlayerIndex());
	}

	@Override
	public void playRoadBuildCard() {
		master.canPlayRoadBuilder(master.getPlayerIndex());
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		master.canPlaySoldier(master.getPlayerIndex());
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		master.playYearOfPlenty(master.getPlayerIndex(), resource1, resource2);
	}

}

