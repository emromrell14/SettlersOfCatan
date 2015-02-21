package client.communication;

import java.util.*;

import JSONmodels.MessageLineJSON;
import JSONmodels.MessageListJSON;
import client.base.*;
import facade.IMasterManager;
import facade.MasterManager;
import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, Observer {
	
	private IMasterManager master;

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
		initFromModel();
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel()
	{
		MessageListJSON log = master.getLog();
		List<LogEntry> entries = new ArrayList<LogEntry>();
		
		if(log != null)
		{
			for(MessageLineJSON m : log.getLines())
			{
				String name = m.getSource();
				CatanColor playerColor = master.getCurrentModel().getPlayerColor(name);
				entries.add(new LogEntry(playerColor,m.getMessage()));
			}
		}
		
		getView().setEntries(entries);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		initFromModel();
	}
	
}

