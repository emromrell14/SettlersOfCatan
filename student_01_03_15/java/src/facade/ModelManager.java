package facade;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import models.Game;
import models.Index;
import models.Player;
import models.ResourceList;
import models.Status;

public class ModelManager {
	private Game mGameModel;
	
	public ModelManager() {}

	public Game gameModel()
	{
		return mGameModel;
	}

	public void updateModel(Game newGameModel) 
	{
		mGameModel = newGameModel;
	}

	/**
	 * Checks all preconditions for user registration
	 * 
	 * @pre Username and password not null
	 * @post User is registered and logged in
	 * 
	 * @return true if they can register, false if the user cannot
	 */
	public boolean canRegister() 
	{
		return true;
	}

	/**
	 * Checks all preconditions for road building
	 * 
	 * @pre Player is logged in, has joined a game, has a road, has 1 wood 1
	 *      brick, and it is their turn. Dice have also already been rolled.
	 * @post none
	 * @return true if a road can be built, false otherwise
	 */
	public boolean canAffordRoad(int playerID) 
	{
		Player p = this.mGameModel.getPlayer(playerID);
		if (mGameModel.turnTracker().currentTurn().equals(p.playerIndex()) && // Checks that this player is in the game
			(mGameModel.turnTracker().status().equals(Status.FIRSTROUND) || // Checks that it is this player's turn
			mGameModel.turnTracker().status().equals(Status.SECONDROUND))) 
		{
			return true;

		}
		else if (
				!mGameModel.players().contains(p) || // Checks that this player is in this game
				!mGameModel.turnTracker().currentTurn().equals(p.playerIndex()) || // Checks that it is this player's turn
				!mGameModel.turnTracker().status().equals(Status.PLAYING) // Checks that the dice has been rolled
		)
		{

			return false;
		}
		return p.canAffordRoad();
	}
	
	/**
	 * This function will check the player and the board to see if this is an okay spot for a road
	 * 
	 * @param playerID
	 * @param loc
	 * @return If this place is an eligible edge for a road
	 */
	public boolean canPlaceRoad(int playerID, EdgeLocation loc)
	{
		Player p = this.mGameModel.getPlayer(playerID);
		if (mGameModel.turnTracker().currentTurn().equals(p.playerIndex()) &&
				(mGameModel.turnTracker().status() == Status.FIRSTROUND ||
				mGameModel.turnTracker().status() == Status.SECONDROUND)
		)
		{
			//This is the first or second round, use the overloaded function, passing in the location of the last settlement
			if (p.canPlaceRoad(loc, p.settlements().get(p.settlements().size()-1).location()))
			{
				return true;
			}
			return false;
		}
		else
		{
			//This is a regular round, use the the regular function
			if (p.canPlaceRoad(loc) && this.mGameModel.board().canPlaceRoad(loc))
			{
				return true;
			}
			return false;
		}
	}

	
	/**
	 * Builds a road at the specified location
	 * 
	 * @pre player must have necessary resources to build a road
	 * @pre location (edge) must be adjacent to existing road or building built by this player
	 * @pre location (edge) must not already be occupied
	 * @post road will be built where specified
	 * @post player's resources will be decreased according to building cost of road
	 * @post bank's resources will be increased accoring to building cost of road
	 * @return JSON String with the client model
	 */
	public String buildRoad(int playerID, EdgeLocation loc) 
	{
		Player p = mGameModel.getPlayer(playerID);
		p.buildRoad(loc);
		mGameModel.bank().addBrick(1);
		mGameModel.bank().addWood(1);
		mGameModel.board().buildRoad(mGameModel.getPlayerIndex(playerID),loc);
		return "";
	}
	
	/**
	 * Checks all preconditions for building a new settlement
	 * 
	 * @pre Player is logged in, has joined a game, has a settlement, 1 wood 1
	 *      brick 1 wheat 1 sheep, a valid place to build, and it is their turn.
	 *      Dice have also already been rolled.
	 * @post none
	 * @return true if a settlement can be built, false otherwise
	 */
	public boolean canAffordSettlement(int playerID) 
	{
		Player p = this.mGameModel.getPlayer(playerID);
		if (mGameModel.turnTracker().currentTurn().equals(p.playerIndex()) && // Checks that it is this player's turn
				(mGameModel.turnTracker().status().equals(Status.FIRSTROUND) || // Checks if this is first or
				mGameModel.turnTracker().status().equals(Status.SECONDROUND)) // second round (special cases)
		) 
		{
			return true;
		}
		else if (!mGameModel.players().contains(p) || // Checks that this player is in this game
				!mGameModel.turnTracker().currentTurn().equals(p.playerIndex()) || // Checks that it is this player's turn
				!mGameModel.turnTracker().status().equals(Status.PLAYING) // Checks that the dice has been rolled
		)
		{
			return false;
		}
		return p.canAffordSettlement();
	}

