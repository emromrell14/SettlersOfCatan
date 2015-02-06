package shared.locations;

public enum EdgeDirection
{
	
	NorthWest, North, NorthEast, SouthEast, South, SouthWest, NW, N, NE, SE, S, SW;
	
	private EdgeDirection opposite;
	
	static
	{
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
		
		NW.opposite = SE;
		N.opposite = S;
		NE.opposite = SW;
		SE.opposite = NW;
		S.opposite = N;
		SW.opposite = NE;
	}
	
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
}

