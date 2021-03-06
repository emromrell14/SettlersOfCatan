package facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import models.Building;
import models.Game;
import models.Index;
import models.Message;
import models.Player;
import models.Status;

public class ModelManager extends Observable
{
	private Game mGameModel;
	
	public ModelManager() {}

	public Game gameModel()
	{
		return mGameModel;
	}

	public void updateModel(Game newGameModel) 
	{
		mGameModel = newGameModel;
//		System.out.println("ModelManager - " + mGameModel.version());
		setModelChanged();
		notifyObservers();
	}
	
	public Game getCurrentModel()
	{
		return mGameModel;
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
	public boolean canAffordRoad(Index playerIndex) 
	{
		Player p = this.mGameModel.getPlayer(playerIndex);
				
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
	public boolean canPlaceRoad(Index playerIndex, EdgeLocation loc)
	{
		Player p = mGameModel.getPlayer(playerIndex);
		if (mGameModel.turnTracker().currentTurn().equals(playerIndex) &&
				(mGameModel.turnTracker().status() == Status.FIRSTROUND ||
				mGameModel.turnTracker().status() == Status.SECONDROUND)
		)
		{
			//If they don't have any settlement, return false
			if(p.settlements().size() != 0)
			{
				//This is the first or second round, use the overloaded function, passing in the location of the last settlement
				for(Building settlement : p.settlements())
				{
					if(!p.doesSettlementHaveRoadAttached(settlement))
					{
						return p.canPlaceRoad(loc, settlement.location());
					}
				}
			}
			return false;
		}
		else
		{
			//This is a regular round, use the the regular function
			if (!p.canPlaceRoad(loc))
			{
				return false;
			}
			
			if(!this.mGameModel.board().canPlaceRoad(loc))
			{
				return false;
			}
			
			// Check for the weird condition...
			if(this.mGameModel.buildingThroughAnotherPlayer(p, loc)) {
				return false;
			}
			
			return true;
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
	public String buildRoad(Index index, EdgeLocation loc) 
	{
		Player p = mGameModel.getPlayer(index);
		p.buildRoad(loc);
		mGameModel.bank().addBrick(1);
		mGameModel.bank().addWood(1);
		mGameModel.board().buildRoad(index,loc);
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
	public boolean canAffordSettlement(Index playerIndex) 
	{
		Player p = this.mGameModel.getPlayer(playerIndex);

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

	public boolean canPlaceSettlement(Index playerIndex, VertexLocation loc) 
	{
		if (mGameModel.turnTracker().status() != Status.FIRSTROUND &&
				mGameModel.turnTracker().status() != Status.SECONDROUND)
		{
			if (!this.mGameModel.getPlayer(playerIndex).canPlaceSettlement(loc))
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
	public boolean canAffordCity(Index playerIndex) 
	{
		Player p = mGameModel.getPlayer(playerIndex);
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

	public boolean canPlaceCity(Index playerIndex, VertexLocation loc) 
	{
		return this.mGameModel.getPlayer(playerIndex).canPlaceCity(loc);
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
	
	
	public boolean canBuyDevCard(Index playerIndex)
	{
		return mGameModel.canBuyDevCard(playerIndex);
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
	public boolean canPlayDevCard(Index playerIndex) 
	{
		Player p = mGameModel.getPlayer(playerIndex);
		if (
				!mGameModel.players().contains(p) || // Checks that this player is in this game
				!mGameModel.turnTracker().currentTurn().equals(p.playerIndex()) || // Checks that it is this player's turn
				!mGameModel.turnTracker().status().equals(Status.PLAYING) // Checks that the dice has been rolled
		)
		{
			return false;
		}
		return p.canPlayDevCard();
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
	public boolean canOfferTrade(Index playerIndex) 
	{
		return mGameModel.canOfferTrade(playerIndex, null);
	}

	/**
	 * Checks all preconditions for accepting a trade.
	 * 
	 * @pre Player is logged in, playing a game, it is not their turn, they have
	 *      requested resource cards. Dice have also already been rolled.
	 * @post none
	 * @param playerIndex
	 *            ID of the player
	 * @param tradeOffer
	 *            List of cards that another player wants to trade for.
	 * @return true if the trade can be accepted, false otherwise
	 */
	public boolean canAcceptTrade(Index playerIndex) 
	{
		return this.mGameModel.canAcceptTrade(playerIndex, true);
	}

	/**
	 * Checks all preconditions for making a maritime trade.
	 * 
	 * @pre Player is logged in, playing a game, it is their turn, they own
	 *      enough cards for the maritime rate.
	 * @post none
	 * @return true if a maritime trade can be made, false otherwise
	 */
	public boolean canMaritimeTrade(Index playerIndex) 
	{
		return this.mGameModel.canMaritimeTrade(playerIndex, 0, null, null);
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
	public boolean canRollDice(Index playerIndex) 
	{
		return this.mGameModel.canRollDice(playerIndex, 0);
	}

	/**
	 * Checks all preconditions for discarding a card (when a 7 is rolled)
	 * 
	 * @pre Player owns the resource card.
	 * @post none
	 * @return true if the card can be discarded, false otherwise
	 */
	public boolean canDiscard(Index playerIndex) 
	{
		return this.mGameModel.canDiscardCards(playerIndex, null);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can finish turn, false otherwise
	 */
	public boolean canFinishTurn(Index playerIndex) 
	{
		return this.mGameModel.canFinishTurn(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Year Of Plenty, false otherwise
	 */
	public boolean canPlayYearOfPlenty(Index playerIndex) 
	{
		return this.mGameModel.canPlayYearOfPlenty(playerIndex, null, null);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Road Builder, false otherwise
	 */
	public boolean canPlayRoadBuilder(Index playerIndex) 
	{
		return this.mGameModel.canPlayRoadBuilding(playerIndex, null, null);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use a Soldier, false otherwise
	 */
	public boolean canPlaySoldier(Index playerIndex) 
	{
		return this.mGameModel.canPlaySoldier(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monopoly, false otherwise
	 */
	public boolean canPlayMonopoly(Index playerIndex) 
	{
		return this.mGameModel.canPlayMonopoly(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @return true if player can use Monument, false otherwise
	 */
	public boolean canPlayMonument(Index playerIndex) 
	{
		return this.mGameModel.canPlayMonument(playerIndex);
	}

	/**
	 * @pre none
	 * @post none
	 * @param newRobberLocation
	 *            The location of where the player wants to play the robber on
	 *            the board.
	 * @return true if player can place the Robber, false otherwise
	 */
	public boolean canPlaceRobber(HexLocation newRobberLocation) 
	{
		return this.mGameModel.canRobPlayer(null, null, newRobberLocation);
	}

	public int getPlayerPoints(int playerID) 
	{
		return mGameModel == null? 0 : mGameModel.getPlayer(playerID).victoryPointCount();
	}
	
	public List<Message> getLog()
	{
		return mGameModel == null? null : mGameModel.log();
	}
	public void setModelChanged() 
	{
		setChanged();		
	}

	public List<Player> getRobbingVictims(HexLocation hexLoc)
	{
		return mGameModel == null? new ArrayList<Player>() : mGameModel.getRobbingVictims(hexLoc);
	}

	public int getLargestArmyIndex()
	{
		return mGameModel == null? -1 : mGameModel.getLargestArmyIndex();
	}

	public int getLongestRoadIndex() 
	{
		return mGameModel == null? -1 : mGameModel.getLongestRoadIndex();
	}

	public Player getWinner()
	{
		Index winner = mGameModel.winner();
		return winner.value() == -1? null : mGameModel.getPlayer(winner);
	}
}
