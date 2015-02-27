package shared.locations;

public enum VertexDirection
{
	West, NorthWest, NorthEast, East, SouthEast, SouthWest, W, NW, NE, E, SE, SW;
	
	private VertexDirection opposite;
	private VertexDirection shortened;
	private VertexDirection lengthened;
	
	static
	{
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;
		
		W.opposite = E;
		NW.opposite = SE;
		NE.opposite = SW;
		E.opposite = W;
		SE.opposite = NW;
		SW.opposite = NE;
		
		West.shortened = W;
		NorthWest.shortened = NW;
		NorthEast.shortened = NE;
		East.shortened = E;
		SouthEast.shortened = SE;
		SouthWest.shortened = SW;
		
		W.shortened = W;
		NW.shortened = NW;
		NE.shortened = NE;
		E.shortened = E;
		SE.shortened = SE;
		SW.shortened = SW;
		
		West.lengthened = West;
		NorthWest.lengthened = NorthWest;
		NorthEast.lengthened = NorthEast;
		East.lengthened = East;
		SouthEast.lengthened = SouthEast;
		SouthWest.lengthened = SouthWest;
		
		W.lengthened = West;
		NW.lengthened = NorthWest;
		NE.lengthened = NorthEast;
		E.lengthened = East;
		SE.lengthened = SouthEast;
		SW.lengthened = SouthWest;
	}

	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}
	public VertexDirection getShortenedDirection()
	{
		return shortened;
	}
	public VertexDirection getLengthenedDirection()
	{
		return lengthened;
	}
	
}

