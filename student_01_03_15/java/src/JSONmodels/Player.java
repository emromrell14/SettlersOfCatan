package JSONmodels;

import java.util.ArrayList;
import java.util.List;

import models.Board;
import models.Building;
import models.DevCard;
import models.Index;
import models.Road;
import shared.definitions.CatanColor;

import com.google.gson.Gson;

public class Player 
{
	private String color; //The color of this player
	private boolean discarded; //Whether this player has discarded or not already this discard phase
	private Number monuments; //How many monuments this player has played.
	private String name;
	private DevCardList newDevCards; //The dev cards the player bought this turn
	private DevCardList oldDevCards; //The dev cards the player had when the turn started
	private int playerIndex; //What place in the array is this player? 0-3. It determines their turn order. This is used often everywhere
	private boolean playedDevCard; //Whether the player has played a dev card this turn
	private int playerID; //The unique playerID. This is used to pick the client player apart from the others. This is only used here and in your cookie.
	private ResourceList resources; //The resource cards this player has.
	private int roads;
	private int settlements; //How many settlements this player has left to play.
	private int cities; //How many cities this player has left to play.
	private int soldiers;
	private int victoryPoints;
	
	
	public models.Player getModel(Board board)
	{
		List<Road> boardRoads = board.roads();
		List<Building> boardCities = board.cities();
		List<Building> boardSettlements = board.settlements();
		models.Player player = null;
		List<DevCard> newList = makeDevCardList(newDevCards,true);
		List<DevCard> oldList = makeDevCardList(oldDevCards,false);
		
		try 
		{
			player = new models.Player(CatanColor.valueOf(color), discarded, victoryPoints, name, newList, oldList,
										new Index(playerIndex), playerID, resources.getModel(),this.soldiers, this.victoryPoints,
										this.settlements, this.cities, this.roads);			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		for(Road r : boardRoads)
		{
			if(r.owner().index() == playerIndex)
			{
				player.addRoad(r);
			}
		}
		for(Building b : boardCities)
		{
			if(b.owner().index() == playerIndex)
			{
				player.addCity(b);
			}
		}
		for(Building b : boardSettlements)
		{
			if(b.owner().index() == playerIndex)
			{
				player.addSettlement(b);
			}
		}
		return player;
	}
	// call this from createGameModel() and use lines like: 
		//	List<Building> sets = makeBuildings(player.settlementCount(), ?, ?);
		//	List<Building> cits = makeBuildings(player.cityCount(), ?, ?);
//	private List<Building> makeBuildings(int numBuildings, Index index, shared.locations.VertexLocation vertLoc )
//	{
//		List<Building> buildings = new ArrayList();
//		for(int i = 0; i < numBuildings; i++)
//		{
//			buildings.add(new Building(index, vertLoc));
//		}
//		return buildings;
//	}
	
	// call this from createGameModel() and use a line like: 
		//	List<Road> rds = makeRoads(?, ?);
//	private List<Road> makeRoads(Index index, shared.locations.EdgeLocation loc)
//	{
//		List<Road> r = new ArrayList();
//		for(int i = 0; i < this.roads; i++)
//		{
//			r.add(new Road(index, loc));
//		}
//		return r;
//	}
	private List<DevCard> makeDevCardList(DevCardList list, boolean isNew)
	{
		List<DevCard> devList = new ArrayList<>();
		for(int i = 0; i < list.getMonopoly(); i++)
		{
			models.Monopoly m = new models.Monopoly();
			m.setNew(isNew);
			devList.add(m);
		}
		for(int i = 0; i < list.getMonument(); i++)
		{
			devList.add(new models.Monument());
		}
		for(int i = 0; i < list.getRoadBuilding(); i++)
		{
			models.RoadBuild rb = new models.RoadBuild();
			rb.setNew(isNew);
			devList.add(rb);
		}
		for(int i = 0; i < list.getSoldier(); i++)
		{
			models.Soldier s = new models.Soldier();
			s.setNew(isNew);
			devList.add(s);
		}
		for(int i = 0; i < list.getYearOfPlenty(); i++)
		{
			models.YearOfPlenty yop = new models.YearOfPlenty();
			yop.setNew(isNew);
			devList.add(yop);
		}
		return devList;
	}
	
	/**
	 * Creates a Player object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Player object
	 */
	public static Player fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, Player.class);
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return if they discarded this round
	 */
	public boolean hasDiscarded() {
		return discarded;
	}

	/**
	 * @return the monuments
	 */
	public Number getMonuments() {
		return monuments;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the newDevCards
	 */
	public DevCardList getNewDevCards() {
		return newDevCards;
	}

	/**
	 * @return the oldDevCards
	 */
	public DevCardList getOldDevCards() {
		return oldDevCards;
	}

	/**
	 * @return the playerIndex
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * @return the playerDevCard
	 */
	public boolean hasPlayedDevCard() {
		return playedDevCard;
	}

	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * @return the resources
	 */
	public ResourceList getResources() {
		return resources;
	}

	/**
	 * @return the roads
	 */
	public int getRoads() {
		return roads;
	}

	/**
	 * @return the settlements
	 */
	public int getSettlements() {
		return settlements;
	}

	/**
	 * @return the cities
	 */
	public int getCities() {
		return cities;
	}

	/**
	 * @return the soldiers
	 */
	public int getSoldiers() {
		return soldiers;
	}

	/**
	 * @return the victoryPoints
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}
	
}
