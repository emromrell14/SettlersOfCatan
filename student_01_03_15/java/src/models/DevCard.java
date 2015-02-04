package models;

import shared.definitions.DevCardType;

public abstract class DevCard implements IDevCard
{
	private boolean mPlayed = false;
	private DevCardType mType;
	private boolean mNew = true;
	
	
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
	
	/**
	 * Returns whether or not the card was drawn this turn
	 * 
	 * @return if the card is new
	 */
	public boolean isNew()
	{
		return mNew;
	}
	/**
	 * Sets the value of the "new" variable
	 * 
	 * @param isNew
	 */
	public void setNew(boolean isNew)
	{
		this.mNew = isNew;
	}
}
