package JSONmodels;

import models.Index;

public class TradeOffer 
{
	private Index mSender; //The index of the person offering the trade.
	private Index mReceiver; //The index of the person the trade was offered to.
	private ResourceList mOffer; //Positive numbers are resources being offered. Negative are resources being asked for.
	
	/**
	 * Creates a TradeOffer object from all the variables
	 * 
	 * @param sender
	 * @param receiver
	 * @param offer
	 * @return New TradeOffer object
	 */
	public TradeOffer(Index sender, Index receiver, ResourceList offer)
	{
		this.mSender = sender;
		this.mReceiver = receiver;
		this.mOffer = offer;
	}
	
	/**
	 * Creates a TradeOffer object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New TradeOffer object
	 */
	public TradeOffer(String JSON)
	{
		
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		return "";
	}
}
