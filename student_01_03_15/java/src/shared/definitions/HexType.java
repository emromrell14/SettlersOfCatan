package shared.definitions;

public enum HexType
{
	
	WOOD, BRICK, SHEEP, WHEAT, ORE, DESERT, WATER;
	
	private ResourceType mResourceType;
	
	static 
	{
		WOOD.mResourceType = ResourceType.WOOD;
		BRICK.mResourceType = ResourceType.BRICK;
		SHEEP.mResourceType = ResourceType.SHEEP;
		WHEAT.mResourceType = ResourceType.WHEAT;
		ORE.mResourceType = ResourceType.ORE;
		DESERT.mResourceType = null;
		WATER.mResourceType = null;
	}
	
	public ResourceType resourceType()
	{
		return mResourceType;
	}
}