	public boolean canPlaceSettlement(int playerID, VertexLocation loc) 
	{
		if (mGameModel.turnTracker().status() != Status.FIRSTROUND &&
				mGameModel.turnTracker().status() != Status.SECONDROUND)
		{
			if (!this.mGameModel.getPlayer(playerID).canPlaceSettlement(loc))
			{
				return false;
			}
		}
		return this.mGameModel.board().canPlaceSettlement(loc);

	}

	/**
	 * Builds a settlement at the specified location. (Set 'free' to true during initial setup)
	 * @pre player must have less than 5 settlements built
	 * @pre location specified must connect to an existing road built by this player
	 * @pre location (vertex) must not already be occupied
	 * @pre player must have necessary resources to build settlement
	 * @pre location must not be within two road distances (edges) from an existing settlement or city
	 * @post a settlement will be built where specified
	 * @post resources will be decreased according to building costs
	 * @return JSON String with the client model
	 */
	public String buildSettlement(int playerID, VertexLocation loc) 
	{
		Player p = mGameModel.getPlayer(playerID);
		p.buildSettlement(loc);
		mGameModel.bank().addBrick(1);
		mGameModel.bank().addWood(1);
		mGameModel.bank().addSheep(1);
		mGameModel.bank().addWheat(1);

		mGameModel.board().buildSettlement(mGameModel.getPlayerIndex(playerID),loc);
		return "";
	}
	
	/**
	 * Checks all preconditions for building a city.
	 * 
	 * @pre Player is logged in, has joined a game, has a city, 3 ore 2 wheat, a
	 *      settlement to build on, and it is their turn. Dice have also already
	 *      been rolled.
	 * @post none
	 * @return true if a city can be built, false otherwise
	 */
	public boolean canAffordCity(int playerID) 
	{
		Player p = mGameModel.getPlayer(playerID);
		if (
				!mGameModel.players().contains(p) || // Checks that this player is in this game
				!mGameModel.turnTracker().currentTurn().equals(p.playerIndex()) || // Checks that it is this player's turn
				!mGameModel.turnTracker().status().equals(Status.PLAYING) // Checks that the dice has been rolled
		)
		{
			return false;
		}
		return p.canAffordCity();
	}

	public boolean canPlaceCity(int playerID, VertexLocation loc) 
	{
		return this.mGameModel.getPlayer(playerID).canPlaceCity(loc);
	}

	public String buildCity(int playerID, VertexLocation loc) 
	{
		Player p = mGameModel.getPlayer(playerID);
		p.buildCity(loc);
		mGameModel.bank().addOre(3);
		mGameModel.bank().addWheat(2);

		mGameModel.board().buildCity(mGameModel.getPlayerIndex(playerID),loc);
		return "";
	}
	
	/**
	 * Checks all preconditions for buying a development card.
	 * 
	 * @pre Player is logged in, joined a game, has 1 sheep 1 wheat 1 ore, it is
	 *      their turn, and there are development cards in bank. Dice have also
	 *      already been rolled.
	 * @post none
	 * @return true if a card can be bought, false otherwise
	 */
	public boolean canBuyDevCard(int playerID)
	{
		Player p = mGameModel.getPlayer(playerID);
		if (
				!mGameModel.players().contains(p) || // Checks that this player is in this game
				!mGameModel.turnTracker().currentTurn().equals(p.playerIndex()) || // Checks that it is this player's turn
				!mGameModel.turnTracker().status().equals(Status.PLAYING) || // Checks that the dice has been rolled
				mGameModel.devCards().isEmpty() // Checks that there are still Dev Cards to buy
		)
		{
			return false;
		}
		return p.canBuyDevCard();
	}

	/**
	 * Checks all preconditions for playing a development card.
	 * 
	 * @pre Player is logged in, in a game, it is their turn, they own a dev
	 *      card, they have not played a dev card this turn other than victory
	 *      points, they didn't buy the dev card this turn. Dice have also
	 *      already been rolled.
	 * @post none
	 * @return true if a card can be played, false otherwise
	 */
	public boolean canPlayDevCard(int playerID) 
	{
		if (
				!mGameModel.players().contains(this) || // Checks that this player is in this game
				mGameModel.turnTracker().currentTurn().value() != playerID || // Checks that it is this player's turn
				!mGameModel.turnTracker().status().equals(Status.PLAYING) // Checks that the dice has been rolled
		)
		{
			return false;
		}
		return this.mGameModel.getPlayer(playerID).canPlayDevCard();
	}

