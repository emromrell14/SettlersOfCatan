package client.roll;

import java.util.Observable;
import java.util.Observer;

import models.Status;
import states.DiscardingState;
import states.IState;
import states.PlayingState;
import states.RobbingState;
import states.RollingState;
import states.SetupState;
import client.base.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, Observer {

	private IRollResultView resultView;
	private IMasterManager master;
	private IState state;
	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		
		setResultView(resultView);
		this.state = new SetupState();
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
	}
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	@Override
	public void rollDice() {
		
		int die1 = (int)(Math.random() * 6) + 1;
		int die2 = (int)(Math.random() * 6) + 1;
		int rollNum = die1 + die2;
		this.resultView.setRollValue(rollNum);
		master.rollDice(master.getPlayerIndex(), rollNum);
		getResultView().showModal();
		master.getCurrentModel().turnTracker().setStatus(Status.PLAYING);

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("\nState: " + state.getClass().getName());
		System.out.println("\nTurnTrackerStatus: " + master.getCurrentModel().turnTracker().status());
		
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
			state = new DiscardingState();
			break;
		case ROLLING:
			if (master.getPlayer().playerIndex().value() == master.getCurrentModel().turnTracker().currentTurn().value())
			{
				getRollView().showModal();
			}
			state = new RollingState();
			break;
		case FIRSTROUND:
			state = new SetupState();
			break;
		case SECONDROUND:
			state = new SetupState();
			break;
		default:
			System.out.println("RollController update() should never get here.");
		}
	}

}

