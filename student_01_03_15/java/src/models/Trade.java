package models;

import JSONmodels.ResourceList;

public class Trade 
{
	private Index mSender; //The index of the person offering the trade.
	private Index mReceiver; //The index of the person the trade was offered to.
	private ResourceList mOffer; //Positive numbers are resources being offered. Negative are resources being asked for.
	
	public Trade()
	{
		
	}

	public Index getmSender() 
	{
		return mSender;
	}

	public void setmSender(Index sender) 
	{
		this.mSender = sender;
	}

	public Index getmReceiver() 
	{
		return mReceiver;
	}

	public void setmReceiver(Index receiver) 
	{
		this.mReceiver = receiver;
	}

	public ResourceList getmOffer() 
	{
		return mOffer;
	}

	public void setmOffer(ResourceList offer)
	{
		this.mOffer = offer;
	}

}
