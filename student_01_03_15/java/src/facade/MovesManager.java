package facade;

import JSONmodels.ClientModelJSON;
import JSONmodels.EdgeLocationJSON;
import JSONmodels.ResourceListJSON;
import models.Game;
import models.Index;
import models.ResourceList;
import proxy.IProxy;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class MovesManager
{
	private IProxy mProxy;
	
	/**
	 * Creates a MovesManager facade that connects the client to the proxy
	 * @param proxy 
	 * 
	 * @return New MovesManager object
	 */
	public MovesManager()
	{
	}
	
	public void setProxy(IProxy proxy)
	{
		mProxy = proxy;
	}
	
	private Game jsonToGame(String response)
	{
		Game game = null;
		if(!response.contains("Failed"))
		{
			ClientModelJSON model = ClientModelJSON.fromJSON(response);
			game = model.getGameObject();
		}
		return game;
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
	public Game sendChatMessage(Index playerIndex, String message) 
	{
		String response;
		String body;
		
		body = "{type:\"sendChat\", playerIndex:" + playerIndex.value() + ", content:\"" + message + "\"}";
		
		response = mProxy.post("/moves/sendChat", body);
		return jsonToGame(response);
	}
	/**
	 * Used to roll a number at the beginning of your turn
	 * @pre must be this player's turn
	 * @pre must be called once per turn
	 * @post a number (2-12) is returned for determining what happens next 
	 * 		(who gets resources, whether the robber gets moved, etc.)
	 * @return JSON String with the client model
	 */
	public Game rollDice(Index playerIndex, int rollNum)
	{
		String response;
		String body;
		
		body = "{type:\"rollNumber\", playerIndex:" + playerIndex.value() + ", number:" + rollNum + "}";
		
		response = mProxy.post("/moves/rollNumber", body);
		return jsonToGame(response);
	}
	/**
	 * Moves the robber, selecting the new robber position and player to rob
	 * @pre player must have just rolled a seven
	 * @post robber is moved
	 * @post player is given opportunity to choose which player to steal from
	 * @return JSON String with the client model
	 */
	public Game robPlayer(Index playerIndex, Index victimIndex, HexLocation location) 
	{
		String response;
		String body;
		
		body = "{type:\"robPlayer\", playerIndex:" + playerIndex.value() + ", victimIndex:" + victimIndex.value() + 
					", location:{x:\"" + location.getX() + "\", y:\"" + location.getY() + "\"}}";
		
		response = mProxy.post("/moves/robPlayer", body);
		return jsonToGame(response);
	}
	/**
	 * Used to finish your turn
	 * @pre must be called by player who has the current turn
	 * @post turn is moved to next player in order
	 * @return JSON String with the client model
	 */
	public Game finishTurn(Index playerIndex) 
	{
		String response;
		String body;
		
		body = "{type:\"finishTurn\", playerIndex:" + playerIndex.value() + "}";
		
		response = mProxy.post("/moves/finishTurn", body);
		return jsonToGame(response);
	}
	/**
	 * Used to buy a development card
	 * @pre player must have sufficient resources
	 * @post player receives development card
	 * @post player's resource cards decrease by development card cost
	 * @return JSON String with the client model
	 */
	public Game buyDevCard(Index playerIndex) 
	{
		String response;
		String body;
		
		body = "{type:\"buyDevCard\", playerIndex:" + playerIndex.value() + "}";
		
		response = mProxy.post("/moves/buyDevCard", body);
		return jsonToGame(response);
	}
	/**
	 * Plays a "Year of Plenty" card from your hand to gain the two specified resources
	 * @pre Player must have "year of plenty" card
	 * @post Player gains the two specified resources
	 * @return JSON String with the client model
	 */
	public Game playYearOfPlenty(Index playerIndex, ResourceType res1, ResourceType res2)
	{
		String response;
		String body;
		
		body = "{type:\"Year_of_Plenty\", playerIndex:" + playerIndex.value() + ", resource1:\"" + res1.toString().toLowerCase() +
					"\", resource2:\"" + res2.toString().toLowerCase() + "\"}";
		
		response = mProxy.post("/moves/Year_of_Plenty", body);
		return jsonToGame(response);
	}
	/**
	 * Plays a "Road Building" card from your hand to build two roads at the specified locations
	 * @pre player must have road building card
	 * @post player is given opportunity to place two roads
	 * @return JSON String with the client model
	 */
	public Game playRoadBuilding(Index playerIndex, EdgeLocation spot1, EdgeLocation spot2 ) 
	{
		String response;
		String body;
		
		body = "{type:\"Road_Building\", playerIndex:" + playerIndex.value() + ", spot1:{x:" +
					spot1.getHexLoc().getX() + ", y:" + spot1.getHexLoc().getY() + ", direction:\"" + spot1.getDir() + "\"}, spot2:{x:" +
					spot2.getHexLoc().getX() + ", y:" + spot2.getHexLoc().getY() + ", direction:\"" + spot2.getDir() + "\"}}";
		
		response = mProxy.post("/moves/Road_Building", body);
		return jsonToGame(response);
	}
	/**
	 * Plays a "Soldier" from your hand, selecting the new robber position and player to rob
	 * @pre player must have "Soldier" card
	 * @post adds soldier to your "army"
	 * @post removes soldier card from player's hand
	 * @post gives player option to move Robber
	 * @return JSON String with the client model
	 */
	public Game playSoldier(Index playerIndex, Index victimIndex, HexLocation location)
	{
		String response;
		String body;
		
		body = "{type:\"Soldier\", playerIndex:" + playerIndex.value() + ", victimIndex:" + victimIndex.value() + 
					", location:{x:\"" + location.getX() + "\", y:\"" + location.getY() + "\"}}";
		
		response = mProxy.post("/moves/Soldier", body);
		return jsonToGame(response);
	}
	/**
	 * Plays a "Monopoly" card from your hand to monopolize the specified resource
	 * @pre player must have monopoly card
	 * @pre player must specify which resource to monopolize
	 * @post player receives as many of specified resource as all other players have
	 * @post all opposing players will have all of the specified resource discarded
	 * @return JSON String with the client model
	 */
	public Game playMonopoly(ResourceType resource, Index playerIndex) 
	{
		String response;
		String body;
		
		body = "{type:\"Monopoly\", resource:\"" + resource.toString().toLowerCase() + "\", playerIndex:" + playerIndex.value() + "}";
		
		response = mProxy.post("/moves/Monopoly", body);
		return jsonToGame(response);
	}
	/**
	 * Plays a "Monument" card from your hand to give you a victory point
	 * @pre player must have monument card
	 * @post a victory point is awarded to player
	 * @return JSON String with the client model
	 */
	public Game playMonument(Index playerIndex) 
	{
		String response;
		String body;
		
		body = "{type:\"Monument\", playerIndex:" + playerIndex.value() + "}";
		
		response = mProxy.post("/moves/Monument", body);
		return jsonToGame(response);
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
	public Game buildRoad(Index playerIndex, EdgeLocation roadLoc, boolean free) 
	{
		String response;
		String body;
		
		body = "{type:\"buildRoad\", playerIndex:" + playerIndex.value() + ", roadLocation:" + new EdgeLocationJSON(roadLoc).toJSON() + ", free:" +
				free + "}";
		
		response = mProxy.post("/moves/buildRoad", body);
		return jsonToGame(response);
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
	public Game buildSettlement(Index playerIndex, VertexLocation vertexLoc, boolean free) 
	{
		String response;
		String body;
		
		body = "{type:\"buildSettlement\", playerIndex:" + playerIndex.value() + ", vertexLocation:" + 
				"{x:" + vertexLoc.getHexLoc().getX() + ", y:" + vertexLoc.getHexLoc().getY() + ", direction:\"" + shorten(vertexLoc.getDir()) + 
				"\"}, free:" + free + "}";
		
		response = mProxy.post("/moves/buildSettlement", body);
		return jsonToGame(response);
	}
	
	private String shorten(VertexDirection dir)
	{
		switch (dir)
		{
			case West:
			case W:
				return "W";
			case East:
			case E: 
				return "E";
			case NorthWest:
			case NW:
				return "NW";
			case NorthEast:
			case NE: 
				return "NE";
			case SouthWest: 
			case SW:
				return "SW";
			case SouthEast: 
			case SE:
				return "SE";				
			default:
				System.out.println("NULL!!!");
				assert false;
				return null;
		} 			
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
	public Game buildCity(Index playerIndex, VertexLocation vertexLoc, boolean free) 
	{
		String response;
		String body;
		
		body = "{type:\"buildCity\", playerIndex:" + playerIndex.value() + ", vertexLocation:" + 
				"{x:" + vertexLoc.getHexLoc().getX() + ", y:" + vertexLoc.getHexLoc() + ", direction:\"" + vertexLoc.getDir() + 
				"\"}, free:" + free + "}";
		
		response = mProxy.post("/moves/buildCity", body);
		return jsonToGame(response);
	}
	/**
	 * Offers a domestic trade to another player
	 * @pre player must have resources for trade specified. Player
	 * @pre player must own resources they are offering and
	 * @pre player being offered trade must have resource being asked for 
	 * @post a trade is offered
	 * @return JSON String with the client model
	 */
	public Game offerTrade(Index playerIndex, ResourceList offer, Index receiverIndex) 
	{
		String response;
		String body;
		
		body = "{type:\"offerTrade\", playerIndex:" + playerIndex.value() + ", offer:" + new ResourceListJSON(offer).toJSON() + ", receiver:" + 
					receiverIndex + "}";
		
		response = mProxy.post("/moves/offerTrade", body);
		return jsonToGame(response);
	}
	/**
	 * Used to accept or reject a trade offered to you
	 * @pre there must have been a trade offered, resources specified
	 * @post a trade is either accepted or rejected
	 * @return JSON String with the client model
	 */
	public Game acceptTrade(Index playerIndex, boolean willAccept) 
	{
		String response;
		String body;
		
		body = "{type:\"acceptTrade\", playerIndex:" + playerIndex.value() + ", willAccept:" + willAccept + "}";
		
		response = mProxy.post("/moves/acceptTrade", body);
		return jsonToGame(response);
	}
	/**
	 * Used to execute a maritime trade, The ratio of the trade your doing as an integer (ie. put 3 for a 3:1 trade)
	 * @pre must have type of resource to trade and trade for specified
	 * @post adjusts resource amounts according to trade criteria
	 * @return JSON String with the client model
	 */
	public Game executeMaritimeTrade(Index playerIndex, int ratio, ResourceType inputRes, ResourceType outputRes)
	{
		String response;
		String body;
		
		body = "{type:\"maritimeTrade\", playerIndex:" + playerIndex.value() + ", ratio:" + ratio + ", inputResource:\"" +
					inputRes.toString().toLowerCase() + "\", outputResource:\"" + outputRes.toString().toLowerCase() +"\"}";
		
//		System.out.println("maritimeTrade body:"+body);
		
		response = mProxy.post("/moves/maritimeTrade", body);
		return jsonToGame(response);
	}
	/**
	 * Discards the specified resource cards
	 * @pre must have selected the required amount of resource cards to discard
	 * @post specified resource cards will be discarded
	 * @return JSON String with the client model
	 */
	public Game discardCards(Index playerIndex, ResourceList cards) 
	{
		String response;
		String body;
		
		body = "{type:\"discardCards\", playerIndex:" + playerIndex.value() + ", discardedCards:" + new ResourceListJSON(cards).toJSON() + "}";
		
		response = mProxy.post("/moves/discardCards", body);
		return jsonToGame(response);
	}
}
