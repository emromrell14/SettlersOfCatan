package client.join;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import models.Game;
import models.Player;
import shared.definitions.CatanColor;
import client.base.*;
import client.data.GameInfo;
import client.data.PlayerInfo;
import facade.IMasterManager;
import facade.MasterManager;
import facade.ModelManager;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {
	
	private MasterManager master;
	private boolean controllerStarted = false;

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
	public void start()
	{
		System.out.println("----> start() called in playerWaitingController");
		getView().showModal();
		Game gameModel = master.getCurrentModel();
		this.controllerStarted = true;
		this.setupPlayerInfo(gameModel);
		// This doesn't show the current player because joining a game doesn't change the version number...dumb
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
		System.out.println("UPDATING playerWaitingController.");
		ModelManager manager = (ModelManager) o;
		Game gameModel = manager.getCurrentModel();
		
		this.setupPlayerInfo(gameModel);
	}
	
	public void setupPlayerInfo(Game gameModel)
	{
		ArrayList<PlayerInfo> players = new ArrayList();
		for(Player p : gameModel.players())
		{
			if(p != null)
			{
				PlayerInfo info  = new PlayerInfo();
				info.setName(p.name());
				info.setId(p.playerID());
				info.setPlayerIndex(p.playerIndex().value());
				info.setColorCatan(p.color());
//				System.out.println("playerwaiting--->"+ p.color().name());
				players.add(info);
			}
		}
		// Convert ArrayList to Array
		PlayerInfo[] playersArray = new PlayerInfo[players.size()];
		players.toArray(playersArray);
//		for(int i = 0; i < playersArray.length; ++i)
//		{
//			System.out.print(playersArray[i].getName() + playersArray[i].getColorCatan().name() + "--");
//		}
		getView().setPlayers(playersArray);
		
		// Check to close modal
		if(controllerStarted && playersArray.length == 4)
		{
			startGame();
		}
	}
	
	public void startGame()
	{
		System.out.println("------> startGame() called in PlayerWaiting...closing modal");
		master.hasJoinedGame = true;
		getView().closeModal();
	}

}

