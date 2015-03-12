package models;

import shared.definitions.DevCardType;

public class Soldier extends DevCard
{

	
	/**
	 * Default constructor. Sets the card's type to Soldier
	 * 
	 * @return a new Soldier object
	 */
	public Soldier()
	{
		this.setType(DevCardType.SOLDIER);
	}
	
	
	
	@Override
	public void execute() 
	{
		// TODO Auto-generated method stub
		
	}

}
