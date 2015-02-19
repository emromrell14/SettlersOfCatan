package facade;

import models.Game;
import models.Index;
import models.ResourceList;
import proxy.*;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class MasterManager implements IMasterManager
{
	
	private GameManager mGameManager;
	private GamesManager mGamesManager;
	private MovesManager mMovesManager;
	private UserManager mUserManager;
	private UtilManager mUtilManager;
	private ModelManager mModelManager;
	protected IProxy mProxy;
	private static MasterManager mInstance;
	
	/**
	 * Facade to manage all other managers. 
	 */
	private MasterManager()
	{
		mUserManager = new UserManager();
		mGameManager = new GameManager();
		mGamesManager = new GamesManager();
		mMovesManager = new MovesManager();
		mUtilManager = new UtilManager();
		mModelManager = new ModelManager();
		communicateWithRealProxy();
	}

	public static MasterManager getInstance() 
	{
		if(mInstance == null)
		{
			mInstance = new MasterManager();
		}
		return mInstance;
	}
	
	public int getPlayerID()
	{
		return mProxy.getPlayerID();
	}
	public String getPlayerName()
	{
		return mProxy.getPlayerName();
	}
	
	public Index getPlayerIndex()
	{
		return this.getCurrentModel().getPlayerIndex(mProxy.getPlayerID());
	}
	
	public void updateModel(Game newGameModel)
	{
		if(newGameModel != null)
		{
			mModelManager.updateModel(newGameModel);
		}
	}
	
	public ModelManager getModelManager()
	{
		return this.mModelManager;
	}
	
	public Game getCurrentModel()
	{
		return mModelManager.getCurrentModel();
	}

	public void communicateWithMockProxy()
	{
		mProxy = new MockProxy();
		mUserManager.setProxy(mProxy);
		mGameManager.setProxy(mProxy);
		mGamesManager.setProxy(mProxy);
		mMovesManager.setProxy(mProxy);
		mUtilManager.setProxy(mProxy);
	}
	
	public void communicateWithRealProxy()
	{
		mProxy = new Proxy();
		mUserManager.setProxy(mProxy);
		mGameManager.setProxy(mProxy);
		mGamesManager.setProxy(mProxy);
		mMovesManager.setProxy(mProxy);
		mUtilManager.setProxy(mProxy);
	}

	/** 
	 * Checks all preconditions for user registration
	 *  @pre Username and password not null
	 *  @post User is registered and logged in
	 *  
	 * @return true if they can register, false if the user cannot
	 */
	public boolean canRegister() 
	{
		return mModelManager.canRegister();
	}

	/**
	 * Checks all preconditions for road building
	 * @pre Player is logged in, has joined a game, has a road, has 1 wood 1 brick, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a road can be built, false otherwise
	 */
	public boolean canAffordRoad(Index playerIndex)
	{
		return mModelManager.canAffordRoad(playerIndex);
	}
	
	public boolean canPlaceRoad(Index playerIndex, EdgeLocation loc)
	{
		return mModelManager.canPlaceRoad(playerIndex, loc);
	}

	/**
	 * Checks all preconditions for building a new settlement
	 * @pre Player is logged in, has joined a game, has a settlement, 1 wood 1 brick 1 wheat 1 sheep, a valid place to build, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a settlement can be built, false otherwise
	 */
	public boolean canAffordSettlement(Index playerIndex) 
	{
		return mModelManager.canAffordSettlement(playerIndex);
	}
	
	public boolean canPlaceSettlement(Index playerIndex, VertexLocation loc)
	{
		return mModelManager.canPlaceSettlement(playerIndex, loc);
	}
	
	/**
	 * Checks all preconditions for building a city.
	 * @pre Player is logged in, has joined a game, has a city, 3 ore 2 wheat, a settlement to build on, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return	true if a city can be built, false otherwise
	 */
	public boolean canAffordCity(Index playerIndex) 
	{
		return mModelManager.canAffordCity(playerIndex);
	}
	
	public boolean canPlaceCity(Index playerIndex, VertexLocation loc)
	{
		return mModelManager.canPlaceCity(playerIndex, loc);
	}
	
	/**
	 * Checks all preconditions for buying a development card.
	 * @pre Player is logged in, joined a game, has 1 sheep 1 wheat 1 ore, it is their turn, and there are development cards in bank. 
	 * 		Dice have also already been rolled.
	 * @post none 
	 * @return true if a card can be bought, false otherwise
	 */
	public boolean canBuyDevCard(Index playerIndex)
	{
		return mModelManager.canBuyDevCard(playerIndex);
	}

	/**
	 * Checks all preconditions for playing a development card.
	 * @pre Player is logged in, in a game, it is their turn, they own a dev card, they have not played a dev card this turn
	 * 		other than victory points, they didn't buy the dev card this turn. Dice have also already been rolled.
	 * @post none
	 * @return true if a card can be played, false otherwise
	 */
	public boolean canPlayDevCard(Index playerIndex) 
	{
		return mModelManager.canPlayDevCard(playerIndex);
	}

	/**
	 * Checks all preconditions for offering a resource card trade.
	 * @pre Player is logged in, playing a game, it is their turn, they have resource cards, other players have resource cards.  
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a trade can be offered, false otherwise
	 */
	public boolean canOfferTrade(Index playerIndex) 
	{
		return mModelManager.canOfferTrade(playerIndex);
	}

	/**
	 * Checks all preconditions for accepting a trade.
	 * @pre Player is logged in, playing a game, it is not their turn, they have requested resource cards. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if the trade can be accepted, false otherwise
	 */
	public boolean canAcceptTrade(Index playerIndex, models.ResourceList tradeOffer) 
	{
		return mModelManager.canAcceptTrade(playerIndex,tradeOffer);
	}

	/**
	 * Checks all preconditions for making a maritime trade.
	 * @pre Player is logged in, playing a game, it is their turn, they own enough cards for the maritime rate.
	 * @post none
	 * @return true if a maritime trade can be made, false otherwise
	 */
	public boolean canMaritimeTrade(Index playerIndex) 
	{
		return mModelManager.canMaritimeTrade(playerIndex);
	}

	/**
	 * Checks all preconditions for rolling the dice.
	 * @pre Player is logged in, playing a game, it is their turn, they haven't already rolled. 
	 * @post none
	 * @params playerIndex Index of the player
	 * @return true if the dice can be rolled, false otherwise
	 */
	public boolean canRollDice(Index playerIndex) 
	{
		return mModelManager.canRollDice(playerIndex);
	}

	/**
	 * Checks all preconditions for discarding a card (when a 7 is rolled)
	 * @pre Player owns the resource card.
	 * @post none
	 * @return true if the card can be discarded, false otherwise
	 */
	public boolean canDiscard(Index playerIndex)
	{
		return mModelManager.canDiscard(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can finish turn, false otherwise
	 */
	public boolean canFinishTurn(Index playerIndex)
	{
		return mModelManager.canFinishTurn(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Year Of Plenty, false otherwise
	 */
	public boolean canPlayYearOfPlenty(Index playerIndex)
	{
		return mModelManager.canPlayYearOfPlenty(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Road Builder, false otherwise
	 */
	public boolean canPlayRoadBuilder(Index playerIndex)
	{
		return mModelManager.canPlayRoadBuilder(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use a Soldier, false otherwise
	 */
	public boolean canPlaySoldier(Index playerIndex)
	{
		return mModelManager.canPlaySoldier(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monopoly, false otherwise
	 */
	public boolean canPlayMonopoly(Index playerIndex)
	{
		return mModelManager.canPlayMonopoly(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monument, false otherwise
	 */
	public boolean canPlayMonument(Index playerIndex)
	{
		return mModelManager.canPlayMonument(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @param newRobberLocation The location of where the player wants to play the robber on the board. 
	 * @return true if player can place the Robber, false otherwise
	 */
	public boolean canPlaceRobber(HexLocation newRobberLocation)
	{
		return mModelManager.canPlaceRobber(newRobberLocation);
	}

	// FROM THERE DOWN IS STRAIGHT FROM SWAGGER
	// User Manager
	/**
	 * Validates the player's credentials, and logs them into the server. (i.e., sets their catan.user HTTP cookie)
	 * @pre Username and password != null, canLogin returns true
	 * @post Cookie will be set.
	 * @return a String of JSON
	 */
	public boolean login(String username, String password)
	{
		return mUserManager.login(username, password);
	}

	/**
	 * Creates a new player account, and logs them into the server. (i.e., sets up their catan.user HTTP cookie)
	 * @pre Username and password not null. 
	 * @post Player is logged in and cookie set.
	 * @return a String of JSON
	 */
	public boolean register(String username, String password) 
	{
		return mUserManager.register(username, password);
	}

	// Games Manager
	/**
	 * gets a list of all games in the progress
	 * @pre none
	 * @return a String of JSON
	 */
	public String getGameList()
	{
		return mGamesManager.getGameList();
	}

	/**
	 * Creates a new game
	 * @pre none
	 * @return a String of JSON
	 */
	public String createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name)
	{
		return mGamesManager.createGame(randomTiles, randomNumbers, randomPorts, name);
	}

	/**
	 * Adds (or re-adds) the player to the specified game, and sets their catan.game HTTP cookie
	 * @pre There must not be more than 3 players already in the specified game
	 * @post Player is added to the game
	 * @return a String of JSON
	 */
	public String joinGame(int id, String color)
	{
		return mGamesManager.joinGame(id, color);
	}

	/**
	 * Saves the current state of the specified game to a file
	 * @pre none
	 * @return a String of JSON
	 */
	public String saveGame(int gameId, String fileName)
	{
		return mGamesManager.saveGame(gameId, fileName);
	}

	/**
	 * Loads a previously saved game file to restore the state of the game
	 * pre saved game file must be in parsable JSON format
	 * @return a String of JSON
	 */
	public String loadGame(String fileName) 
	{
		return mGamesManager.loadGame(fileName);
	}

	// Game Manager
	/**
	 * Requests json String with game model info
	 * @pre none
	 * @return the current game version
	 */
	public void getGameModel(int version)
	{
		Game game = mGameManager.getGameModel(version);
		mModelManager.updateModel(game);
//		return mModelManager.gameModel().version();
	}
	
	/**
	 * Resets current game to the original state of initial placement round
	 * @pre none
	 * @post game is reset to initial state
	 * @return new GameModel json String
	 */
	public void resetGame()
	{
		Game game = mGameManager.resetGame();
		this.updateModel(game);
	}
	
	/**
	 * Executes list of commands in the current game
	 * @pre must be valid game commands
	 * @post list of commands is executed
	 * @return json String client model identical to GameModel
	 */
	public void executeCommandList()
	{
		mGameManager.executeCommandList();
	}

	/**
	 * Requests list of already executed commands
	 * @pre none
	 * @post A list of all commands is returned
	 * @return json String of list of executed commands in the current game
	 */
	public String getCommandList() 
	{
		return mGameManager.getCommandList();
	}
	
	/**
	 * Adds an AI player to the game
	 * @pre must not have more than 3 total players already
	 * @post an AI player is added to the game
	 */
	public void addAIPlayer() 
	{
		mGameManager.addAIPlayer();
	}

	/**
	 * Requests list of AI Players
	 * @pre none
	 * @post a list of the current AI players is returned
	 * @return json String of list of AI Players
	 */
	public String getAIPlayers()
	{
		return mGameManager.getAIPlayers();
	}

	// Moves Manager

	/**
	 * Sends a chat message
	 * @pre none
	 * @post a message is sent to the other plans via the message board
	 * @return JSON String with the client model
	 */
	public void sendChatMessage(Index playerIndex,String message) 
	{
		Game game = mMovesManager.sendChatMessage(playerIndex, message);
		this.updateModel(game);
	}
	/**
	 * Used to roll a number at the beginning of your turn
	 * @pre must be this player's turn
	 * @pre must be called once per turn
	 * @post a number (2-12) is returned for determining what happens next 
	 * 		(who gets resources, whether the robber gets moved, etc.)
	 * @return JSON String with the client model
	 */
	public void rollDice(Index playerIndex, int rollNum)
	{
		Game game = mMovesManager.rollDice(playerIndex, rollNum);
		this.updateModel(game);
	}
	/**
	 * Moves the robber, selecting the new robber position and player to rob
	 * @pre player must have just rolled a seven
	 * @post robber is moved
	 * @post player is given opportunity to choose which player to steal from
	 * @return JSON String with the client model
	 */
	public void robPlayer(Index playerIndex, Index victimIndex, HexLocation location) 
	{
		Game game = mMovesManager.robPlayer(playerIndex, victimIndex, location);
		this.updateModel(game);
	}
	/**
	 * Used to finish your turn
	 * @pre must be called by player who has the current turn
	 * @post turn is moved to next player in order
	 * @return JSON String with the client model
	 */
	public void finishTurn(Index playerIndex) 
	{
		Game game = mMovesManager.finishTurn(playerIndex);
		this.updateModel(game);
	}
	/**
	 * Used to buy a development card
	 * @pre player must have sufficient resources
	 * @post player receives development card
	 * @post player's resource cards decrease by development card cost
	 * @return JSON String with the client model
	 */
	public void buyDevCard(Index playerIndex) 
	{
		Game game = mMovesManager.buyDevCard(playerIndex);
		this.updateModel(game);
	}
	/**
	 * Plays a "Year of Plenty" card from your hand to gain the two specified resources
	 * @pre Player must have "year of plenty" card
	 * @post Player gains the two specified resources
	 * @return JSON String with the client model
	 */
	public void playYearOfPlenty(Index playerIndex, ResourceType res1, ResourceType res2)
	{
		Game game = mMovesManager.playYearOfPlenty(playerIndex, res1, res2);
		this.updateModel(game);
	}
	/**
	 * Plays a "Road Building" card from your hand to build two roads at the specified locations
	 * @pre player must have road building card
	 * @post player is given opportunity to place two roads
	 * @return JSON String with the client model
	 */
	public void playRoadBuilding(Index playerIndex, EdgeLocation spot1, EdgeLocation spot2) 
	{
		Game game = mMovesManager.playRoadBuilding(playerIndex, spot1, spot2);
		this.updateModel(game);
	}
	/**
	 * Plays a "Soldier" from your hand, selecting the new robber position and player to rob
	 * @pre player must have "Soldier" card
	 * @post adds soldier to your "army"
	 * @post removes soldier card from player's hand
	 * @post gives player option to move Robber
	 * @return JSON String with the client model
	 */
	public void playSoldier(Index playerIndex, Index victimIndex, HexLocation location)
	{
		Game game = mMovesManager.playSoldier(playerIndex, victimIndex, location);
		this.updateModel(game);
	}
	/**
	 * Plays a "Monopoly" card from your hand to monopolize the specified resource
	 * @pre player must have monopoly card
	 * @pre player must specify which resource to monopolize
	 * @post player receives as many of specified resource as all other players have
	 * @post all opposing players will have all of the specified resource discarded
	 * @return JSON String with the client model
	 */
	public void playMonopoly(ResourceType resource, Index playerIndex) 
	{
		Game game = mMovesManager.playMonopoly(resource, playerIndex);
		this.updateModel(game);
	}
	/**
	 * Plays a "Monument" card from your hand to give you a victory point
	 * @pre player must have monument card
	 * @post a victory point is awarded to player
	 * @return JSON String with the client model
	 */
	public void playMonument(Index playerIndex) 
	{
		Game game = mMovesManager.playMonument(playerIndex);
		this.updateModel(game);
	}
	
	/**
	 * Builds a road at the specified location. (Set 'free' to true during initial setup)
	 * @pre player must have necessary resources to build a road
	 * @pre location (edge) must be adjacent to existing road or building built by this player
	 * @pre location (edge) must not already be occupied
	 * @post road will be built where specified
	 * @post player's resources will be decreased according to building cost of road
	 * @return JSON String with the client model
	 */
	public void buildRoad(Index playerIndex, EdgeLocation roadLoc, boolean free) 
	{
		Game game = mMovesManager.buildRoad(playerIndex,roadLoc,free);
		this.updateModel(game);
	}
	/**
	 * Builds a settlement at the specified location. (Set 'free' to true during initial setup)
	 * @pre player must have less than 5 settlements built
	 * @pre location specified must connect to an existing road built by this player
	 * @pre location (vertex) must not already be occupied
	 * @pre player must have necessary resources to build settlement
	 * @pre location must not be within two road distances (edges) from an existing settlement or city
	 * @post a settlement will be built where specified
	 * @post resources will be decreased according to building costs
	 * @return JSON String with the client model
	 */
	public void buildSettlement(Index playerIndex, VertexLocation vertexLoc, boolean free) 
	{
		Game game = mMovesManager.buildSettlement(playerIndex, vertexLoc,free);
		this.updateModel(game);
	}
	/**
	 * Builds a city at the specified location
	 * @pre player must have less than 4 cities built
	 * @pre location specified must be a settlement that player already has built
	 * @pre location (vertex) must not already be occupied
	 * @pre player must have necessary resources to build city
	 * @post a city will replace the player's settlement where specified
	 * @post resources will be decreased according to building costs
	 * @return JSON String with the client model
	 */
	public void buildCity(Index playerIndex, VertexLocation vertexLoc, boolean free) 
	{
		Game game = mMovesManager.buildCity(playerIndex, vertexLoc, free);
		this.updateModel(game);
	}
	/**
	 * Offers a domestic trade to another player
	 * @pre player must have resources for trade specified. Player
	 * @pre player must own resources they are offering and
	 * @pre player being offered trade must have resource being asked for 
	 * @post a trade is offered
	 * @return JSON String with the client model
	 */
	@Override
	public void offerTrade(Index playerIndex, ResourceList offer, Index receiverIndex) 
	{
		Game game = mMovesManager.offerTrade(playerIndex, offer, receiverIndex);
		this.updateModel(game);
	}
	/**
	 * Used to accept or reject a trade offered to you
	 * @pre there must have been a trade offered, resources specified
	 * @post a trade is either accepted or rejected
	 * @return JSON String with the client model
	 */
	public void acceptTrade(Index playerIndex, boolean willAccept) 
	{
		Game game = mMovesManager.acceptTrade(playerIndex, willAccept);
		this.updateModel(game);
	}
	/**
	 * Used to execute a maritime trade
	 * @pre must have type of resource to trade and trade for specified
	 * @post adjusts resource amounts according to trade criteria
	 * @return JSON String with the client model
	 */
	public void executeMaritimeTrade(Index playerIndex, int ratio, ResourceType inputRes, ResourceList outputRes)
	{
		Game game = mMovesManager.executeMaritimeTrade(playerIndex, ratio, inputRes, outputRes);
		this.updateModel(game);
	}
	/**
	 * Discards the specified resource cards
	 * @pre must have selected the required amount of resource cards to discard
	 * @post specified resource cards will be discarded
	 * @return JSON String with the client model
	 */
	public void discardCards(Index playerIndex, ResourceList cards) 
	{
		Game game = mMovesManager.discardCards(playerIndex, cards);
		this.updateModel(game);
	}

	// Util Manager
	/**
	 * Sets the server's log level (ALL, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, OFF)
	 * @pre must be one of the levels enumerated above
	 * @post sets the server's log level
	 * @return JSON string
	 */
	public void changeLogLevel(String log) 
	{
		mUtilManager.changeLogLevel(log);
	}
}
