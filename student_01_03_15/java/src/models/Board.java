package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shared.definitions.HexType;
import shared.definitions.PortType;
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

	public Board(boolean randomTiles, boolean randomNumbers, boolean randomPorts)
	{
		mHexes = new ArrayList<Hex>(Arrays.asList(
					new Hex(new HexLocation(0,-2), HexType.WOOD, new TokenValue(11)),
					new Hex(new HexLocation(0,-1), HexType.ORE, new TokenValue(6)),
					new Hex(new HexLocation(0,0), HexType.WHEAT, new TokenValue(11)),
					new Hex(new HexLocation(0,1), HexType.SHEEP, new TokenValue(9)),
					new Hex(new HexLocation(0,2), HexType.WOOD, new TokenValue(6)),
					new Hex(new HexLocation(1,-2), HexType.SHEEP, new TokenValue(12)),
					new Hex(new HexLocation(1,-1), HexType.BRICK, new TokenValue(5)),
					new Hex(new HexLocation(1,0), HexType.WOOD, new TokenValue(4)),
					new Hex(new HexLocation(1,1), HexType.ORE, new TokenValue(3)),
					new Hex(new HexLocation(2,-2), HexType.WHEAT, new TokenValue(9)),
					new Hex(new HexLocation(2,-1), HexType.SHEEP, new TokenValue(10)),
					new Hex(new HexLocation(2,0), HexType.WHEAT, new TokenValue(8)),
					new Hex(new HexLocation(-1,-1), HexType.BRICK, new TokenValue(4)),
					new Hex(new HexLocation(-1,0), HexType.WOOD, new TokenValue(3)),
					new Hex(new HexLocation(-1,1), HexType.SHEEP, new TokenValue(10)),
					new Hex(new HexLocation(-1,2), HexType.WHEAT, new TokenValue(2)),
					new Hex(new HexLocation(-2,0), HexType.DESERT, new TokenValue(0)),
					new Hex(new HexLocation(-2,1), HexType.BRICK, new TokenValue(8)),
					new Hex(new HexLocation(-2,2), HexType.ORE, new TokenValue(5))
					));
		mPorts = new ArrayList<Port>(Arrays.asList(
					new Port(PortType.THREE, new HexLocation(0,-3), EdgeDirection.S),
					new Port(PortType.SHEEP, new HexLocation(2,-3), EdgeDirection.SW),
					new Port(PortType.THREE, new HexLocation(3,-2), EdgeDirection.SW),
					new Port(PortType.THREE, new HexLocation(3,0), EdgeDirection.NW),
					new Port(PortType.BRICK, new HexLocation(1,2), EdgeDirection.N),
					new Port(PortType.WOOD, new HexLocation(-1,3), EdgeDirection.N),
					new Port(PortType.THREE, new HexLocation(-3,3), EdgeDirection.NE),
					new Port(PortType.WHEAT, new HexLocation(-3,1), EdgeDirection.SE),
					new Port(PortType.ORE, new HexLocation(-2,-1), EdgeDirection.SE)
					));
		mRoads = new ArrayList<Road>();
		mSettlements = new ArrayList<Building>();
		mCities = new ArrayList<Building>();
		
		if(randomTiles)
		{
			this.randomizeHexTypes();
		}
		if(randomNumbers)
		{
			this.randomizeTokenValues();
		}
		if(randomPorts)
		{
			this.randomizePorts();
		}
	}
	
	private void randomizeHexTypes()
	{
		for(int i = 0; i < 100; ++i)
		{
			int index1 = (int) (Math.random() * (mHexes.size()-1));
			int index2 = (int) (Math.random() * (mHexes.size()-1));
			
			HexType temp = mHexes.get(index1).resource();
			mHexes.get(index1).setResource(mHexes.get(index2).resource());
			mHexes.get(index2).setResource(temp);
			if(mHexes.get(index1).resource().toString().equalsIgnoreCase(HexType.DESERT.toString()) || 
					mHexes.get(index2).resource().toString().equalsIgnoreCase(HexType.DESERT.toString()))
			{
				TokenValue t = mHexes.get(index1).number();
				mHexes.get(index1).setTokenValue(mHexes.get(index2).number());
				mHexes.get(index2).setTokenValue(t);
			}
		}
	}
	
	private void randomizePorts()
	{
		for(int i = 0; i < 100; ++i)
		{
			int index1 = (int) (Math.random() * (mPorts.size()-1));
			int index2 = (int) (Math.random() * (mPorts.size()-1));
			
			PortType temp = mPorts.get(index1).resource();
			mPorts.get(index1).setPortType(mPorts.get(index2).resource());
			mPorts.get(index2).setPortType(temp);
		}
	}
	
	private void randomizeTokenValues()
	{
		for(int i = 0; i < 100; ++i)
		{
			int index1 = (int) (Math.random() * (mHexes.size()-1));
			int index2 = (int) (Math.random() * (mHexes.size()-1));
			
			if(!mHexes.get(index1).resource().toString().equalsIgnoreCase(HexType.DESERT.toString()) &&
					!mHexes.get(index2).resource().toString().equalsIgnoreCase(HexType.DESERT.toString()))
			{
				TokenValue temp = mHexes.get(index1).number();
				mHexes.get(index1).setTokenValue(mHexes.get(index2).number());
				mHexes.get(index2).setTokenValue(temp);
			}
		}
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
	 * removes the settlement at the specified location from the board
	 * @param vertexLocation
	 */
	public void removeSettlement(VertexLocation vertexLocation) 
	{
		for (Building b : this.mSettlements)
		{
			if (b.location().equals(vertexLocation))
			{
				this.mSettlements.remove(b);
				return;
			}
		}
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
			if (loc.getX() == h.location().getX() && loc.getY() == h.location().getY())
			{
				return h;
			}
		}
		return new Hex(loc, HexType.WATER, new TokenValue(-1));
	}

	public HexLocation getDesertLocation() 
	{
		for(int i = 0; i < mHexes.size(); ++i)
		{
			if(mHexes.get(i).resource() == HexType.DESERT)
			{
				return mHexes.get(i).location();
			}
		}
		
		return null;
	}

	public boolean checkForBuildingOfAnotherPlayer(Index playerIndex, VertexLocation location) {
		location = location.getNormalizedLocation();
		for(Building settlement : this.mSettlements)
		{
			VertexLocation buildingLocation = settlement.location().getNormalizedLocation();
			if(buildingLocation.equals(location) && !settlement.owner().equals(playerIndex))
			{
				return true;
			}
		}
		for(Building city : this.mCities)
		{
			VertexLocation buildingLocation = city.location().getNormalizedLocation();
			if(buildingLocation.equals(location) && !city.owner().equals(playerIndex))
			{
				return true;
			}
		}
		return false;
	}
	
}
