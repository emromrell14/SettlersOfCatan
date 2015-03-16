package server.JSON;

import JSONmodels.ResourceListJSON;

import com.google.gson.Gson;

public class DiscardRequest {
	private String type;
	private int playerIndex;
	private ResourceListJSON discardedCards;
	
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

	public ResourceListJSON getDiscardedCards() {
		return discardedCards;
	}

	public void setDiscardedCards(ResourceListJSON discardedCards) {
		this.discardedCards = discardedCards;
	}

	public DiscardRequest() {
		
	}	
	
	public static DiscardRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, DiscardRequest.class);
	}
}
