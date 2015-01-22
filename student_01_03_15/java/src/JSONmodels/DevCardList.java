package classes;

public class DevCardList 
{
	private int mMonopoly;
	private int mMonument;
	private int mRoadBuilding;
	private int mSoldier;
	private int mYearOfPlenty;
	
	/**
	 * Creates a DevCardList object from all the variables
	 * 
	 * @param monopoly
	 * @param monument
	 * @param roadBuilding
	 * @param soldier
	 * @param yearOfPlenty
	 * @return New DevCardList object
	 */
	public DevCardList(int monopoly, int monument, int roadBuilding, int soldier, int yearOfPlenty)
	{
		
	}
	
	/**
	 * Creates a DevCardList object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New DevCardList object
	 */
	public DevCardList(String JSON)
	{
		this.mMonopoly = monopoly;
		this.mMonument = monument;
		this.mRoadBuilding = roadBuilding;
		this.mSoldier = soldier;
		this.mYearOfPlenty = yearOfPlenty;
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public toJSON()
	{
		
	}
}
