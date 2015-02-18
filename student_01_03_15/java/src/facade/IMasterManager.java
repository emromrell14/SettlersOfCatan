package facade;

import shared.definitions.*;
import shared.locations.*;
import models.*;

public interface IMasterManager 
{		
	public int getPlayerID();
	
	public Game getCurrentModel();
	
	public void updateModel(Game newGameModel);
	
	public void communicateWithMockProxy();
	
	public void communicateWithRealProxy();

	public boolean canRegister();

	public boolean canBuyDevCard(Index playerIndex);

	public boolean canPlayDevCard(Index playerIndex);

	public boolean canOfferTrade(Index playerIndex);

	public boolean canAcceptTrade(Index playerIndex, ResourceList tradeOffer);

	public boolean canMaritimeTrade(Index playerIndex);

	public boolean canRollDice(Index playerIndex);

	public boolean canDiscard(Index playerIndex);
	
	public boolean canFinishTurn(Index playerIndex);
	
	public boolean canPlayYearOfPlenty(Index playerIndex);
	
	public boolean canPlayRoadBuilder(Index playerIndex);

	public boolean canPlaySoldier(Index playerIndex);

	public boolean canPlayMonopoly(Index playerIndex);

	public boolean canPlayMonument(Index playerIndex);

	public boolean canPlaceRobber(HexLocation newRobberLocation);
	
	public boolean canAffordRoad(Index playerIndex);
	
	public boolean canPlaceRoad(Index playerIndex, EdgeLocation loc);
	
	public boolean canAffordSettlement(Index playerIndex);
	
	public boolean canPlaceSettlement(Index playerIndex, VertexLocation loc);
	
	public boolean canAffordCity(Index playerIndex);
	
	public boolean canPlaceCity(Index playerIndex, VertexLocation loc);

	// FROM THERE DOWN IS STRAIGHT FROM SWAGGER
	// User Manager
	public boolean login(String username,String password);

	public boolean register(String username,String password);

	// Games Manager
	public String getGameList();

	public String createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name);

	public String joinGame(int id, String color);

	public String saveGame(int gameId, String fileName);

	public String loadGame(String fileName);

	// Game Manager
	public void getGameModel(int version);

	public void resetGame();

	public void executeCommandList();

	public void addAIPlayer();

	public String getAIPlayers();

	// Moves Manager
	public void sendChatMessage(Index playerIndex,String message);

	public void rollDice(Index playerIndex, int rollNum);

	public void robPlayer(Index playerIndex, Index victimIndex, HexLocation location);

	public void finishTurn(Index playerIndex);

	public void buyDevCard(Index playerIndex);

	public void playYearOfPlenty(Index playerIndex, ResourceType res1, ResourceType res2);

	public void playRoadBuilding(Index playerIndex, EdgeLocation spot1, EdgeLocation spot2);

	public void playSoldier(Index playerIndex, Index victimIndex, HexLocation location);

	public void playMonopoly(ResourceType resource, Index playerIndex);

	public void playMonument(Index playerIndex);

	public void buildRoad(Index playerIndex, EdgeLocation roadLoc, boolean free);

	public void buildSettlement(Index playerIndex, VertexLocation vertexLoc, boolean free);

	public void buildCity(Index playerIndex, VertexLocation vertexLoc, boolean free);

	public void offerTrade(Index playerIndex, ResourceList offer, Index receiverIndex);

	public void acceptTrade(Index playerIndex, boolean willAccept);

	public void executeMaritimeTrade(Index playerIndex, int ratio, ResourceType inputRes, ResourceList outputRes);

	public void discardCards(Index playerIndex, ResourceList cards);

	// Util Manager
	public void changeLogLevel(String log);

}
