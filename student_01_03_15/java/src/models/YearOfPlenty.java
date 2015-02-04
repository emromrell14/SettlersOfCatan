package models;

import shared.definitions.DevCardType;

//import facade.MasterManager;

public class YearOfPlenty extends DevCard
{

	public YearOfPlenty()
	{
		this.setType(DevCardType.YEAR_OF_PLENTY);
	}
	
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		
//		if(MasterManager.getInstance().canPlayYearOfPlenty())
//		{
//			MasterManager.getInstance().playYearOfPlenty();
//			this.setmPlayed(true);
//		}
		
	}

}
