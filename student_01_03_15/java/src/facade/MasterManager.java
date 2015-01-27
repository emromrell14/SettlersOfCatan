package facade;

import proxy.IProxy;

public abstract class MasterManager implements IMasterManager
{
	
	private GameManager mGameManager;
	private GamesManager mGamesManager;
	private MovesManager mMovesManager;
	private UserManager mUserManager;
	private UtilManager mUtilManager;
	protected IProxy mIProxy;
//	private static MasterManager mInstance;
	
	/**
	 * Facade to manage all other managers. 
	 */
	private MasterManager()
	{
		
	}

//	public static MasterManager getInstance() 
//	{
//		if(mInstance == null)
//		{
//			mInstance = new MasterManager();
//		}
//		return mInstance;
//	}
	
	/**
	 * Checks all preconditions for logging in.
	 * @pre User is already registered
	 * @post User logs in iff login parameters are valid.
	 * 
	 * @return true if the user can login, false if they cannot
	 */
	public boolean canLogin() 
	{
		return true;
	}

	/** 
	 * Checks all preconditions for user registration
	 *  @pre none
	 *  @post User is registered and logged in
	 *  
	 * @return true if they can register, false if the user cannot
	 */
	public boolean canRegister() 
	{
		return true;
	}

	/**
	 * Validates the Cookie.
	 * @pre 
	 * @post 
	 * @return true if the user is logged in, false otherwise
	 */
	public boolean isLoggedIn()
	{
		return true;
	}

	/**
	 * Checks all preconditions for road building
	 * @return true if a road can be built, false otherwise
	 */
	public boolean canBuildRoad()
	{
		return true;
	}

	/**
	 * Checks all preconditions for building a new settlement
	 * @return true if a settlement can be built, false otherwise
	 */
	public boolean canBuildSettlement() 
	{
		return true;
	}
	
	/**
	 * Checks all preconditions for building a city.
	 * @return	true if a city can be built, false otherwise
	 */
	public boolean canBuildCity() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for buying a development card.
	 * @return true if a card can be bought, false otherwise
	 */
	public boolean canBuyDevCard()
	{
		return true;
	}

	/**
	 * Checks all preconditions for playing a development card.
	 * @return true if a card can be played, false otherwise
	 */
	public boolean canPlayDevCard() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for offering a resource card trade.
	 * @return true if a trade can be offered, false otherwise
	 */
	public boolean canOfferTrade() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for accepting a trade.
	 * @return true if the trade can be accepted, false otherwise
	 */
	public boolean canAcceptTrade() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for making a maritime trade.
	 * @return true if a maritime trade can be made, false otherwise
	 */
	public boolean canMaritimeTrade() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for rolling the dice.
	 * @return true if the dice can be rolled, false otherwise
	 */
	public boolean canRollDice() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for discarding a card (when a 7 is rolled)
	 * @return true if the card can be discarded, false otherwise
	 */
	public boolean canDiscard()
	{
		return true;
	}

	// FROM THERE DOWN IS STRAIGHT FROM SWAGGER
	// User Manager
	/**
	 * Validates the player's credentials, and logs them into the server. (i.e., sets their catan.user HTTP cookie)
	 * 
	 * @return a String of JSON
	 */
	public String login()
	{
		return null;
	}

	/**
	 * Creates a new player account, and logs them into the server. (i.e., sets up their catan.user HTTP cookie)
	 * 
	 * @return a String of JSON
	 */
	public String register() 
	{
		return null;
	}

	// Games Manager
	/**
	 * gets a list of all games in the progress
	 * 
	 * @return a String of JSON
	 */
	public String getGameList()
	{
		return null;
	}

	/**
	 * Creates a new game
	 * 
	 * @return a String of JSON
	 */
	public String createGame()
	{
		return null;
	}

	/**
	 * Adds (or re-adds) the player to the specified game, and sets their catan.game HTTP cookie
	 * 
	 * @return a String of JSON
	 */
	public String joinGame()
	{
		return null;
	}

	/**
	 * Saves the current state of the specified game to a file
	 * 
	 * @return a String of JSON
	 */
	public String saveGame()
	{
		return null;
	}

	/**
	 * Loads a previously saved game file to restore the state of the game
	 * 
	 * @return a String of JSON
	 */
	public String loadGame() 
	{
		return null;
	}

