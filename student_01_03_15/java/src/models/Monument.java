package models;

import shared.definitions.DevCardType;

//import facade.MasterManager;

public class Monument extends DevCard
{

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
