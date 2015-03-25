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
import facade.MasterManager;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, Observer {

	private IRollResultView resultView;
	private MasterManager master;
	@SuppressWarnings("unused")
	private IState state;
	private static boolean semaphore = false;
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
		master.hasRolled = true;
		int die1 = (int)(Math.random() * 6) + 1;
		int die2 = (int)(Math.random() * 6) + 1;
		int rollNum = die1 + die2;
		this.resultView.setRollValue(rollNum);
		
		// HACK TO NEVER ROLL A 7
//			if (rollNum == 7) rollNum++;
//			System.out.println("HACK NEVER ROLL A 7");
		// ---------------------
	//	rollNum = 7;
		master.rollDice(master.getPlayerIndex(), rollNum);
		getResultView().showModal();
//		master.getCurrentModel().turnTracker().setStatus(Status.PLAYING);
//		if (rollNum == 7)
//		{
//			master.getCurrentModel().turnTracker().setStatus(Status.DISCARDING);
//		}
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
//		////System.out.println("\nRollController update status: " + master.getCurrentModel().turnTracker().status());
		
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
				if (!RollController.semaphore)
				{
					RollController.semaphore = true;
					
					if (master.getPlayer().playerIndex().value() == master.getCurrentModel().turnTracker().currentTurn().value())
					{
						if (!getRollView().isModalShowing() && !master.hasRolled)
						{
							getRollView().showModal();
						}
					}
					state = new RollingState();
					RollController.semaphore = false;
				}
				break;
			case FIRSTROUND:
				state = new SetupState();
				break;
			case SECONDROUND:
				state = new SetupState();
				break;
			default:
				////System.out.println("RollController update() should never get here.");
		}
	}

}

