package shared.definitions;

public enum ResourceType
{
	WOOD, BRICK, SHEEP, WHEAT, ORE;
	
	public String toLowerCaseString()
	{
		switch (this)
		{
			case WOOD:
				return "wood";
			case BRICK:
				return "brick";
			case SHEEP:
				return "sheep";
			case WHEAT:
				return "wheat";
			case ORE:
				return "ore";
			default:
				System.out.println("ResourceType toLowerCaseString had invalid enum type");
				return null;
		}
	}
}

