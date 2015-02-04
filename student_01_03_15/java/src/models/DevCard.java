package models;

import shared.definitions.DevCardType;

public abstract class DevCard implements IDevCard
{
	private boolean mPlayed = false;
	private DevCardType mType;
	
	
	public boolean hasBeenPlayed()
	{
		return mPlayed;
	}
	public void setPlayed(boolean played)
	{
		mPlayed = played;
	}
	
	public void setDevCardType(DevCardType type) 
	{
		mType = type;
	}
	public DevCardType getType()
	{
		return mType;
	}
}
