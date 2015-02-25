package client.join;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cookie.Cookie;
import JSONmodels.GameInfoJSON;
import JSONmodels.GamesInfoJSON;
import models.Game;
import models.Player;
import shared.definitions.CatanColor;
import client.base.*;
import client.data.*;
import client.misc.*;
import facade.IMasterManager;
import facade.MasterManager;
import facade.ModelManager;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController, Observer {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private IMasterManager master;
	private PlayerInfo localPlayer;
	private GameInfo localGame;
	private Game gameModel;
	private ArrayList<String> takenColors, newTakenColors;
	private final int NUMBER_OF_PLAYERS = 4;
	
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
		this.master.getModelManager().addObserver(this);
		takenColors = new ArrayList();
		newTakenColors = new ArrayList();
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
	public void start()
	{
		this.generateGameList();
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
		
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame()
	{
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
			String response = master.createGame(randHexes, randNums, randPorts, title);
			GameInfoJSON newGame = new GameInfoJSON();
			newGame = newGame.fromJSON(response);

			// Add the creator of the game to the game
			String responseJoin = master.joinGame(newGame.getId(), "white");
			
			// get the newest list of games
			this.generateGameList();
			getNewGameView().closeModal();
		}
		else
		{
			messageView.setTitle("ERROR:");
			messageView.setMessage("Invalid name!");
			messageView.showModal();	
		}
	}

	@Override
	public void startJoinGame(GameInfo game)
	{
		takenColors = new ArrayList();
		localGame = game;
		
//		I need to add the game id to the cookie here
		master.setGameCookie(game.getId());
		master.getGameModel(-2);
		
		this.enableAllColors();
		for(PlayerInfo p : localGame.getPlayers())
		{
			if(p.getColorCatan() != null)
			{
				if(takenColors.size() < NUMBER_OF_PLAYERS)
					takenColors.add(p.getColorCatan().toString());
				getSelectColorView().setColorEnabled(p.getColorCatan(), false);
				
				if(master.getPlayer() != null)
				{
					// for re-join
					if(p.getName().equalsIgnoreCase(master.getPlayer().name()))
					{
						getSelectColorView().setColorEnabled(p.getColorCatan(), true);
					}
				}
			}
		}
		
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
		this.generateGameList();
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color)
	{
		localPlayer.setColorCatan(color);
		String response = master.joinGame(localGame.getId(), color.toString().toLowerCase());
//		System.out.println("joinGameController RESPONSE: " + response);
		
		// Check for failure...it won't fail if same color is chosen
		if(response.equals("Success"))
		{
			getSelectColorView().closeModal();
			getJoinGameView().closeModal();
			joinAction.execute();
		}
		else
		{
			messageView.setTitle("ERROR:");
			messageView.setMessage("Something went wrong when trying to join the game!");
			messageView.showModal();
		}
	}
	
	public void generateGameList()
	{
		String JSON = master.getGameList();
		System.out.println("JoinGameController json:" + JSON + ":");
		ArrayList<GameInfo> games = new ArrayList<GameInfo>();
		GameInfoJSON gameInfo = new GameInfoJSON();
		GameInfoJSON[] gameInfoArray;
		
//		System.out.println("JoinGameController json2:" + JSON + ":");

		gameInfoArray = gameInfo.getGamesArrayFromJSON(JSON);
		for(GameInfoJSON gameJSON : gameInfoArray)
		{
			GameInfo game = new GameInfo();
			game.setId(gameJSON.getId());
			game.setTitle(gameJSON.getTitle());
			for(PlayerInfo p : gameJSON.getPlayers())
			{
				// Set the CatanColor if the player has a color
				if(p.getColor() != null)
					p.setColor(p.getColor());
				if(!p.getName().equals("") || p.getId() != -1)
				{
					game.addPlayer(p);
				}
			}
			games.add(game);
		}
		
		// Set info for current player
		this.localPlayer.setId(master.getPlayerID());
		this.localPlayer.setName(master.getPlayerName());
		
		// Convert ArrayList to Array
		GameInfo[] gamesArray = new GameInfo[games.size()];
		games.toArray(gamesArray);
	
		getJoinGameView().setGames(gamesArray, this.localPlayer);
		
	}
	public void enableAllColors()
	{
		CatanColor colors[] = new CatanColor[] {CatanColor.BLUE, CatanColor.BROWN, CatanColor.GREEN, CatanColor.ORANGE,
				CatanColor.PUCE, CatanColor.PURPLE, CatanColor.RED, CatanColor.WHITE, CatanColor.YELLOW};
		for(CatanColor c : colors)
		{
			getSelectColorView().setColorEnabled(c, true);
		}
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		System.out.println("UPDATING joinGameController.");
		ModelManager manager = (ModelManager) o;
		gameModel = manager.getCurrentModel();
		
		int i = 0;
		for(Player p : gameModel.players())
		{
			// Add colors the first time through, but not again
			if(!takenColors.contains(p.color().name()) && takenColors.size() < NUMBER_OF_PLAYERS)
				takenColors.add(p.color().name()); 
			
			for(int k = 0; k < takenColors.size(); ++k)
			{
				System.out.print(takenColors.get(k) + " " );
			}
			// Check to replace a player's old color with their new one
			if(takenColors.size() != 0 && i < takenColors.size())
			{
//				System.out.println(p.color().name() +"="+takenColors.get(i)+"--------------Taken colors" + takenColors.get(i) + " INDEX: " + i);
				if(!takenColors.get(i).equals(p.color().name()))
				{
					System.out.println("$$$$$$$$$$$ enabling " + takenColors.get(i));
					getSelectColorView().setColorEnabled(CatanColor.valueOf(takenColors.get(i)), true); 
					takenColors.set(i, p.color().name());
				}
			}
			
			
			i++;
		}
		for(int j = 0; j < takenColors.size(); j++)
		{
			getSelectColorView().setColorEnabled(CatanColor.valueOf(takenColors.get(j)), false);
			
			// for re-join, enable current player's old color so they can repick
			if(master.getPlayer() != null)
			{
				if(takenColors.get(j).equalsIgnoreCase(master.getPlayer().color().toString()))
				{
					getSelectColorView().setColorEnabled(master.getPlayer().color(), true);
				}
			}
			else
			{
				System.out.println("NULLLLLLLLLLLL PLAYERRRRRRRRRRRR (joinGameController)");
			}
		}

	}

}

