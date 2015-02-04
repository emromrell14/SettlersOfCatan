package facade;

import JSONmodels.EdgeLocation;
import proxy.IProxy;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

public class MovesManager
{
	private IProxy mProxy;
	
	/**
	 * Creates a MovesManager facade that connects the client to the proxy
	 * @param proxy 
	 * 
	 * @return New MovesManager object
	 */
	public MovesManager(IProxy proxy)
	{
		mProxy = proxy;
	}
	/**
	 * Sends a chat message
	 * 
	 * @return JSON String with the client model
	 */
	/**
	 * Sends a chat message
	 * @pre none
	 * @post a message is sent to the other plans via the message board
	 * @return JSON String with the client model
	 */
	public String sendChatMessage(int playerIndex, String message) 
	{
		String response;
		String body;
		
		body = "{type:\"sendChat\", playerIndex:" + playerIndex + ", content:\"" + message + "\"}";
		
		response = mProxy.post("/moves/sendChat", body);
		return response;
	}
	/**
	 * Used to roll a number at the beginning of your turn
	 * @pre must be this player's turn
	 * @pre must be called once per turn
	 * @post a number (2-12) is returned for determining what happens next 
	 * 		(who gets resources, whether the robber gets moved, etc.)
	 * @return JSON String with the client model
	 */
	public String rollDice(int playerIndex, int rollNum)
	{
		String response;
		String body;
		
		body = "{type:\"rollNumber\", playerIndex:" + playerIndex + ", number:" + rollNum + "}";
		
		response = mProxy.post("/moves/rollNumber", body);
		return response;
	}
	/**
	 * Moves the robber, selecting the new robber position and player to rob
	 * @pre player must have just rolled a seven
	 * @post robber is moved
	 * @post player is given opportunity to choose which player to steal from
	 * @return JSON String with the client model
	 */
	public String robPlayer(int playerIndex, int victimIndex, HexLocation location) 
	{
		String response;
		String body;
		
		body = "{type:\"robPlayer\", playerIndex:" + playerIndex + ", victimIndex:" + victimIndex + 
					", location:{x:\"" + location.getX() + "\", y:\"" + location.getY() + "\"}}";
		
		response = mProxy.post("/moves/robPlayer", body);
		return response;
	}
	/**
	 * Used to finish your turn
	 * @pre must be called by player who has the current turn
	 * @post turn is moved to next player in order
	 * @return JSON String with the client model
	 */
	public String finishTurn(int playerIndex) 
	{
		String response;
		String body;
		
		body = "{type:\"finishTurn\", playerIndex:" + playerIndex + "}";
		
		response = mProxy.post("/moves/finishTurn", body);
		return response;
	}
	/**
	 * Used to buy a development card
	 * @pre player must have sufficient resources
	 * @post player receives development card
	 * @post player's resource cards decrease by development card cost
	 * @return JSON String with the client model
	 */
	public String buyDevCard(int playerIndex) 
	{
		String response;
		String body;
		
		body = "{type:\"buyDevCard\", playerIndex:" + playerIndex + "}";
		
		response = mProxy.post("/moves/buyDevCard", body);
		return response;
	}
	/**
	 * Plays a "Year of Plenty" card from your hand to gain the two specified resources
	 * @pre Player must have "year of plenty" card
	 * @post Player gains the two specified resources
	 * @return JSON String with the client model
	 */
	public String playYearOfPlenty(int playerIndex, ResourceType res1, ResourceType res2)
	{
		String response;
		String body;
		
		body = "{type:\"Year_of_Plenty\", playerIndex:" + playerIndex + ", resource1:\"" + res1 +
					"\", resource2:\"" + res2 + "\"}";
		
		response = mProxy.post("/moves/Year_of_Plenty", body);
		return response;
	}
	/**
	 * Plays a "Road Building" card from your hand to build two roads at the specified locations
	 * @pre player must have road building card
	 * @post player is given opportunity to place two roads
	 * @return JSON String with the client model
	 */
	public String playRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2 ) 
	{
		String response;
		String body;
		
		body = "{type:\"Road_Building\", playerIndex:" + playerIndex + ", spot1:{x:" +
					spot1.getX() + ", y:" + spot1.getY() + ", direction:\"" + spot1.getDirection() + "\"}, spot2:{x:" +
					spot2.getX() + ", y:" + spot2.getY() + ", direction:\"" + spot2.getDirection() + "\"}}";
		
		response = mProxy.post("/moves/Road_Building", body);
		return response;
	}
	/**
	 * Plays a "Soldier" from your hand, selecting the new robber position and player to rob
	 * @pre player must have "Soldier" card
	 * @post adds soldier to your "army"
	 * @post removes soldier card from player's hand
	 * @post gives player option to move Robber
	 * @return JSON String with the client model
	 */
	public String playSoldier(int playerIndex, int victimIndex, HexLocation location)
	{
		String response;
		String body;
		
		body = "{type:\"Soldier\", playerIndex:" + playerIndex + ", victimIndex:" + victimIndex + 
					", location:{x:\"" + location.getX() + "\", y:\"" + location.getY() + "\"}}";
		
		response = mProxy.post("/moves/Soldier", body);
		return response;
	}
	/**
	 * Plays a "Monopoly" card from your hand to monopolize the specified resource
	 * @pre player must have monopoly card
	 * @pre player must specify which resource to monopolize
	 * @post player receives as many of specified resource as all other players have
	 * @post all opposing players will have all of the specified resource discarded
	 * @return JSON String with the client model
	 */
	public String playMonopoly(ResourceType resource, int playerIndex) 
	{
		String response;
		String body;
		
		body = "{type:\"Monopoly\", resource:\"" + resource + "\", playerIndex:" + playerIndex + "}";
		
		response = mProxy.post("/moves/Monopoly", body);
		return response;
	}
	/**
	 * Plays a "Monument" card from your hand to give you a victory point
	 * @pre player must have monument card
	 * @post a victory point is awarded to player
	 * @return JSON String with the client model
	 */
	public String playMonument(int playerIndex) 
	{
		String response;
		String body;
		
		body = "{type:\"Monument\", playerIndex:" + playerIndex + "}";
		
		response = mProxy.post("/moves/Monument", body);
		return response;
	}
	/**
	 * Builds a road at the specified location. (Set 'free' to true during initial setup)
	 * @pre player must have necessary resources to build a road
	 * @pre location (edge) must be adjacent to existing road or building built by this player
	 * @pre location (edge) must not already be occupied
	 * @post road will be built where specified
	 * @post player's resources will be decreased according to building cost of road
	 * @return JSON String with the client model
	 */
	public String buildRoad(int playerIndex, EdgeLocation roadLoc, boolean free) 
	{
		String response;
		String body;
		
		body = "{type:\"buildRoad\", playerIndex:" + playerIndex + ", roadLocation:" + roadLoc.toJSON() + ", free:" +
				free + "}";
		
		response = mProxy.post("/moves/buildRoad", body);
		return response;
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
	public String buildSettlement() 
	{
		return null;
	}
	/**
	 * Builds a city at the specified location
	 * @pre player must have less than 4 cities built
	 * @pre location specified must be a settlement that player already has built
	 * @pre location (vertex) must not already be occupied
	 * @pre player must have necessary resources to build city
	 * @post a city will replace the player's settlement where specified
	 * @post resources will be decreased according to building costs
	 * @return JSON String with the client model
	 */
	public String buildCity() 
	{
		return null;
	}
	/**
	 * Offers a domestic trade to another player
	 * @pre player must have resources for trade specified. Player
	 * @pre player must own resources they are offering and
	 * @pre player being offered trade must have resource being asked for 
	 * @post a trade is offered
	 * @return JSON String with the client model
	 */
	public String offerTrade() 
	{
		return null;
	}
	/**
	 * Used to accept or reject a trade offered to you
	 * @pre there must have been a trade offered, resources specified
	 * @post a trade is either accepted or rejected
	 * @return JSON String with the client model
	 */
	public String acceptTrade() 
	{
		return null;
	}
	/**
	 * Used to execute a maritime trade
	 * @pre must have type of resource to trade and trade for specified
	 * @post adjusts resource amounts according to trade criteria
	 * @return JSON String with the client model
	 */
	public String executeMaritimeTrade()
	{
		return null;
	}
	/**
	 * Discards the specified resource cards
	 * @pre must have selected the required amount of resource cards to discard
	 * @post specified resource cards will be discarded
	 * @return JSON String with the client model
	 */
	public String discardCards() 
	{
		return null;
	}
}
