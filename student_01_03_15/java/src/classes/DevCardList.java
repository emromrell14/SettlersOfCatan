package classes;

public class DevCardList 
{
	private Number mMonopoly;
	private Number mMonument;
	private Number mRoadBuilding;
	private Number mSoldier;
	private Number mYearOfPlenty;
	
	public DevCardList(Number monopoly, Number monument, Number roadBuilding, Number soldier, Number yearOfPlenty)
	{
		this.mMonopoly = monopoly;
		this.mMonument = monument;
		this.mRoadBuilding = roadBuilding;
		this.mSoldier = soldier;
		this.mYearOfPlenty = yearOfPlenty;
	}
	
	public Number monopoly()
	{
		return mMonopoly;
	}
	public Number monument()
	{
		return mMonument;
	}
	public Number roadBuilding()
	{
		return mRoadBuilding;
	}
	public Number soldier()
	{
		return mSoldier;
	}
	public Number yearOfPlenty()
	{
		return mYearOfPlenty;
	}
}
