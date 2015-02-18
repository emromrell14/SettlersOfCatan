package shared.locations;

public enum EdgeDirection
{
	
	NorthWest, North, NorthEast, SouthEast, South, SouthWest;//, NW, N, NE, SE, S, SW;
	
	private EdgeDirection opposite;
	
	static
	{
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
		
		/*
		NW.opposite = SE;
		N.opposite = S;
		NE.opposite = SW;
		SE.opposite = NW;
		S.opposite = N;
		SW.opposite = NE;
		*/
	}
	
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
	
	@Override
	public String toString()
	{
		switch (this)
		{
			case NorthWest: return "NorthWest";
			case North: return "North";
			case NorthEast: return "NorthEast";
			case SouthWest: return "SouthWest";
			case South: return "South";
			case SouthEast: return "SouthEast";
			default:
				assert false;
				System.out.println("NULL!!!");
				return null;
		} 	
	}
}

