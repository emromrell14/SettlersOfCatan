package JSONmodels;

import java.util.ArrayList;
import java.util.List;

import models.Board;
import models.Building;
import models.DevCard;
import models.Index;
import models.Player;
import models.Road;
import shared.definitions.CatanColor;

import com.google.gson.Gson;

public class PlayerJSON 
{
	private String color; //The color of this player
	private boolean discarded; //Whether this player has discarded or not already this discard phase
	private int monuments; //How many monuments this player has played.
	private String name;
	private DevCardListJSON newDevCards; //The dev cards the player bought this turn
	private DevCardListJSON oldDevCards; //The dev cards the player had when the turn started
	private int playerIndex; //What place in the array is this player? 0-3. It determines their turn order. This is used often everywhere
	private boolean playedDevCard; //Whether the player has played a dev card this turn
	private int playerID; //The unique playerID. This is used to pick the client player apart from the others. This is only used here and in your cookie.
	private ResourceListJSON resources; //The resource cards this player has.
	private int roads;
	private int settlements; //How many settlements this player has left to play.
	private int cities; //How many cities this player has left to play.
	private int soldiers;
	private int victoryPoints;
	
	
	public PlayerJSON(Player player) 
	{
		this.color = player.color().toString().toLowerCase();
		this.discarded = player.hasDiscarded();
		this.monuments = player.monuments();
		this.name = player.name();
		this.newDevCards = new DevCardListJSON(player.getNewDevCards());
		this.oldDevCards = new DevCardListJSON(player.getOldDevCards());
		this.playerIndex = player.playerIndex().value();
		this.playedDevCard = player.hasPlayedDevCard();
		this.playerID = player.playerID();
		this.resources = new ResourceListJSON(player.resources());
		this.roads = player.roadCount();
		this.settlements = player.settlementCount();
		this.cities = player.cityCount();
		this.soldiers = player.soldierCount();
		this.victoryPoints = player.victoryPointCount();		
	}

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
			//player = new Player(CatanColor.valueOf(color.toUpperCase()), this.name, new Index(playerIndex), playerID, resources.getModel(), this.roads, this.settlements, this.cities, this.soldiers, this.victoryPoints, 
				//	discarded, this.playedDevCard, this.monuments, new User(playerID), boardRoads, boardSettlements, boardCities, oldList);
			player = new models.Player(CatanColor.valueOf(color.toUpperCase()), discarded, victoryPoints, name, newList, oldList,
										new Index(playerIndex), playerID, resources.getModel(),this.soldiers, this.victoryPoints,
										this.settlements, this.cities, this.roads,this.playedDevCard);			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		for(Road r : boardRoads)
		{
			if(r.owner().value() == playerIndex)
			{
				player.addRoad(r);
			}
		}
		for(Building b : boardCities)
		{
			if(b.owner().value() == playerIndex)
			{
				player.addCity(b);
			}
		}
		for(Building b : boardSettlements)
		{
			if(b.owner().value() == playerIndex)
			{
				player.addSettlement(b);
			}
		}
		return player;
	}

	private List<DevCard> makeDevCardList(DevCardListJSON list, boolean isNew)
	{
		List<DevCard> devList = new ArrayList<DevCard>();
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
	public static PlayerJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, PlayerJSON.class);
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
	public int getMonuments() {
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
	public DevCardListJSON getNewDevCards() {
		return newDevCards;
	}

	/**
	 * @return the oldDevCards
	 */
	public DevCardListJSON getOldDevCards() {
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
	public ResourceListJSON getResources() {
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
