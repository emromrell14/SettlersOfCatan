package shared.locations;

public enum VertexDirection
{
	West, NorthWest, NorthEast, East, SouthEast, SouthWest; //, W, NW, NE, E, SE, SW;
	
	private VertexDirection opposite;
	
	static
	{
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;
		
		/*
		W.opposite = E;
		NW.opposite = SE;
		NE.opposite = SW;
		E.opposite = W;
		SE.opposite = NW;
		SW.opposite = NE;
		*/
	}
	
	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}
}

