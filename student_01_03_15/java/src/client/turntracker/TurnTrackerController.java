package client.turntracker;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import states.DiscardingState;
import states.IState;
import states.PlayingState;
import states.RobbingState;
import states.RollingState;
import states.SetupState;
import models.Game;
import models.Player;
import models.Status;
import client.base.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, Observer {
	private MasterManager master; 
	private IState state;

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
		this.state = new SetupState();
		//initFromModel();
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		System.out.println("Called endTurn() in TurnTrackerController");
		master.finishTurn(master.getPlayerIndex());
	}
	
	private void initFromModel() 
	{
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//initFromModel();
		if(master.hasJoinedGame)
		{
			for (Player p: master.getCurrentModel().players())
			{
				getView().initializePlayer(p.playerIndex().value(), p.name(), p.color());
			}
			getView().setLocalPlayerColor(master.getPlayer().color());
		}
		Status status = master.getCurrentModel().turnTracker().status();
		switch(status)
		{
			case ROBBING:
				getView().updateGameState("Place the Robber", false);
				state = new RobbingState();
				break;
			case PLAYING:
				getView().updateGameState("Finish Turn", true);
				state = new PlayingState();
				break;
			case DISCARDING:
				getView().updateGameState("Discard Cards", false);
				state = new DiscardingState();
				break;
			case ROLLING:
				getView().updateGameState("Roll the Dice", false);
				state = new RollingState();
				break;
			case FIRSTROUND:
				getView().updateGameState("Setting up Map", false);
				state = new SetupState();
				break;
			case SECONDROUND:
				getView().updateGameState("Setting up Map", false);
				state = new SetupState();
				break;
			case WAITINGJOIN:
				getView().updateGameState("Waiting for Other Players", false);
				state = new SetupState();
				break;
			default:
				System.out.println("RollController update() should never get here.");
		}
	}

}