	// Game Manager
	/**
	 * Requests json String with game model info
	 * 
	 * @return json String with game model in it
	 */
	public String getGameModel()
	{
		return null;
	}
	
	/**
	 * Resets current game to the original state of initial placement round
	 * 
	 * @return new GameModel json String
	 */
	public String resetGame()
	{
		return null;
	}
	
	/**
	 * Executes list of commands in the current game
	 * 
	 * @return json String client model identical to GameModel
	 */
	public String executeCommandList()
	{
		return null;
	}

	/**
	 * Requests list of already executed commands
	 * 
	 * @return json String of list of executed commands in the current game
	 */
	public String getCommandList() 
	{
		return null;
	}
	
	/**
	 * Adds an AI player to the game
	 *
	 */
	public void addAIPlayer() 
	{
		
	}

	/**
	 * Requests list of AI Players
	 * 
	 * @return json String of list of AI Players
	 */
	public String getAIPlayers()
	{
		return null;
	}

	// Moves Manager

	/**
	 * Sends a chat message
	 * 
	 * @return JSON String with the client model
	 */
	public String sendChatMessage() 
	{
		return null;
	}
	/**
	 * Used to roll a unmber at the beginning of your turn
	 * 
	 * @return JSON String with the client model
	 */
	public String rollDice()
	{
		return null;
	}
	/**
	 * Moves the robber, selecting the new robber position and player to rob
	 * 
	 * @return JSON String with the client model
	 */
	public String robPlayer() 
	{
		return null;
	}
	/**
	 * Used to finish your turn
	 * 
	 * @return JSON String with the client model
	 */
	public String finishTurn() 
	{
		return null;
	}
	/**
	 * Used to buy a development card
	 * 
	 * @return JSON String with the client model
	 */
	public String buyDevCard() 
	{
		return null;
	}
	/**
	 * Plays a "Year of Plenty" card from your hand to gain the two specified resources
	 * 
	 * @return JSON String with the client model
	 */
	public String playYearOfPlenty()
	{
		return null;
	}
	/**
	 * Plays a "Road Building" card from your hand to build two roads at the specified locations
	 * 
	 * @return JSON String with the client model
	 */
	public String playRoadBuilding() 
	{
		return null;
	}
	/**
	 * Plays a "Soldier" from your hand, selecting the new robber position and player to rob
	 * 
	 * @return JSON String with the client model
	 */
	public String playSoldier()
	{
		return null;
	}
	/**
	 * Plays a "Monopoly" card from your hand to monopolize the specified resource
	 * 
	 * @return JSON String with the client model
	 */
	public String playMonopoly() 
	{
		return null;
	}
	/**
	 * Plays a "Monument" card from your hand to give you a victory point
	 * 
	 * @return JSON String with the client model
	 */
	public String playMonument() 
	{
		return null;
	}
	/**
	 * Builds a road at the specified location. (Set 'free' to true during initial setup)
	 * 
	 * @return JSON String with the client model
	 */
	public String buildRoad() 
	{
		return null;
	}
	/**
	 * Builds a settlement at the specified location. (Set 'free' to true during initial setup)
	 * 
	 * @return JSON String with the client model
	 */
	public String buildSettlement() 
	{
		return null;
	}
	/**
	 * Builds a city at the specified location
	 * 
	 * @return JSON String with the client model
	 */
	public String buildCity() 
	{
		return null;
	}
	/**
	 * Offers a domestic trade to another player
	 * 
	 * @return JSON String with the client model
	 */
	public String offerTrade() 
	{
		return null;
	}
	/**
	 * Used to accept or reject a trade offered to you
	 * 
	 * @return JSON String with the client model
	 */
	public String acceptTrade() 
	{
		return null;
	}
	/**
	 * Used to execute a maritime trade
	 * 
	 * @return JSON String with the client model
	 */
	public String executeMaritimeTrade()
	{
		return null;
	}
	/**
	 * Discards the specified resource cards
	 * 
	 * @return JSON String with the client model
	 */
	public String discardCards() 
	{
		return null;
	}

	// Util Manager
	/**
	 * Sets the server's log level (ALL, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, OFF)
	 * 
	 * @return JSON string
	 */
	public String changeLogLevel() 
	{
		return null;
	}
}