	/**
	 * Checks all preconditions for offering a resource card trade.
	 * 
	 * @pre Player is logged in, playing a game, it is their turn, they have
	 *      resource cards, other players have resource cards. Dice have also
	 *      already been rolled.
	 * @post none
	 * @return true if a trade can be offered, false otherwise
	 */
	public boolean canOfferTrade(int playerID) 
	{
		boolean toReturn = false;
		Index playerIndex = mGameModel.getPlayerIndex(playerID);
		if (mGameModel.turnTracker().isPlayersTurn(playerIndex)) {
			toReturn = mGameModel.canOfferTrade(playerID);
		}

		return toReturn;
	}

	/**
	 * Checks all preconditions for accepting a trade.
	 * 
	 * @pre Player is logged in, playing a game, it is not their turn, they have
	 *      requested resource cards. Dice have also already been rolled.
	 * @post none
	 * @param playerID
	 *            ID of the player
	 * @param tradeOffer
	 *            List of cards that another player wants to trade for.
	 * @return true if the trade can be accepted, false otherwise
	 */
	public boolean canAcceptTrade(int playerID, ResourceList tradeOffer) 
	{
		boolean toReturn = false;
		Player player = mGameModel.getPlayer(playerID);
		if (!mGameModel.turnTracker().isPlayersTurn(player.playerIndex())
				&& mGameModel.turnTracker().hasRolled()) {
			toReturn = player.canAcceptTrade(tradeOffer);
		}
		return toReturn;
	}

	/**
	 * Checks all preconditions for making a maritime trade.
	 * 
	 * @pre Player is logged in, playing a game, it is their turn, they own
	 *      enough cards for the maritime rate.
	 * @post none
	 * @return true if a maritime trade can be made, false otherwise
	 */
	public boolean canMaritimeTrade(int playerID) {
		boolean toReturn = false;
		Player player = mGameModel.getPlayer(playerID);
		if (mGameModel.turnTracker().isPlayersTurn(player.playerIndex())) 
		{
			toReturn = player.canMaritimeTrade();
		}

		return toReturn;
	}

	/**
	 * Checks all preconditions for rolling the dice.
	 * 
	 * @pre Player is logged in, playing a game, it is their turn, they haven't
	 *      already rolled.
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
	 * 
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
		if (this.mGameModel.turnTracker().status() == Status.PLAYING) 
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
		Index playerIndex = mGameModel.getPlayerIndex(playerID);
		if(mGameModel.turnTracker().isPlayersTurn(playerIndex))
		{
			return this.mGameModel.getPlayer(playerID).canPlayYearOfPlenty();
		}
		return false;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Road Builder, false otherwise
	 */
	public boolean canPlayRoadBuilder(int playerID) 
	{
		Index playerIndex = mGameModel.getPlayerIndex(playerID);
		if(mGameModel.turnTracker().isPlayersTurn(playerIndex))
		{
			return this.mGameModel.getPlayer(playerID).canPlayRoadBuilder();
		}
		return false;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use a Soldier, false otherwise
	 */
	public boolean canPlaySoldier(int playerID) 
	{
		Index playerIndex = mGameModel.getPlayerIndex(playerID);
		if(mGameModel.turnTracker().isPlayersTurn(playerIndex))
		{
			return this.mGameModel.getPlayer(playerID).canPlaySoldier();
		}
		return false;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monopoly, false otherwise
	 */
	public boolean canPlayMonopoly(int playerID) 
	{
		Index playerIndex = mGameModel.getPlayerIndex(playerID);
		if(mGameModel.turnTracker().isPlayersTurn(playerIndex))
		{
			return this.mGameModel.getPlayer(playerID).canPlayMonopoly();
		}
		return false;
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monument, false otherwise
	 */
	public boolean canPlayMonument(int playerID) 
	{
		Index playerIndex = mGameModel.getPlayerIndex(playerID);
		if(mGameModel.turnTracker().isPlayersTurn(playerIndex))
		{
			return this.mGameModel.getPlayer(playerID).canPlayMonument();
		}
		return false;
	}

	/**
	 * @pre none
	 * @post none
	 * @param newRobberLocation
	 *            The location of where the player wants to play the robber on
	 *            the board.
	 * @return true if player can place the Robber, false otherwise
	 */
	public boolean canPlaceRobber(int playerID, HexLocation newRobberLocation) 
	{
		Index playerIndex = mGameModel.getPlayerIndex(playerID);
		if(mGameModel.turnTracker().isPlayersTurn(playerIndex))
		{
			HexLocation currentRobberLocation = mGameModel.robber().location();
			return !(currentRobberLocation.getX() == newRobberLocation.getX() && currentRobberLocation
				.getY() == newRobberLocation.getY());
		}
		return false;
	}
	
}
