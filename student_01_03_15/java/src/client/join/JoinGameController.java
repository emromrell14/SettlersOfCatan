package client.join;

import java.awt.List;
import java.util.ArrayList;

import JSONmodels.GameInfoJSON;
import JSONmodels.GamesInfoJSON;
import models.Game;
import shared.definitions.CatanColor;
import client.base.*;
import client.data.*;
import client.misc.*;
import facade.MasterManager;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private MasterManager master;
	private PlayerInfo localPlayer;
	private GameInfo localGame;
	
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
		localPlayer = new PlayerInfo();
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
	public void startJoinGame(GameInfo game)
	{
////	localPlayer.setPlayerIndex();
//		master.joinGame(game.getId(), getSelectColorView().getSelectedColor().toString());
		localGame = game;
		// i dont think this line is necessary
		localGame.addPlayer(localPlayer);
		
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
	
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color)
	{
		localPlayer.setColor(color);
		System.out.println("id: "+localGame.getId());
		String response = master.joinGame(localGame.getId(), color.toString().toLowerCase());
		System.out.println("RESPONSE: " + response);
		
		// If join succeeded
		getSelectColorView().closeModal();
		getJoinGameView().closeModal();
		joinAction.execute();
	}
	
	public void fromJsonToViewFormat()
	{
		String JSON = master.getGameList();
		System.out.println("JoinGameController json1:" + JSON + ":");
		ArrayList<GameInfo> games = new ArrayList<GameInfo>();
		GameInfoJSON gameInfo = new GameInfoJSON();
		GameInfoJSON[] gameInfoArray;
		
		System.out.println("JoinGameController json2:" + JSON + ":");

		gameInfoArray = gameInfo.getGamesArrayFromJSON(JSON);
		for(GameInfoJSON gameJSON : gameInfoArray)
		{
			GameInfo game = new GameInfo();
			game.setId(gameJSON.getId());
			game.setTitle(gameJSON.getTitle());
			for(PlayerInfo p : gameJSON.getPlayers())
			{
				System.out.println("Player read in from games/list: " + p.getName() + ", id: " + p.getId());
				if(!p.getName().equals("") || p.getId() != -1)
					game.addPlayer(p);
			}
			games.add(game);
		}
		
//		Set info for current player
		this.localPlayer.setId(master.getPlayerID());
		this.localPlayer.setName(master.getPlayerName());
		
//		Convert ArrayList to Array
		GameInfo[] gamesArray = new GameInfo[games.size()];
		games.toArray(gamesArray);
	
		getJoinGameView().setGames(gamesArray, this.localPlayer);
		
	}

}

