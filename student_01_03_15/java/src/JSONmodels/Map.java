package classes;

import shared.locations.*;

public class Map 
{
	private Hex[] mHexes; //A list of all the hexes on the grid - it's only land tiles.
	private Port[] mPorts;
	private Road[] mRoads;
	private VertexObject[] mSettlements;
	private VertexObject[] mCities;
	private int mRadius; //The radius of the map (it includes the center hex, and the ocean hexes; pass this into the hex grid constructor)
	private HexLocation mRobber; //The current location of the robber
	
	/**
	 * Creates a Map object from all the variables
	 * 
	 * @param hexes
	 * @param ports
	 * @param roads
	 * @param settlements
	 * @param cities
	 * @param radius
	 * @param robber
	 * @return New Map object
	 */
	public Map(Hex[] hexes, Port[] ports, Road[] roads, VertexObject[] settlements, VertexObject[] cities, int radius, HexLocation robber)
	{
		
	}
	
	/**
	 * Creates a Map object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Map object
	 */
	public Map(String JSON)
	{
		
	}
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		
	}
}
