package JSONmodels;

import models.Index;
import models.ResourceList;
import models.Trade;

import com.google.gson.Gson;

public class TradeOfferJSON 
{
	private int sender; //The index of the person offering the trade.
	private int receiver; //The index of the person the trade was offered to.
	private ResourceListJSON offer; //Positive numbers are resources being offered. Negative are resources being asked for.
	
	public TradeOfferJSON(Trade trade) 
	{
		this.sender = trade.sender().value();
		this.receiver = trade.receiver().value();
		this.offer = new ResourceListJSON(trade.offer());
	}

	/**
	 * Creates a TradeOffer object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New TradeOffer object
	 */
	public static TradeOfferJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, TradeOfferJSON.class);
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
	public ResourceListJSON getOffer() {
		return offer;
	}
	
	public Trade getModel()
	{
		Trade t = null;
		try 
		{
			t = new Trade(new Index(sender), new Index(receiver), new ResourceList(offer.getBrick(), offer.getOre(), offer.getSheep(), offer.getWheat(), offer.getWood()));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}
}
