package client.devcards;

import java.util.Observable;
import java.util.Observer;

import models.DevCard;
import models.Game;
import models.Player;
import models.Status;
import shared.definitions.ResourceType;
import client.base.*;
import facade.MasterManager;
import shared.definitions.*;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController, Observer {

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
		this.master.getModelManager().addObserver(this);
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
		disableAllDevCardButtons();
		master.playMonopoly(resource, master.getPlayerIndex());
	}

	@Override
	public void playMonumentCard() {
		disableAllDevCardButtons();
		master.playMonument(master.getPlayerIndex());
	}

	@Override
	public void playRoadBuildCard() {
		if(master.canPlayRoadBuilder(master.getPlayerIndex()))
		{
			disableAllDevCardButtons();
			roadAction.execute();
		}
	}

	@Override
	public void playSoldierCard() {
		if(master.canPlaySoldier(master.getPlayerIndex()))
		{
			disableAllDevCardButtons();
			master.getCurrentModel().turnTracker().setStatus(Status.ROBBING);
			soldierAction.execute();
		}
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		disableAllDevCardButtons();
		master.playYearOfPlenty(master.getPlayerIndex(), resource1, resource2);
	}
	
	public void disableAllDevCardButtons()
	{
		getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, false);
		getPlayCardView().setCardEnabled(DevCardType.SOLDIER, false);
		getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, false);
		getPlayCardView().setCardEnabled(DevCardType.MONUMENT, false);
		getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, false);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		Game game = master.getCurrentModel();
		if(game != null)
		{
			disableAllDevCardButtons();
			int monopoly = 0;
			boolean monopolyBool = false;
			int soldier = 0;
			boolean soldierBool = false;
			int yearOfPlenty = 0;
			boolean yearBool = false;
			int monument = 0;
			int roadBuilding = 0;
			boolean roadBool = false;
			Player p = master.getPlayer();
			for(DevCard d : p.devCards())
			{
				switch(d.type())
				{
				case MONOPOLY:
					if(!d.hasBeenPlayed())
					{
						++monopoly;
					}
					monopolyBool = monopolyBool || (!d.isNew() && !d.hasBeenPlayed());
					break;
				case SOLDIER:
					if(!d.hasBeenPlayed())
					{
						++soldier;
					}
					soldierBool = soldierBool || (!d.isNew() && !d.hasBeenPlayed());
					break;
				case YEAR_OF_PLENTY:
					if(!d.hasBeenPlayed())
					{
						++yearOfPlenty;
					}
					yearBool = yearBool || (!d.isNew() && !d.hasBeenPlayed());
					break;
				case MONUMENT:
					if(!d.hasBeenPlayed())
					{
						++monument;
					}
					getPlayCardView().setCardEnabled(DevCardType.MONUMENT, true);
					break;
				case ROAD_BUILD:
					if(!d.hasBeenPlayed())
					{
						++roadBuilding;
					}
					roadBool = roadBool || (!d.isNew() && !d.hasBeenPlayed());
					break;
				}
			}
			getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, monopolyBool);
			getPlayCardView().setCardEnabled(DevCardType.SOLDIER, soldierBool);
			getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, yearBool);
			getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, roadBool);
			
			getPlayCardView().setCardAmount(DevCardType.MONOPOLY, monopoly);
			getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, yearOfPlenty);
			getPlayCardView().setCardAmount(DevCardType.SOLDIER, soldier);
			getPlayCardView().setCardAmount(DevCardType.MONUMENT, monument);
			getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, roadBuilding);
		}
	}

}

