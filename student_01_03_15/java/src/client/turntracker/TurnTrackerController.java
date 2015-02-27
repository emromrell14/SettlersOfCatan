package client.turntracker;

import java.util.Observable;
import java.util.Observer;

import models.Game;
import client.base.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, Observer {
	private IMasterManager mMaster; 

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		this.mMaster = MasterManager.getInstance();
		this.mMaster.getModelManager().addObserver(this);
		initFromModel();
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		System.out.println("Called endTurn() in TurnTrackerController");
		mMaster.finishTurn(mMaster.getPlayerIndex());
	}
	
	private void initFromModel() {
		int playerID = mMaster.getPlayerID();
		Game game = mMaster.getCurrentModel();
		if(game != null && playerID >= 0)
		{
			getView().setLocalPlayerColor(game.getPlayerColor(playerID));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

