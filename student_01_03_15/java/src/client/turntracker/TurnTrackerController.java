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
	public ITurnTrackerView getView() 
	{
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() 
	{
		System.out.println("Called endTurn() in TurnTrackerController");
		master.finishTurn(master.getPlayerIndex());
	}
	
	private void initFromModel() 
	{
		for (Player p: master.getCurrentModel().players())
		{
			int playerIndex = p.playerIndex().value();
			if(!getView().isPlayerInitialized(playerIndex))
			{
				getView().initializePlayer(playerIndex, p.name(), p.color());
			}
			getView().updatePlayer(playerIndex, p.victoryPointCount(), 
					playerIndex == master.getCurrentModel().turnTracker().currentTurn().value(), 
					playerIndex == master.largestArmyIndex(), 
					playerIndex == master.longestRoadIndex());
		}
		getView().setLocalPlayerColor(master.getPlayer().color());
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if(master.hasJoinedGame)
		{
			initFromModel();
			boolean isPlayersTurn = master.getCurrentModel().turnTracker().isPlayersTurn(master.getPlayerIndex());
			String playingMessage = "Waiting for Other Players";
			String rollingMessage = "Waiting for Other Players";
			String discardMessage = "Waiting for Other Players";
			String setupMessage = "Waiting for Other Players";
			String robbingMessage = "Waiting for Other Players";
			if (isPlayersTurn)
			{
				playingMessage = "Finish Turn";
				rollingMessage = "Roll the Dice";
				discardMessage = "Discard Cards";
				setupMessage = "Setting up Map";
				robbingMessage = "Place the Robber";
			}
			Status status = master.getCurrentModel().turnTracker().status();
			switch(status)
			{
				case ROBBING:
					getView().updateGameState(robbingMessage, false);
					state = new RobbingState();
					break;
				case PLAYING:
					getView().updateGameState(playingMessage, isPlayersTurn);
					state = new PlayingState();
					break;
				case DISCARDING:
					getView().updateGameState(discardMessage, false);
					state = new DiscardingState();
					break;
				case ROLLING:
					getView().updateGameState(rollingMessage, false);
					state = new RollingState();
					break;
				case FIRSTROUND:
				case SECONDROUND:
					getView().updateGameState(setupMessage, false);
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

}

