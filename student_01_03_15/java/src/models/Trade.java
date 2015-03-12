package models;

public class Trade 
{
	/**The index of the person offering the trade */
	private Index mSender;
	/**The index of the person the trade was offered to */
	private Index mReceiver;
	/**Positive numbers are resources being offered. Negative are resources being asked for. */
	private ResourceList mOffer;
	
	/**
	 * 
	 * @param sender    of the trade
	 * @param receiver   of the trade
	 * @param offer   resource list representing items offered and requested
	 * 
	 * @return a new Trade object
	 */
	
	public Trade(Index sender, Index receiver, ResourceList offer)
	{
		mSender = sender;
		mReceiver = receiver;
		mOffer = offer;
	}

	/**
	 * Gets the sender's player Index
	 * @return (player's) Index of the person sending the trade
	 */
	public Index sender() 
	{
		return mSender;
	}

	/**
	 * Gets the receiver's player Index
	 * @return (player's) Index of the person being offered the trade
	 */
	public Index receiver() 
	{
		return mReceiver;
	}

	/**
	 * Gets the RecourceList of the trade
	 * @return ResourceList of the trade being offered. Negative values represent resources asked for, positive: offered
	 */
	public ResourceList offer() 
	{
		return mOffer;
	}
}
