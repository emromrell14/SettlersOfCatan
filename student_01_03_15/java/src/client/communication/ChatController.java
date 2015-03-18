package client.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import models.Index;
import models.Message;
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
	public void sendMessage(String message) 
	{
		Index playerIndex = master.getPlayerIndex();
		master.sendChatMessage(playerIndex, message);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		////System.out.println("ChatController update");
		List<Message> chat = master.getCurrentModel().chat();
		
		
		if(chat != null)
		{
			List<LogEntry> entries = new ArrayList<LogEntry>();
			for(Message m : chat)
			{
				String name = m.playerName();
				CatanColor color = master.getCurrentModel().getPlayerColor(name);
				entries.add(new LogEntry(color,m.message()));
			}
			getView().setEntries(entries);
		}
	}

}

