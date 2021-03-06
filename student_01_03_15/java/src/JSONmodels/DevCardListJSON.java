package JSONmodels;

import java.util.List;

import models.DevCard;

import com.google.gson.Gson;

public class DevCardListJSON 
{
	private int monopoly;
	private int monument;
	private int roadBuilding;
	private int soldier;
	private int yearOfPlenty;
	
	public DevCardListJSON(List<DevCard> devCards) 
	{
		for(DevCard d : devCards)
		{
			switch (d.type())
			{
				case MONOPOLY:
					monopoly++;
					break;
				case MONUMENT:
					monument++;
					break;
				case ROAD_BUILD:
					roadBuilding++;
					break;
				case SOLDIER:
					soldier++;
					break;
				case YEAR_OF_PLENTY:
					yearOfPlenty++;
					break;
			}
		}
	}

	/**
	 * Creates a DevCardList object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New DevCardList object
	 */
	public static DevCardListJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, DevCardListJSON.class);
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the monopoly
	 */
	public int getMonopoly() {
		return monopoly;
	}

	/**
	 * @return the monument
	 */
	public int getMonument() {
		return monument;
	}

	/**
	 * @return the roadBuilding
	 */
	public int getRoadBuilding() {
		return roadBuilding;
	}

	/**
	 * @return the soldier
	 */
	public int getSoldier() {
		return soldier;
	}

	/**
	 * @return the yearOfPlenty
	 */
	public int getYearOfPlenty() {
		return yearOfPlenty;
	}
}
