package facade;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import models.Game;
import models.Index;
import models.Status;

public class ModelManager 
{
	private Game mGameModel;
	
	public ModelManager() 
	{
		
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
	public boolean canBuildRoad(EdgeLocation edgeLocation)
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
	public boolean canBuildSettlement(int playerID) 
	{
		if (
				!isLoggedIn() || // Checks that player is logged in
				!mGameModel.players().contains(this) || // Checks that this player is in this game
				mGameModel.turnTracker().getCurrentTurn().getIndex() != playerID || // Checks that it is this player's turn
				!mGameModel.turnTracker().getStatus().equals(Status.PLAYING) // Checks that the dice has been rolled
		)
		{
			return false;
		}
		return this.mGameModel.getPlayer(playerID).canBuildSettlement();
	}
	
	/**
	 * Checks all preconditions for building a city.
	 * @pre Player is logged in, has joined a game, has a city, 3 ore 2 wheat, a settlement to build on, and it is their turn. 
	 * 		Dice have also already been rolled.
	 * @post none
	 * @return	true if a city can be built, false otherwise
	 */
	public boolean canBuildCity(int playerID) 
	{
		if (
				!isLoggedIn() || // Checks that player is logged in
				!mGameModel.players().contains(this) || // Checks that this player is in this game
				mGameModel.turnTracker().getCurrentTurn().getIndex() != playerID || // Checks that it is this player's turn
				!mGameModel.turnTracker().getStatus().equals(Status.PLAYING) // Checks that the dice has been rolled
		)
		{
			return false;
		}
		return this.mGameModel.getPlayer(playerID).canBuildCity();
	}

	/**
	 * Checks all preconditions for buying a development card.
	 * @pre Player is logged in, joined a game, has 1 sheep 1 wheat 1 ore, it is their turn, and there are development cards in bank. 
	 * 		Dice have also already been rolled.
	 * @post none 
	 * @return true if a card can be bought, false otherwise
	 */
	public boolean canBuyDevCard(int playerID)
	{
		if (
				!isLoggedIn() || // Checks that player is logged in
				!mGameModel.players().contains(this) || // Checks that this player is in this game
				mGameModel.turnTracker().getCurrentTurn().getIndex() != playerID || // Checks that it is this player's turn
				!mGameModel.turnTracker().getStatus().equals(Status.PLAYING) || // Checks that the dice has been rolled
				mGameModel.devCards().isEmpty() // Checks that there are still Dev Cards to buy
		)
		{
			return false;
		}
		return this.mGameModel.getPlayer(playerID).canBuyDevCard();
	}

	/**
	 * Checks all preconditions for playing a development card.
	 * @pre Player is logged in, in a game, it is their turn, they own a dev card, they have not played a dev card this turn
	 * 		other than victory points, they didn't buy the dev card this turn. Dice have also already been rolled.
	 * @post none
	 * @return true if a card can be played, false otherwise
	 */
	public boolean canPlayDevCard(int playerID) 
	{
		if (
				!isLoggedIn() || // Checks that player is logged in
				!mGameModel.players().contains(this) || // Checks that this player is in this game
				mGameModel.turnTracker().getCurrentTurn().getIndex() != playerID || // Checks that it is this player's turn
				!mGameModel.turnTracker().getStatus().equals(Status.PLAYING) // Checks that the dice has been rolled
		)
		{
			return false;
		}
		return this.mGameModel.getPlayer(playerID).canPlayDevCard();
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
	 * @params playerID ID of the player
	 * @return true if the dice can be rolled, false otherwise
	 */
	public boolean canRollDice(int playerID) 
	{
		Index playerIndex = mGameModel.getPlayerIndex(playerID);
		return mGameModel.turnTracker().canRollDice(playerIndex);
	}

	/**
	 * Checks all preconditions for discarding a card (when a 7 is rolled)
	 * @pre Player owns the resource card.
	 * @post none
	 * @return true if the card can be discarded, false otherwise
	 */
	public boolean canDiscard(int playerID)
	{
		return this.mGameModel.getPlayer(playerID).canDiscard();
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can finish turn, false otherwise
	 */
	public boolean canFinishTurn(int playerID)
	{
		if(this.mGameModel.turnTracker().status() == Status.PLAYING)
		{
			return true;
		}
		return false;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Year Of Plenty, false otherwise
	 */
	public boolean canPlayYearOfPlenty(int playerID)
	{
		return this.mGameModel.getPlayer(playerID).canPlayYearOfPlenty();
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Road Builder, false otherwise
	 */
	public boolean canPlayRoadBuilder(int playerID)
	{
		return this.mGameModel.getPlayer(playerID).canPlayRoadBuilder();
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use a Soldier, false otherwise
	 */
	public boolean canPlaySoldier(int playerID)
	{
		return this.mGameModel.getPlayer(playerID).canPlaySoldier();
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monopoly, false otherwise
	 */
	public boolean canPlayMonopoly(int playerID)
	{
		return this.mGameModel.getPlayer(playerID).canPlayMonopoly();
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monument, false otherwise
	 */
	public boolean canPlayMonument(int playerID)
	{
		return this.mGameModel.getPlayer(playerID).canPlayMonument();
	}

	/**
	 * @pre none
	 * @post none
	 * @param newRobberLocation The location of where the player wants to play the robber on the board. 
	 * @return true if player can place the Robber, false otherwise
	 */
	public boolean canPlaceRobber(HexLocation newRobberLocation)
	{
		HexLocation currentRobberLocation = mGameModel.robber().location();
		return !(currentRobberLocation.getX() == newRobberLocation.getX()
				&& currentRobberLocation.getY() == newRobberLocation.getY());
	}
}
