package facade;

public class MasterManager
{
	public boolean canLogin() 
	{
		return true;
	}

	public boolean canRegister() 
	{
		return true;
	}

	/**
	 * Validates the Cookie.
	 * 
	 * @return If the current user is logged in.
	 */
	public boolean isLoggedIn()
	{
		return true;
	}

	public boolean canBuildRoad()
	{
		return true;
	}

	public boolean canBuildSettlement() 
	{
		return true;
	}

	public boolean canBuildCity() 
	{
		return true;
	}

	public boolean canBuyDevCard()
	{
		return true;
	}

	public boolean canPlayDevCard() 
	{
		return true;
	}

	public boolean canOfferTrade() 
	{
		return true;
	}

	public boolean canAcceptTrade() 
	{
		return true;
	}

	public boolean canMaritimeTrade() 
	{
		return true;
	}

	public boolean canRollDice() 
	{
		return true;
	}

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
