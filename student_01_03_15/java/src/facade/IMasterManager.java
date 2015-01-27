package facade;

public interface IMasterManager 
{	
	public boolean canLogin();

	public boolean canRegister();

	/**
	 * Validates the Cookie.
	 * 
	 * @return If the current user is logged in.
	 */
	public boolean isLoggedIn();

	public boolean canBuildRoad();

	public boolean canBuildSettlement();

	public boolean canBuildCity();

	public boolean canBuyDevCard();

	public boolean canPlayDevCard();

	public boolean canOfferTrade();

	public boolean canAcceptTrade();

	public boolean canMaritimeTrade();

	public boolean canRollDice();

	public boolean canDiscard();

	// FROM THERE DOWN IS STRAIGHT FROM SWAGGER
	// User Manager
	public String login();

	public String register();

	// Games Manager
	public String getGameList();

	public String createGame();

	public String joinGame();

	public String saveGame();

	public String loadGame();

	// Game Manager
	public String getGameModel();

	public String resetGame();

	public String executeCommandList();

	public void addAIPlayer();

	public String getAIPlayers();

	// Moves Manager
	public String sendChatMessage();

	public String rollDice();

	public String robPlayer();

	public String finishTurn();

	public String buyDevCard();

	public String playYearOfPlenty();

	public String playRoadBuilding();

	public String playSoldier();

	public String playMonopoly();

	public String playMonument();

	public String buildRoad();

	public String buildSettlement();

	public String buildCity();

	public String offerTrade();

	public String acceptTrade();

	public String executeMaritimeTrade();

	public String discardCards();

	// Util Manager
	public String changeLogLevel();

}
