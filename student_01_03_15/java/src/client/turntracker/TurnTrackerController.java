package client.turntracker;

import models.Game;
import client.base.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {
	private IMasterManager mMaster = MasterManager.getInstance();

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		initFromModel();
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		mMaster.getCurrentModel().endTurn();
	}
	
	private void initFromModel() {
		int playerID = mMaster.getPlayerID();
		Game game = mMaster.getCurrentModel();
		if(game != null)
		{
			getView().setLocalPlayerColor(game.getPlayerColor(playerID));
		}
	}

}

