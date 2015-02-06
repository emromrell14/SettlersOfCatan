package models;

import JSONmodels.ResourceList;

public class Trade 
{
	private Index mSender; //The index of the person offering the trade.
	private Index mReceiver; //The index of the person the trade was offered to.
	private ResourceList mOffer; //Positive numbers are resources being offered. Negative are resources being asked for.
	
	public Trade(Index sender, Index receiver, ResourceList offer)
	{
		mSender = sender;
		mReceiver = receiver;
		mOffer = offer;
	}

	public Index sender() 
	{
		return mSender;
	}

	public Index receiver() 
	{
		return mReceiver;
	}

	public ResourceList offer() 
	{
		return mOffer;
	}
}
