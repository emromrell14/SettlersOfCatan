package client.devcards;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import models.DevCard;
import models.Game;
import models.Player;
import models.ResourceList;
import models.Status;
import shared.definitions.ResourceType;
import client.base.*;
import client.misc.IMessageView;
import client.misc.MessageView;
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
	private IMessageView error = new MessageView();
	private ImageIcon devCardIcon = new ImageIcon("images" + File.separator + "building" + File.separator + "card.jpg");
	
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
		else
		{
			JOptionPane.showMessageDialog(null, "No more Development Cards in bank!", "Empty Bank!", JOptionPane.INFORMATION_MESSAGE, devCardIcon);
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
		master.playMonument(master.getPlayerIndex());
	}

	@Override
	public void playRoadBuildCard() {
		if(master.canPlayRoadBuilder(master.getPlayerIndex()))
		{
			roadAction.execute();
		}
	}

	@Override
	public void playSoldierCard() {
		if(master.canPlaySoldier(master.getPlayerIndex()))
		{
			master.getCurrentModel().turnTracker().setStatus(Status.ROBBING);
			soldierAction.execute();
		}
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		if(bankHasResources(resource1,resource2))
		{
			master.playYearOfPlenty(master.getPlayerIndex(), resource1, resource2);
		}
		else
		{
			error.setTitle("ERROR");
			error.setMessage("Not enough resources in bank");
			error.showModal();
		}
	}
	
	public boolean hasResourceAmount(ResourceType r1, int amount)
	{
		ResourceList bank = master.getCurrentModel().bank();
		switch(r1)
		{
		case WOOD:
			if(bank.wood() > amount)
			{
				return true;
			}
			break;
		case SHEEP:
			if(bank.sheep() > amount)
			{
				return true;
			}
			break;
		case ORE:
			if(bank.ore() > amount)
			{
				return true;
			}
			break;
		case BRICK:
			if(bank.brick() > amount)
			{
				return true;
			}
			break;
		case WHEAT:
			if(bank.wheat() > amount)
			{
				return true;
			}
			break;
		default:
			////System.out.println("DevCardController.hasResourceAmount() should never get here");
		}
		return false;
	}
	
	public boolean bankHasResources(ResourceType r1, ResourceType r2)
	{
		boolean same = r1 == r2;
		if(same)
		{
			return hasResourceAmount(r1,1);
		}
		
		return hasResourceAmount(r1,0) && hasResourceAmount(r2,0);
	}
	
	public void disableAllDevCardButtons()
	{
		getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, false);
		getPlayCardView().setCardEnabled(DevCardType.SOLDIER, false);
		getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, false);
		getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, false);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		Game game = master.getCurrentModel();
		if(game != null && master.hasJoinedGame)
		{
			disableAllDevCardButtons();
			
			Player p = master.getPlayer();
			
			Map<DevCardType, Boolean> visible = new HashMap<DevCardType, Boolean>();
			Map<DevCardType, Integer> amount = new HashMap<DevCardType, Integer>();
			for(DevCardType type : DevCardType.values())
			{
				visible.put(type, false);
				amount.put(type, 0);
			}
				
			for(DevCard d : p.devCards())
			{
				if(!d.isNew())
				{
					visible.put(d.type(), true);
				}
				amount.put(d.type(), amount.get(d.type()) + 1);
			}

			for(DevCardType type : DevCardType.values())
			{
				this.getPlayCardView().setCardAmount(type, amount.get(type));
				
				//Only set the monument's visibility if they have already played a dev card
				if(!p.hasPlayedDevCard())
				{
					this.getPlayCardView().setCardEnabled(type, visible.get(type));
				}
				else
				{
					this.getPlayCardView().setCardEnabled(DevCardType.MONUMENT, visible.get(DevCardType.MONUMENT));
				}
			}
		}
	}
}

