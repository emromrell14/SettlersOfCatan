package server.JSON;

import JSONmodels.ResourceListJSON;

import com.google.gson.Gson;

public class OfferTradeRequest {
	private String type;
	private int playerIndex;
	private ResourceListJSON offer;
	private int receiver;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public ResourceListJSON getOffer() {
		return offer;
	}

	public void setOffer(ResourceListJSON offer) {
		this.offer = offer;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public OfferTradeRequest() {
		
	}	
	
	public static OfferTradeRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, OfferTradeRequest.class);
	}
}
