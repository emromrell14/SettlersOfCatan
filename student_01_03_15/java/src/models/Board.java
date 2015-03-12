package models;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.HexType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/** Board class that contains a list of the hexes and a list of the harbors (ports)
*
* @author Team 2
*/
public class Board 
{
	/** A list of all the hexes(land tiles only) on the grid */
	private List<Hex> mHexes; 
	
	/** A list of all the ports (harbors) on the grid */
	private List<Port> mPorts;
	/** A list of all the roads on the grid */
	private List<Road> mRoads;
	/** A list of all the settlements on the grid */
	private List<Building> mSettlements;
	/** A list of all the cities on the grid */
	private List<Building> mCities;
	

	/** 
	 * Creates a Board model object
	 * 
	 * @return a new Board object
	 */
	public Board()
	{
		mHexes = new ArrayList<Hex>();
		mPorts = new ArrayList<Port>();
		mRoads = new ArrayList<Road>();
		mSettlements = new ArrayList<Building>();
		mCities = new ArrayList<Building>();
	}
	/**
	 * Gets list of settlements
	 * @pre none
	 * @return List<Building> settlements
	 */
	public List<Building> settlements()
	{
		return mSettlements;
	}
	/**
	 * Gets list of cities
	 * @pre none
	 * @return List<Building> cities
	 */
	public List<Building> cities()
	{
		return mCities;
	}
	/**
	 * Gets list of roads
	 * @pre none
	 * @return List<Road> roads
	 */
	public List<Road> roads()
	{
		return mRoads;
	}
	/**
	 * Adds a settlement to board's list
	 * @pre none
	 * @param b   building to add(Settlement)
	 */
	public void addSettlement(Building b)
	{
		mSettlements.add(b);
	}
	/**
	 * Adds a city to board's list
	 * @pre none
	 * @param b   building to add(City)
	 */
	public void addCity(Building b)
	{
		mCities.add(b);
	}
	/**
	 * Adds a road to board's list
	 * @pre none
	 * @param r   road to add(road)
	 */
	public void addRoad(Road r)
	{
		mRoads.add(r);
	}
	/**
	 * Add's hex to board
	 * @pre none
	 * @param h    hex to add
	 */
	public void addHex(Hex h)
	{
		mHexes.add(h);
	}
	/**
	 * Add's port to board
	 * @pre none
	 * @param p   port to add
	 */
	public void addPort(Port p)
	{
		mPorts.add(p);
	}
	
	/** 
	 * Gets hexes of grid
	 * @pre none
	 * @return a list of all the hexes on the grid
	 */
	public List<Hex> hexes()
	{
		return mHexes;
	}
	
	/** 
	 * Gets ports (harbors) of grid
	 * @pre none
	 * @return a list of all the ports on the grid
	 */
	public List<Port> ports()
	{
		return mPorts;
	}

	/**
	 * if Player can place road in location
	 * @pre loc is not null
	 * @param loc   location of where the player wants to place road
	 * @return boolean if player can/can't place road
	 */
	public boolean canPlaceRoad(EdgeLocation loc)
	{
		loc = loc.getNormalizedLocation();
		for (Road road : mRoads)
		{
			EdgeLocation edge = road.location().getNormalizedLocation();
			if (loc.equals(edge))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Builds road in location
	 * @pre playerIndex is not null
	 * @param playerIndex	player's Index of who's building the road
	 * @param loc	location to build road
	 */
	public void buildRoad(Index playerIndex, EdgeLocation loc) 
	{
		this.mRoads.add(new Road(playerIndex, loc));
	}

	/**
	 * If player can place settlement in location
	 * @pre loc is not null
	 * @post true if the settlement can be placed
	 * @param loc	vertex location of where player wants to build settlement
	 * @return boolean if player can place settlement in location
	 */
	public boolean canPlaceSettlement(VertexLocation loc) 
	{		
		loc = loc.getNormalizedLocation();
		

		// Check that vertex is not already taken
		if (checkForBuilding(loc))
		{
			return false;
		}	
			
		switch (loc.getDir().getLengthenedDirection())
		{
			case NorthWest:
				// Check that the vertex touches a resource
				if (
						getHexByLocation(loc.getHexLoc()).resource() == HexType.WATER &&
						getHexByLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest)).resource() == HexType.WATER &&
						getHexByLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.North)).resource() == HexType.WATER
				)
				{
					return false;
				}
				
