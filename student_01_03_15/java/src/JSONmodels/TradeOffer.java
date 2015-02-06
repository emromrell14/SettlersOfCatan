package JSONmodels;

import models.Index;
import models.Trade;

import com.google.gson.Gson;

public class TradeOffer 
{
	private int sender; //The index of the person offering the trade.
	private int receiver; //The index of the person the trade was offered to.
	private ResourceList offer; //Positive numbers are resources being offered. Negative are resources being asked for.
	
	/**
	 * Creates a TradeOffer object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New TradeOffer object
	 */
	public static TradeOffer fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, TradeOffer.class);
	}
	
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the sender
	 */
	public int getSender() {
		return sender;
	}

	/**
	 * @return the receiver
	 */
	public int getReceiver() {
		return receiver;
	}

	/**
	 * @return the offer
	 */
	public ResourceList getOffer() {
		return offer;
	}
	
	public Trade getModel()
	{
		Trade t = null;
		try 
		{
			t = new Trade(new Index(sender), new Index(receiver), offer);
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}
}
