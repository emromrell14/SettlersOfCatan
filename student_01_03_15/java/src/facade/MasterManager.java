package facade;

public class MasterManager
{
	/**
	 * Facade to manage all other managers. 
	 */
	public MasterManager()
	{
		
	}
	
	/**
	 * Checks all preconditions for logging in.
	 * 
	 * @return true if the user can login, false if they cannot
	 */
	public boolean canLogin() 
	{
		return true;
	}

	/** 
	 * Checks all preconditions for user registration
	 *  
	 * @return true if they can register, false if the user cannot
	 */
	public boolean canRegister() 
	{
		return true;
	}

	/**
	 * Validates the Cookie.
	 * 
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
	public String login()
	{
		return null;
	}

	public String register() 
	{
		return null;
	}

	// Games Manager
	public String getGameList()
	{
		return null;
	}

	public String createGame()
	{
		return null;
	}

	public String joinGame()
	{
		return null;
	}

	public String saveGame()
	{
		return null;
	}

	public String loadGame() 
	{
		return null;
	}

	// Game Manager
	public String getGameModel()
	{
		return null;
	}

	public String resetGame()
	{
		return null;
	}

	public String executeCommandList()
	{
		return null;
	}

	public String addAIPlayer() 
	{
		return null;
	}

	public String getAIPlayers()
	{
		return null;
	}

	// Moves Manager
	public String sendChatMessage() 
	{
		return null;
	}

	public String rollDice()
	{
		return null;
	}

	public String robPlayer() 
	{
		return null;
	}

	public String finishTurn() 
	{
		return null;
	}

	public String buyDevCard() 
	{
		return null;
	}

	public String playYearOfPlenty()
	{
		return null;
	}

	public String playRoadBuilding() 
	{
		return null;
	}

	public String playSoldier()
	{
		return null;
	}

	public String playMonopoly() 
	{
		return null;
	}

	public String playMonument() 
	{
		return null;
	}

	public String buildRoad() 
	{
		return null;
	}

	public String buildSettlement() 
	{
		return null;
	}

	public String buildCity() 
	{
		return null;
	}

	public String offerTrade() 
	{
		return null;
	}

	public String acceptTrade() 
	{
		return null;
	}

	public String executeMaritimeTrade()
	{
		return null;
	}

	public String discardCards() 
	{
		return null;
	}

	// Util Manager
	public String changeLogLevel() 
	{
		return null;
	}

}
