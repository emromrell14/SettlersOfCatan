package client.join;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import JSONmodels.GameInfoJSON;
import JSONmodels.GamesInfoJSON;
import models.Game;
import shared.definitions.CatanColor;
import client.base.*;
import client.data.*;
import client.misc.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController, Observer {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private IMasterManager master;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
		master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
	}
	
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}

	@Override
	public void start() {
			
		this.fromJsonToViewFormat();
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
		
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() 
	{
		String title = this.newGameView.getTitle();
		Boolean randHexes = this.newGameView.getRandomlyPlaceHexes();
		Boolean randNums = this.newGameView.getRandomlyPlaceNumbers();
		Boolean randPorts = this.newGameView.getUseRandomPorts();
		
		// for testing
//		System.out.println("Checkboxes: " + randHexes + " " + randNums +  " " + randPorts);
//		System.out.println("Title-" + title + "-");
		
		// Check that title has at least 1 non-whitespace character
		if(title.matches(".*\\w.*"))
		{
			master.createGame(randHexes, randNums, randPorts, title);
			getNewGameView().closeModal();
		}
	}

	@Override
	public void startJoinGame(GameInfo game) {

		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
	
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		
		// If join succeeded
		getSelectColorView().closeModal();
		getJoinGameView().closeModal();
		joinAction.execute();
	}
	
	public void fromJsonToViewFormat()
	{
		String JSON = master.getGameList();
		ArrayList<GameInfo> games = new ArrayList<GameInfo>();
		GameInfoJSON gameInfo = new GameInfoJSON();
		GameInfoJSON[] gameInfoArray;
		
		gameInfoArray = gameInfo.getGamesArrayFromJSON(JSON);
		for(GameInfoJSON gameJSON : gameInfoArray)
		{
			GameInfo game = new GameInfo();
			game.setId(gameJSON.getId());
			game.setTitle(gameJSON.getTitle());
			for(PlayerInfo p : gameJSON.getPlayers())
			{
				game.addPlayer(p);
			}
			games.add(game);
		}
		
//		Set info for current player
		PlayerInfo localPlayer = new PlayerInfo();
		localPlayer.setId(master.getPlayerID());
		localPlayer.setName(master.getPlayer().name());
		
//		Convert ArrayList to Array
		GameInfo[] gamesArray = new GameInfo[games.size()];
		games.toArray(gamesArray);
		
		getJoinGameView().setGames(gamesArray, localPlayer);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

