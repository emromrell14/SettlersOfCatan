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
	private boolean showModal = false;
	private final int NUMBER_OF_PLAYERS = 4;

	public PlayerWaitingController(IPlayerWaitingView view) {
		
		
		super(view);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
	}

	@Override
	public IPlayerWaitingView getView()
	{

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start()
	{
		System.out.println("----> start() called in playerWaitingController");

		Game gameModel = master.getCurrentModel();
		this.controllerStarted = true;
		this.showModal = true;
//		this.setupPlayerInfo(gameModel);
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
		if(!master.hasJoinedGame)
		{
			System.out.println("UPDATING playerWaitingController.");
			ModelManager manager = (ModelManager) o;
			Game gameModel = manager.getCurrentModel();
			
			this.setupPlayerInfo(gameModel);
		}
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
				System.out.println("playerwaiting--->"+ p.color().name());
				players.add(info);
			}
		}
		// Convert ArrayList to Array
		PlayerInfo[] playersArray = new PlayerInfo[players.size()];
		players.toArray(playersArray);
		
		// Set players in the view
		getView().setPlayers(playersArray);
		
		// Hack to work around current player not showing up on Waiting list. Will be true first time start() is called.
		if(showModal)
		{
			getView().showModal();
			showModal = false;
		}
		// Check to close modal
		if(controllerStarted && playersArray.length == NUMBER_OF_PLAYERS)
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

