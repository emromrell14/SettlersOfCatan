package client.roll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

import models.Status;
import states.DiscardingState;
import states.IState;
import states.PlayingState;
import states.RobbingState;
import states.RollingState;
import states.SetupState;
import client.base.*;
import facade.MasterManager;

import javax.swing.Timer;


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
		
//		 HACK TO NEVER ROLL A 7
			if (rollNum == 7) rollNum++;
//			System.out.println("HACK NEVER ROLL A 7");
		// ---------------------
		master.rollDice(master.getPlayerIndex(), rollNum);
		getResultView().showModal();
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
							goTimers();
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
	public void goTimers()
	{
//		Code for automatic rolls
		getRollView().setMessage("three");
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  goTimer2();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	public void goTimer2()
	{
		  getRollView().setMessage("two");
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  goTimer3();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	public void goTimer3()
	{
		  getRollView().setMessage("one!");
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  if(!master.hasRolled)
				  {
					  getRollView().closeModal();
					  rollDice();
				  }
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	

}

