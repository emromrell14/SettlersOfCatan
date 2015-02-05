package models;

import java.util.List;

import shared.locations.EdgeLocation;

/** Board class that contains a list of the hexes and a list of the harbors (ports)
*
* @author Team 2
*/
public class Board 
{
	/** A list of all the hexes on the grid */
	private Hex[] mHexes; //A list of all the hexes on the grid - it's only land tiles.
	
	/** A list of all the ports (harbors) on the grid */
	private Port[] mPorts;
	
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
		
	}
	
	/** 
	 * Gets hexes of grid
	 * 
	 * @return a list of all the hexes on the grid
	 */
	public Hex[] hexes()
	{
		return mHexes;
	}
	
	/** 
	 * Gets ports (harbors) of grid
	 * 
	 * @return a list of all the ports on the grid
	 */
	public Port[] ports()
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

	public void buildRoad(Index playerIndex, EdgeLocation loc) {
		this.mRoads.add(new Road(playerIndex, loc));
	}
	
}