				// Check that this vertex is not adjacent to another vertex
				if (
					!checkForBuilding(new VertexLocation(loc.getHexLoc(),VertexDirection.NorthEast)) &&
					!checkForBuilding(new VertexLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest),VertexDirection.NorthEast)) &&
					!checkForBuilding(new VertexLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest),VertexDirection.NorthEast))
				)
				{
					return true;
				}
				break;
			case NorthEast:
				// Check that the vertex touches a resource
				if (
						getHexByLocation(loc.getHexLoc()).resource() == HexType.WATER &&
						getHexByLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast)).resource() == HexType.WATER &&
						getHexByLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.North)).resource() == HexType.WATER
				)
				{
					return false;
				}
				// Check that this vertex is not adjacent to another vertex
				if (
					!checkForBuilding(new VertexLocation(loc.getHexLoc(),VertexDirection.NorthWest)) &&
					!checkForBuilding(new VertexLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast),VertexDirection.NorthWest)) &&
					!checkForBuilding(new VertexLocation(loc.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast),VertexDirection.NorthWest))
				)
				{
					return true;
				}
				break;
			default:
				break;
		}
			
		return false;	
	}
	/**
	 * Checks vertex location to see if a building exists
	 * @pre loc is not null
	 * @post true if building exists at loc
	 * @param loc	location to check for builing
	 * @return boolean 		if location is taken or not by building
	 */
	public boolean checkForBuilding(VertexLocation loc)
	{
		loc = loc.getNormalizedLocation();
		for(Building settlement : this.mSettlements)
		{
			VertexLocation vertex = settlement.location().getNormalizedLocation();
			if(loc.equals(vertex))
			{
				return true;
			}
		}
		for(Building city : this.mCities)
		{
			VertexLocation vertex = city.location().getNormalizedLocation();
			if(loc.equals(vertex))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * Builds settlement in location
	 * @pre playerIndex and loc are not null
	 * @post settlement is built at loc
	 * @param playerIndex	Index of player building the settlement
	 * @param loc	vertex location of where the settlement is going to be built
	 */
	public void buildSettlement(Index playerIndex, VertexLocation loc) 
	{
		this.mSettlements.add(new Building(playerIndex, loc));
	}
	/**
	 * Builds city over settlement in location
	 * @pre playerIndex and loc are not null
	 * @post city is built at loc and settlement is added back to player
	 * @param playerIndex	Index of player building the city
	 * @param loc	location of the settlement the player is building(new city) over
	 */
	public void buildCity(Index playerIndex, VertexLocation loc)
	{
		loc = loc.getNormalizedLocation();
		for(Building settlement : this.mSettlements)
		{
			VertexLocation vertex = settlement.location().getNormalizedLocation();
			if(loc.equals(vertex))
			{
				//Remove settlement, change to a city, and add to cities
				this.mSettlements.remove(settlement);
				settlement.setBuildingTypeToCity();
				this.mCities.add(settlement);
				break;
			}
		}
	}
	/**
	 * Gets hex from hexLocation
	 * @pre loc is not null
	 * @post the hex at loc
	 * @param loc	hexLocation, coordinates
	 * @return	hex from the coordinates, or new hex type water(blank hex)
	 */
	private Hex getHexByLocation(HexLocation loc)
	{
		for (Hex h : mHexes)
		{
			if (loc.getX() == h.getHexLocation().getX() && loc.getY() == h.getHexLocation().getY())
			{
				return h;
			}
		}
		return new Hex(loc, HexType.WATER, new TokenValue(-1));
	}
}
