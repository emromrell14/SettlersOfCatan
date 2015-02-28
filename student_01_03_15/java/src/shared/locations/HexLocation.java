package shared.locations;

import models.Hex;
import models.TokenValue;
import shared.definitions.HexType;

/**
 * Represents the location of a hex on a hex map
 */
public class HexLocation
{
	
	private int x;
	private int y;
	
	public HexLocation(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	public int getX()
	{
		return x;
	}
	
	private void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	private void setY(int y)
	{
		this.y = y;
	}
	
	@Override
	public String toString()
	{
		return "HexLocation [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		HexLocation other = (HexLocation)obj;
		if(x != other.x)
			return false;
		if(y != other.y)
			return false;
		return true;
	}
	
	public HexLocation getNeighborLoc(EdgeDirection dir)
	{
		switch (dir)
		{
			case NorthWest:
			case NW:
				return new HexLocation(x - 1, y);
			case North:
			case N:
				return new HexLocation(x, y - 1);
			case NorthEast:
			case NE:
				return new HexLocation(x + 1, y - 1);
			case SouthWest:
			case SW:
				return new HexLocation(x - 1, y + 1);
			case South:
			case S:
				return new HexLocation(x, y + 1);
			case SouthEast:
			case SE:	
				return new HexLocation(x + 1, y);
			default:
				System.out.println("NULL HEXLOC Dir!!!");
				assert false;
				return null;
		}
	}

	public boolean isSea() {
		return x >= 3 || x <= -3 || y >= 3 || y <= -3 || (x==1 && y==2) || (x==-1 && y==-2) || (x==2 && y==1) || (x==-2 && y==-1);
	}
	
	public boolean equals(HexLocation h)
	{
		return x == h.x && y == h.y;
	}
	
}

