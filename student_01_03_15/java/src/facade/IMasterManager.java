package facade;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import models.Game;
import models.ResourceList;

public interface IMasterManager 
{		
	public Game getCurrentModel();
	
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
	public void getGameModel(int version);

	public String resetGame();

	public String executeCommandList();

	public void addAIPlayer();

	public String getAIPlayers();

	// Moves Manager
	public void sendChatMessage(int playerIndex,String message);

	public void rollDice(int playerIndex, int rollNum);

	public void robPlayer(int playerIndex, int victimIndex, HexLocation location);

	public void finishTurn(int playerIndex);

	public void buyDevCard(int playerIndex);

	public void playYearOfPlenty(int playerIndex, ResourceType res1, ResourceType res2);

	public void playRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2);

	public void playSoldier(int playerIndex, int victimIndex, HexLocation location);

	public void playMonopoly(ResourceType resource, int playerIndex);

	public void playMonument(int playerIndex);

	public void buildRoad(int playerIndex, EdgeLocation roadLoc, boolean free);

	public void buildSettlement(int playerIndex, VertexLocation vertexLoc, boolean free);

	public void buildCity(int playerIndex, VertexLocation vertexLoc, boolean free);

	public void offerTrade(int playerIndex, ResourceList offer, int receiverIndex);

	public void acceptTrade(int playerIndex, boolean willAccept);

	public void executeMaritimeTrade(int playerIndex, int ratio, ResourceType inputRes, ResourceList outputRes);

	public void discardCards(int playerIndex, ResourceList cards);

	// Util Manager
	public String changeLogLevel();

}
