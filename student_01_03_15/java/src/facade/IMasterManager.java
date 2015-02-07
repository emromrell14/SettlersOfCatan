package facade;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import models.Game;
import models.ResourceList;

public interface IMasterManager 
{		
	public void updateModel(Game newGameModel);
	
	public void communicateWithMockProxy();
	
	public void communicateWithRealProxy();

	public boolean canRegister();

	public boolean canBuyDevCard(int playerID);

	public boolean canPlayDevCard(int playerID);

	public boolean canOfferTrade(int playerID);

	public boolean canAcceptTrade(int playerID, ResourceList tradeOffer);

	public boolean canMaritimeTrade(int playerID);

	public boolean canRollDice(int playerID);

	public boolean canDiscard(int playerID);
	
	public boolean canFinishTurn(int playerID);
	
	public boolean canPlayYearOfPlenty(int playerID);
	
	public boolean canPlayRoadBuilder(int playerID);

	public boolean canPlaySoldier(int playerID);

	public boolean canPlayMonopoly(int playerID);

	public boolean canPlayMonument(int playerID);

	public boolean canPlaceRobber(HexLocation newRobberLocation);
	
	public boolean canAffordRoad(int playerID);
	
	public boolean canPlaceRoad(int playerID, EdgeLocation loc);
	
	public boolean canAffordSettlement(int playerID);
	
	public boolean canPlaceSettlement(int playerID, VertexLocation loc);
	
	public boolean canAffordCity(int playerID);
	
	public boolean canPlaceCity(int playerID, VertexLocation loc);

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

	public String buildRoad(int playerID, EdgeLocation loc);

	public String buildSettlement();

	public String buildCity();

	public String offerTrade();

	public String acceptTrade();

	public String executeMaritimeTrade();

	public String discardCards();

	// Util Manager
	public String changeLogLevel();

}
