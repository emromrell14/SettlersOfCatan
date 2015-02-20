package client.communication;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, Observer {
	
	private IMasterManager master;

	public ChatController(IChatView view) {
		
		super(view);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

