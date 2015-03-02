package shared.locations;

public enum EdgeDirection
{
	
	NorthWest, North, NorthEast, SouthEast, South, SouthWest, NW, N, NE, SE, S, SW;
	
	private EdgeDirection opposite;
	private EdgeDirection shortened;
	private EdgeDirection lengthened;
	
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
		
		NorthWest.shortened = NW;
		North.shortened = N;
		NorthEast.shortened = NE;
		SouthEast.shortened = SE;
		South.shortened = S;
		SouthWest.shortened = SW;
		
		NW.shortened = NW;
		N.shortened = N;
		NE.shortened = NE;
		SE.shortened = SE;
		S.shortened = S;
		SW.shortened = SW;
		
		NorthWest.lengthened = NorthWest;
		North.lengthened = North;
		NorthEast.lengthened = NorthEast;
		SouthEast.lengthened = SouthEast;
		South.lengthened = South;
		SouthWest.lengthened = SouthWest;
		
		NW.lengthened = NorthWest;
		N.lengthened = North;
		NE.lengthened = NorthEast;
		SE.lengthened = SouthEast;
		S.lengthened = South;
		SW.lengthened = SouthWest;
	}
	
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
	public EdgeDirection getShortenedDirection()
	{
		return shortened;
	}	
	public EdgeDirection getLengthendDirection()
	{
		return lengthened;
	}	
	
//	@Override
//	public String toString()
//	{
//		switch (this)
//		{
//			case NorthWest:
//			case NW:
//				return "NorthWest";
//			case North: 
//			case N: 
//				return "North";
//			case NorthEast:
//			case NE: 
//				return "NorthEast";
//			case SouthWest: 
//			case SW:
//				return "SouthWest";
//			case South:
//			case S:
//				return "South";
//			case SouthEast: 
//			case SE:
//				return "SouthEast";
//				
//			default:
//				System.out.println("NULL!!!");
//				assert false;
//				return null;
//		} 	
//	}
}

