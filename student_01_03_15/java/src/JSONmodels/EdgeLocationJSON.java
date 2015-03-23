package JSONmodels;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

import com.google.gson.Gson;

public class EdgeLocationJSON 
{
	private int x;
	private int y;
	private String direction;
	
	public EdgeLocationJSON(EdgeLocation edgeLoc) {
		this.x = edgeLoc.getHexLoc().getX();
		this.y = edgeLoc.getHexLoc().getY();
		this.direction = Shorten(edgeLoc.getDir());
	}

	private String Shorten(EdgeDirection dir)
	{
		switch (dir)
		{
			case NorthWest:
			case NW:
				return "NW";
			case North: 
			case N: 
				return "N";
			case NorthEast:
			case NE: 
				return "NE";
			case SouthWest: 
			case SW:
				return "SW";
			case South:
			case S:
				return "S";
			case SouthEast: 
			case SE:
				return "SE";
				
			default:
				////System.out.println("NULL!!!");
				assert false;
				return null;
		}
	}
	
	/**
	 * Creates a EdgeLocation object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New EdgeLocation object
	 */
	public static EdgeLocationJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, EdgeLocationJSON.class);
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
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	public HexLocation getHexLocation()
	{
		return new HexLocation(x,y);
	}

	/**
	 * @return the direction
	 */
	public EdgeDirection getDirection() {
		return EdgeDirection.valueOf(direction);
	}
	
	public EdgeLocation getModelEdgeLocation()
	{
		return new EdgeLocation(new shared.locations.HexLocation(x,y), EdgeDirection.valueOf(direction));
	}
}
