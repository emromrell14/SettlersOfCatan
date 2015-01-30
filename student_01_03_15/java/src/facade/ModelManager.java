package facade;

import proxy.IProxy;
import models.Game;

public class ModelManager 
{
	private Game mGameModel;
	private IProxy mProxy;
	
	public ModelManager(IProxy proxy) 
	{
		mProxy = proxy;
	}

	public void updateModel(Game newGameModel)
	{
		mGameModel = newGameModel;
	}
	
	/**
	 * Checks all preconditions for logging in.
	 * @pre Username and password not null.
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
	 *  @pre Username and password not null
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
	 * @pre The cookie exists. Player has registered.
	 * @post none
	 * @return true if the user is logged in, false otherwise
	 */
	public boolean isLoggedIn()
	{
		return true;
	}

	/**
	 * Checks all preconditions for road building
	 * @pre Player is logged in, has joined a game, has a road, has 1 wood 1 brick, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a road can be built, false otherwise
	 */
	public boolean canBuildRoad()
	{
		return true;
	}

	/**
	 * Checks all preconditions for building a new settlement
	 * @pre Player is logged in, has joined a game, has a settlement, 1 wood 1 brick 1 wheat 1 sheep, a valid place to build, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a settlement can be built, false otherwise
	 */
	public boolean canBuildSettlement() 
	{
		return true;
	}
	
	/**
	 * Checks all preconditions for building a city.
	 * @pre Player is logged in, has joined a game, has a city, 3 ore 2 wheat, a settlement to build on, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return	true if a city can be built, false otherwise
	 */
	public boolean canBuildCity() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for buying a development card.
	 * @pre Player is logged in, joined a game, has 1 sheep 1 wheat 1 ore, it is their turn, and there are development cards in bank. 
	 * 		Dice have also already been rolled.
	 * @post none 
	 * @return true if a card can be bought, false otherwise
	 */
	public boolean canBuyDevCard()
	{
		return true;
	}

	/**
	 * Checks all preconditions for playing a development card.
	 * @pre Player is logged in, in a game, it is their turn, they own a dev card, they have not played a dev card this turn
	 * 		other than victory points, they didn't buy the dev card this turn. Dice have also already been rolled.
	 * @post none
	 * @return true if a card can be played, false otherwise
	 */
	public boolean canPlayDevCard() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for offering a resource card trade.
	 * @pre Player is logged in, playing a game, it is their turn, they have resource cards, other players have resource cards.  
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if a trade can be offered, false otherwise
	 */
	public boolean canOfferTrade() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for accepting a trade.
	 * @pre Player is logged in, playing a game, it is not their turn, they have requested resource cards. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return true if the trade can be accepted, false otherwise
	 */
	public boolean canAcceptTrade() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for making a maritime trade.
	 * @pre Player is logged in, playing a game, it is their turn, they own enough cards for the maritime rate.
	 * @post none
	 * @return true if a maritime trade can be made, false otherwise
	 */
	public boolean canMaritimeTrade() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for rolling the dice.
	 * @pre Player is logged in, playing a game, it is their turn, they haven't already rolled. 
	 * @post none
	 * @return true if the dice can be rolled, false otherwise
	 */
	public boolean canRollDice() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for discarding a card (when a 7 is rolled)
	 * @pre Player owns the resource card.
	 * @post none
	 * @return true if the card can be discarded, false otherwise
	 */
	public boolean canDiscard()
	{
		return true;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can finish turn, false otherwise
	 */
	public boolean canFinishTurn()
	{
		return true;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Year Of Plenty, false otherwise
	 */
	public boolean canUseYearOfPlenty()
	{
		return true;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Road Builder, false otherwise
	 */
	public boolean canUseRoadBuilder()
	{
		return true;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use a Soldier, false otherwise
	 */
	public boolean canUseSoldier()
	{
		return true;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monopoly, false otherwise
	 */
	public boolean canUseMonopoly()
	{
		return true;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monument, false otherwise
	 */
	public boolean canUseMonument()
	{
		return true;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can place the Robber, false otherwise
	 */
	public boolean canPlaceRobber()
	{
		return true;
	}
}
