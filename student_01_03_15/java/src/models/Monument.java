package models;

import shared.definitions.DevCardType;

//import facade.MasterManager;

public class Monument extends DevCard
{

	/**
	 * Default constructor. Sets the card's type to Monument and declares it as an old card
	 */
	public Monument() 
	{
		this.setType(DevCardType.MONUMENT);
		this.setNew(false);
	}
	
	
	
	@Override
	public void execute()
	{
		// TODO Auto-generated method stub
//		if (MasterManager.getInstance().canPlayMonument()) 
//		{
//			MasterManager.getInstance().playMonument();
//			this.setmPlayed(true);
//		}
		
		
		
	}

}
