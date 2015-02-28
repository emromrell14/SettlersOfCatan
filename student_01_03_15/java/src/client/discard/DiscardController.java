package client.discard;

import java.util.Observable;
import java.util.Observer;

import models.Game;
import models.Player;
import models.ResourceList;
import models.Status;
import shared.definitions.*;
import states.DiscardingState;
import states.IState;
import states.PlayingState;
import states.RobbingState;
import states.RollingState;
import states.SetupState;
import client.base.*;
import client.misc.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, Observer {

	private IWaitView waitView;
	private MasterManager master;
	private int cardsToDiscard = 0;
	private int currentDiscardNum = 0;
	private Player player;
	private int oreTotal = 0;
	private int woodTotal = 0;
	private int sheepTotal = 0;
	private int wheatTotal = 0;
	private int brickTotal = 0;
	private int oreDiscard = 0;
	private int woodDiscard = 0;
	private int sheepDiscard = 0;
	private int wheatDiscard = 0;
	private int brickDiscard = 0;
	private IState state;

	/**
	 * DiscardController constructor	
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView)
	{		
		super(view);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
		this.player = master.getPlayer();
		this.waitView = waitView;
		this.initialize();
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}
	public void initialize()
	{
		this.setCardsToDiscard();
		this.setCardsTotals();
		this.setMap();
		String message = this.currentDiscardNum + "/" + this.cardsToDiscard;
		this.getDiscardView().setStateMessage(message);
	}

	@Override
	public void increaseAmount(ResourceType resource) 
	{
		this.increaseType(resource);
		this.getDiscardView().setResourceDiscardAmount(resource, this.getType(resource));
		this.getDiscardView().setResourceAmountChangeEnabled(resource, this.canIncrease(resource), this.canDecrease(resource));
		String message = this.currentDiscardNum + "/" + this.cardsToDiscard;
		this.getDiscardView().setStateMessage(message);
		
		if (this.discardReached())
		{
			this.discardNumReached();
		}
	}

	@Override
	public void decreaseAmount(ResourceType resource) 
	{
		this.decreaseType(resource);
		this.getDiscardView().setResourceDiscardAmount(resource, this.getType(resource));
		this.getDiscardView().setResourceAmountChangeEnabled(resource, this.canIncrease(resource), this.canDecrease(resource));
		String message = this.currentDiscardNum + "/" + this.cardsToDiscard;
		this.getDiscardView().setStateMessage(message);
		
	}

	@Override
	public void discard() {
		if (this.discardReached() && this.player != null)
		{
			ResourceList rList = new ResourceList(this.brickDiscard, this.oreDiscard, this.sheepDiscard, this.wheatDiscard, this.woodDiscard);
			this.master.discardCards(this.player.playerIndex(), rList);
			getDiscardView().closeModal();
		}
	}

	public void setCardsToDiscard()
	{
		if(player != null)
		{
			if (player.canDiscard())
			{
				cardsToDiscard = player.resources().getTotal()/2;
			}
		}
	}
	public void setCardsTotals()
	{
		if(player != null)
		{
			this.oreTotal = player.resources().ore();
			this.woodTotal = player.resources().wood();
			this.brickTotal = player.resources().brick();
			this.sheepTotal = player.resources().sheep();
			this.wheatTotal = player.resources().wheat();
		}
	}
	public void discardNumReached()
	{
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, this.canDecrease(ResourceType.ORE));
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, this.canDecrease(ResourceType.WOOD));
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, this.canDecrease(ResourceType.SHEEP));
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, this.canDecrease(ResourceType.WHEAT));
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, this.canDecrease(ResourceType.BRICK));
		this.getDiscardView().setDiscardButtonEnabled(true);
	}
	public boolean canDecrease(ResourceType type)
	{
		if (type == ResourceType.ORE)
		{
			if (this.currentDiscardNum != 0 && this.oreDiscard > 0)
			{
				return true;
			}
		}
		if (type == ResourceType.WOOD)
		{
			if (this.currentDiscardNum != 0 && this.woodDiscard > 0)
			{
				return true;
			}
		}
		if (type == ResourceType.SHEEP)
		{
			if (this.currentDiscardNum != 0 && this.sheepDiscard > 0)
			{
				return true;
			}
		}
		if (type == ResourceType.WHEAT)
		{
			if (this.currentDiscardNum != 0 && this.wheatDiscard > 0)
			{
				return true;
			}
		}
		if (type == ResourceType.BRICK)
		{
			if (this.currentDiscardNum != 0 && this.brickDiscard > 0)
			{
				return true;
			}
		}
		return false;
	}
	public boolean canIncrease(ResourceType type)
	{
		if (type == ResourceType.ORE)
		{
			if (this.cardsToDiscard != this.currentDiscardNum && this.oreTotal > this.oreDiscard)
			{
				return true;
			}
		}
		if (type == ResourceType.WOOD)
		{
			if (this.cardsToDiscard != this.currentDiscardNum && this.woodTotal > this.woodDiscard)
			{
				return true;
			}
		}
		if (type == ResourceType.SHEEP)
		{
			if (this.cardsToDiscard != this.currentDiscardNum && this.sheepTotal > this.sheepDiscard)
			{
				return true;
			}
		}
		if (type == ResourceType.WHEAT)
		{
			if (this.cardsToDiscard != this.currentDiscardNum && this.wheatTotal > this.wheatDiscard)
			{
				return true;
			}
		}
		if (type == ResourceType.BRICK)
		{
			if (this.cardsToDiscard != this.currentDiscardNum && this.brickTotal > this.brickDiscard)
			{
				return true;
			}
		}
		return false;
	}
	public void increaseType(ResourceType type)
	{
		if (type == ResourceType.ORE)
		{
			this.brickDiscard+=1;
		}
		if (type == ResourceType.WOOD)
		{
			this.woodDiscard+=1;
		}
		if (type == ResourceType.SHEEP)
		{
			this.sheepDiscard+=1;
		}
		if (type == ResourceType.WHEAT)
		{
			this.wheatDiscard+=1;
		}
		if (type == ResourceType.BRICK)
		{
			this.brickDiscard+=1;
		}
		this.currentDiscardNum+=1;
	}
	public void decreaseType(ResourceType type)
	{
		if (type == ResourceType.ORE)
		{
			this.brickDiscard-=1;
		}
		if (type == ResourceType.WOOD)
		{
			this.woodDiscard-=1;
		}
		if (type == ResourceType.SHEEP)
		{
			this.sheepDiscard-=1;
		}
		if (type == ResourceType.WHEAT)
		{
			this.wheatDiscard-=1;
		}
		if (type == ResourceType.BRICK)
		{
			this.brickDiscard-=1;
		}
		this.currentDiscardNum-=1;
	}
	public boolean discardReached()
	{
		if (this.currentDiscardNum == this.cardsToDiscard)
		{
			return true;
		}
		return false;
	}
	public void setMap()
	{
		//Ore SetUp
		this.getDiscardView().setResourceMaxAmount(ResourceType.ORE, oreTotal);
		this.getDiscardView().setResourceDiscardAmount(ResourceType.ORE, oreDiscard);
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, this.canIncrease(ResourceType.ORE), this.canDecrease(ResourceType.ORE));
		
		//Wood SetUp
		this.getDiscardView().setResourceMaxAmount(ResourceType.WOOD, woodTotal);
		this.getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, woodDiscard);
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, this.canIncrease(ResourceType.WOOD), this.canDecrease(ResourceType.WOOD));
		
		//Sheep SetUp
		this.getDiscardView().setResourceMaxAmount(ResourceType.SHEEP, sheepTotal);
		this.getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, sheepDiscard);
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, this.canIncrease(ResourceType.SHEEP), this.canDecrease(ResourceType.SHEEP));
		
		//Wheat SetUp
		this.getDiscardView().setResourceMaxAmount(ResourceType.WHEAT, wheatTotal);
		this.getDiscardView().setResourceDiscardAmount(ResourceType.WHEAT, wheatDiscard);
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, this.canIncrease(ResourceType.WHEAT), this.canDecrease(ResourceType.WHEAT));
		
		//Brick SetUp
		this.getDiscardView().setResourceMaxAmount(ResourceType.BRICK, brickTotal);
		this.getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, brickDiscard);
		this.getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, this.canIncrease(ResourceType.BRICK), this.canDecrease(ResourceType.BRICK));
		
	}
	
	public int getType(ResourceType type)
	{
		if (type == ResourceType.ORE)
		{
			return this.brickDiscard;
		}
		if (type == ResourceType.WOOD)
		{
			return this.woodDiscard;
		}
		if (type == ResourceType.SHEEP)
		{
			return this.sheepDiscard;
		}
		if (type == ResourceType.WHEAT)
		{
			return this.wheatDiscard;
		}
		if (type == ResourceType.BRICK)
		{
			return this.brickDiscard;
		}
		return -1;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		//Game game = this.master.getCurrentModel();
		if(master.hasJoinedGame)
		{
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
					getDiscardView().showModal();
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
		}
	}

}

