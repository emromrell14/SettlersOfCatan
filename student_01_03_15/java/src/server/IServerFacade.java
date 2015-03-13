package server;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import models.Index;
import models.ResourceList;

public interface IServerFacade {
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post Everyone will get the specified resources
	 * @post Turn Tracker will update
	 * @param playerIndex
	 * @param rollNum
	 */
	void executeRollDiceCommand(Index playerIndex, int rollNum);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The message log will have another entry
	 * @param playerIndex
	 * @param message
	 */
	void executeSendChatMessageCommand(Index playerIndex, String message);

	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The robber's location will be changed
	 * @post The robbing player will take possession of one of the victim's cards
	 * @param playerIndex
	 * @param victimIndex
	 * @param location
	 */
	void executeRobPlayerCommand(Index playerIndex, int victimIndex, HexLocation location);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The turn tracker will change the current turn
	 * @param playerIndex
	 */
	void executeFinishTurnCommand(Index playerIndex);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post A dev card will be added to the player
	 * @post The dev card bank will be depleted by one
	 * @post The player will give 3 resources to the bank
	 * @param playerIndex
	 */
	void executeBuyDevCardCommand(Index playerIndex);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 * @post The player will gain the specified resources
	 * @param playerIndex
	 * @param resourceType1
	 * @param resourceType2
	 */
	void executePlayYearOfPlentyCommand(Index playerIndex, ResourceType resourceType1, ResourceType resourceType2);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 * @post The player will gain the specified resources
	 * @param playerIndex
	 * @param resource
	 */
	void executePlayMonopolyCommand(Index playerIndex, ResourceType resource);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 * @post The player will gain one victory point
	 * @param playerIndex
	 */
	void executePlayMonumentCommand(Index playerIndex);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 * @param playerIndex
	 * @param loc1
	 * @param loc2
	 */
	void executePlayRoadBuildingCommand(Index playerIndex, EdgeLocation loc1, EdgeLocation loc2);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 * @param playerIndex
	 * @param victimIndex
	 * @param loc
	 */
	void executePlaySoldierCommand(Index playerIndex, int victimIndex, HexLocation loc);

	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will gain a road
	 * @post The board will gain a road
	 * @post The player will give 2 cards to the bank
	 * @param playerIndex
	 * @param loc
	 * @param free
	 */
	void executeBuildRoadCommand(Index playerIndex, EdgeLocation loc, boolean free);

	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will gain a settlement
	 * @post The board will gain a settlement
	 * @post The player will give 4 cards to the bank
	 * @param playerIndex
	 * @param loc
	 * @param free
	 */
	void executeBuildSettlementCommand(Index playerIndex, VertexLocation loc, boolean free);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will gain a city
	 * @post The board will gain a city
	 * @post The player will give 5 resources to the bank
	 * @param playerIndex
	 * @param loc
	 * @param free
	 */
	void executeBuildCityCommand(Index playerIndex, VertexLocation loc, boolean free);
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The trade object will now contain the specified trade
	 * @param playerIndex
	 * @param receiveIndex
	 * @param offer
	 */
	void executeOfferTradeCommand(Index playerIndex, Index receiveIndex, ResourceList offer);

	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The trade object will be turned back to null
	 * @post The players involved will gain/give the specified resources
	 * @param playerIndex
	 * @param willAccept
	 */
	void executeAcceptTradeCommand(Index playerIndex, boolean willAccept);

	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player involved will gain/give the specified resources
	 * @param playerIndex
	 * @param ratio
	 * @param inputResource
	 * @param outputResource
	 */
	void executeMaritimeTradeCommand(Index playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource);

	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will give the specified resources to the bank
	 * @post The player will not have to discard again this turn
	 * @param playerIndex
	 * @param cards
	 */
	void executeDiscardCommand(Index playerIndex, ResourceList cards);
}
