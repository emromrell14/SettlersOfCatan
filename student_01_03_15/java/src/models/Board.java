package models;

import java.util.ArrayList;
import java.util.List;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/** Board class that contains a list of the hexes and a list of the harbors (ports)
*
* @author Team 2
*/
public class Board 
{
	/** A list of all the hexes on the grid */
	private List<Hex> mHexes; //A list of all the hexes on the grid - it's only land tiles.
	
	/** A list of all the ports (harbors) on the grid */
	private List<Port> mPorts;
	
	private List<Road> mRoads;
	private List<Building> mSettlements;
	private List<Building> mCities;


	/** 
	 * Creates a Board model object
	 * 
	 * @return a new Board object
	 */
	public Board()
	{
		mHexes = new ArrayList();
		mPorts = new ArrayList();
		mRoads = new ArrayList();
		mSettlements = new ArrayList();
		mCities = new ArrayList();
	}
	
	public List<Building> settlements()
	{
		return mSettlements;
	}
	
	public List<Building> cities()
	{
		return mCities;
	}
	
	public List<Road> roads()
	{
		return mRoads;
	}
	
	public void addSettlement(Building b)
	{
		mSettlements.add(b);
	}
	
	public void addCity(Building b)
	{
		mCities.add(b);
	}
	
	public void addRoad(Road r)
	{
		mRoads.add(r);
	}
	
	public void addHex(Hex h)
	{
		mHexes.add(h);
	}
	
	public void addPort(Port p)
	{
		mPorts.add(p);
	}
	
	/** 
	 * Gets hexes of grid
	 * 
	 * @return a list of all the hexes on the grid
	 */
	public List<Hex> hexes()
	{
		return mHexes;
	}
	
	/** 
	 * Gets ports (harbors) of grid
	 * 
	 * @return a list of all the ports on the grid
	 */
	public List<Port> ports()
	{
		return mPorts;
	}

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

	public void buildRoad(Index playerIndex, EdgeLocation loc) 
	{
		this.mRoads.add(new Road(playerIndex, loc));
	}

	public boolean canPlaceSettlement(VertexLocation loc) 
	{
		loc = loc.getNormalizedLocation();
		switch (loc.getDir())
		{
			case NorthWest:
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
	
	public void buildSettlement(Index playerIndex, VertexLocation loc) 
	{
		this.mSettlements.add(new Building(playerIndex, loc));
	}
	
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
}
