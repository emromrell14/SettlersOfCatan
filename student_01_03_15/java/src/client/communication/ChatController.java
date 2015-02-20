package client.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import JSONmodels.MessageLineJSON;
import JSONmodels.MessageListJSON;
import models.Index;
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
		System.out.println("ChatController update");
		MessageListJSON chat = master.getCurrentModel().chat();
		
		
		if(chat != null)
		{
			List<LogEntry> entries = new ArrayList<LogEntry>();
			for(MessageLineJSON m : chat.getLines())
			{
				String color = m.getSource();
				System.out.println("color:"+color);
				CatanColor playerColor = null;
				switch(color)
				{
				case "red":
					playerColor = CatanColor.RED;
					break;
				case "green":
					playerColor = CatanColor.GREEN;
					break;
				case "brown":
					playerColor = CatanColor.BROWN;
					break;
				case "white":
					playerColor = CatanColor.WHITE;
					break;
				case "orange":
					playerColor = CatanColor.ORANGE;
					break;
				case "purple":
					playerColor = CatanColor.PURPLE;
					break;
				case "yellow":
					playerColor = CatanColor.YELLOW;
					break;
				case "blue":
					playerColor = CatanColor.BLUE;
					break;
				case "puce":
					playerColor = CatanColor.PUCE;
					break;
				default:
					System.out.println("ChatController: should never get here");
						
				}
				entries.add(new LogEntry(playerColor,m.getMessage()));
			}
			getView().setEntries(entries);
		}
	}

}

