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
	
	public Map(Hex[] hexes, Port[] ports, Road[] roads, VertexObject[] settlements, VertexObject[] cities, int radius, HexLocation robber)
	{
		this.mHexes = hexes;
		this.mPorts = ports;
		this.mRoads = roads;
		this.mSettlements = settlements;
		this.mCities = cities;
		this.mRadius = radius;
		this.mRobber = robber;
	}
	
	public Hex[] hexes()
	{
		return mHexes;
	}
	public Port[] ports()
	{
		return mPorts;
	}
	public Road[] roads()
	{
		return mRoads;
	}
	public VertexObject[] settlements()
	{
		return mSettlements;
	}
	public VertexObject[] cities()
	{
		return mCities;
	}
	public int radius()
	{
		return mRadius;
	}
	public HexLocation robber()
	{
		return mRobber;
	}
}
