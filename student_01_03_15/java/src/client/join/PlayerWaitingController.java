package client.join;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {
	
	private IMasterManager master;

	public PlayerWaitingController(IPlayerWaitingView view) {
		
		
		super(view);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {

		getView().showModal();
	}

	@Override
	public void addAI()
	{
		master.addAIPlayer();
		// TEMPORARY
//		getView().closeModal();
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		//if there are already 4 players, close modal
		
	}

}

