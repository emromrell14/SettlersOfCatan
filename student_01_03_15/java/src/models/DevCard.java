package models;

import shared.definitions.DevCardType;

public abstract class DevCard implements IDevCard
{
	private boolean mPlayed = false;
	private DevCardType mType;
	
	
	public boolean getmPlayed()
	{
		return mPlayed;
	}
	public void setmPlayed(boolean played)
	{
		mPlayed = played;
	}
	
	public DevCardType getmType()
	{
		return mType;
	}
}
