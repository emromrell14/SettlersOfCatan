package models;

import shared.definitions.DevCardType;

public abstract class DevCard implements IDevCard
{
	private boolean mPlayed;
	private DevCardType mType;
	
	
	public boolean getmPlayed()
	{
		return mPlayed;
	}
	
	public DevCardType getmType()
	{
		return mType;
	}
}
