package server;

public interface IServerFacade {
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post none
	 */
	void executeRollDiceCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The message log will have another entry
	 */	
	void executeSendChatMessageCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The robber's location will be changed
	 * @post The robbing player will take possession of one of the victim's cards
	 */
	void executeRobPlayerCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The turn tracker will change the current turn
	 */
	void executeFinishTurnCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post A dev card will be added to the player
	 * @post The dev card bank will be depleted by one
	 * @post The player will give 3 resources to the bank
	 */
	void executeBuyDevCardCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 * @post The player will gain the specified resources
	 */
	void executePlayYearOfPlentyCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 * @post The player will gain the specified resources
	 */
	void executePlayMonopolyCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 * @post The player will gain one victory point
	 */
	void executePlayMonumentCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 */
	void executePlayRoadBuildingCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will not be able to play any other dev cards this turn
	 */
	void executePlaySoldierCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will gain a road
	 * @post The board will gain a road
	 * @post The player will give 2 cards to the bank
	 */
	void executeBuildRoadCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will gain a settlement
	 * @post The board will gain a settlement
	 * @post The player will give 4 cards to the bank
	 */
	void executeBuildSettlementCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will gain a city
	 * @post The board will gain a city
	 * @post The player will give 5 resources to the bank
	 */
	void executeBuildCityCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The trade object will now contain the specified trade
	 */
	void executeOfferTradeCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The trade object will be turned back to null
	 * @post The players involved will gain/give the specified resources
	 */
	void executeAcceptTradeCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player involved will gain/give the specified resources
	 */
	void executeMaritimeTradeCommand();
	
	/**
	 * Creates a command object, then executes the command
	 * @pre Game has started
	 * @post The player will give the specified resources to the bank
	 * @post The player will not have to discard again this turn
	 */
	void executeDiscardCommand();
}
