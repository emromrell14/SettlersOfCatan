package models;

import shared.definitions.DevCardType;

public class Soldier extends DevCard
{

	
	/**
	 * Default constructor. Sets the card's type to Soldier
	 * @pre must be in playing state
	 * @return a new Soldier object
	 * @post see return
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
