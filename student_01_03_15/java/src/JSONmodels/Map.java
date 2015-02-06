package JSONmodels;

import models.Board;

import com.google.gson.Gson;

public class Map 
{
	private Hex[] hexes; //A list of all the hexes on the grid - it's only land tiles.
	private Port[] ports;
	private EdgeValue[] roads;
	private VertexObject[] settlements;
	private VertexObject[] cities;
	private int radius; //The radius of the map (it includes the center hex, and the ocean hexes; pass this into the hex grid constructor)
	private HexLocation robber; //The current location of the robber
	
	/**
	 * Creates a Map object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New Map object
	 */
	public static Map fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, Map.class);
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
	 * @return the hexes
	 */
	public Hex[] getHexes() {
		return hexes;
	}
	/**
	 * @return the ports
	 */
	public Port[] getPorts() {
		return ports;
	}
	/**
	 * @return the roads
	 */
	public EdgeValue[] getRoads() {
		return roads;
	}
	/**
	 * @return the settlements
	 */
	public VertexObject[] getSettlements() {
		return settlements;
	}
	/**
	 * @return the cities
	 */
	public VertexObject[] getCities() {
		return cities;
	}
	/**
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}
	/**
	 * @return the robber
	 */
	public HexLocation getRobber() {
		return robber;
	}
	
	public models.Robber getModelRobber()
	{
		return new models.Robber(robber.getModelHexLocation());
	}
	
	public Board getModelBoard()
	{
		Board b = new Board();
		addHexesToBoard(b);
		addPortsToBoard(b);
		addRoadsToBoard(b);
		addSettlementsToBoard(b);
		addCitiesToBoard(b);
		return b;
	}
	
	private void addSettlementsToBoard(Board b)
	{
		for(int i = 0; i < settlements.length; ++i)
		{
			b.addSettlement(settlements[i].getModelSettlement());
		}
	}
	
	private void addCitiesToBoard(Board b)
	{
		for(int i = 0; i < cities.length; ++i)
		{
			b.addCity(cities[i].getModelCity());
		}
	}
	
	private void addRoadsToBoard(Board b)
	{
		for(int i = 0; i < roads.length; ++i)
		{
			b.addRoad(roads[i].getModelRoad());
		}
	}
	
	private void addHexesToBoard(Board b)
	{
		for(int i = 0; i < hexes.length; ++i)
		{
			b.addHex(hexes[i].getModelHex());
		}
	}
	
	private void addPortsToBoard(Board b)
	{
		for(int i = 0; i < ports.length; ++i)
		{
			b.addPort(ports[i].getModelPort());
		}
	}
}
