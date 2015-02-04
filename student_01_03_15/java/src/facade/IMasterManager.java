package facade;

import shared.locations.HexLocation;
import models.Game;
import models.ResourceList;

public interface IMasterManager 
{		
	public void updateModel(Game newGameModel);
	
	public void communicateWithMockProxy();
	
	public void communicateWithRealProxy();

	public boolean canLogin();

	public boolean canRegister();

	public boolean isLoggedIn();

	public boolean canBuildRoad();

	public boolean canBuildSettlement();

	public boolean canBuildCity();

	public boolean canBuyDevCard();

	public boolean canPlayDevCard();

	public boolean canOfferTrade(int playerID);

	public boolean canAcceptTrade(int playerID, ResourceList tradeOffer);

	public boolean canMaritimeTrade(int playerID);

	public boolean canRollDice(int playerID);

	public boolean canDiscard();
	
	public boolean canFinishTurn();
	
	public boolean canPlayYearOfPlenty();
	
	public boolean canPlayRoadBuilder();

	public boolean canPlaySoldier();

	public boolean canPlayMonopoly();

	public boolean canPlayMonument();

	public boolean canPlaceRobber(HexLocation newRobberLocation);

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
	public String getGameModel(int version);

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
