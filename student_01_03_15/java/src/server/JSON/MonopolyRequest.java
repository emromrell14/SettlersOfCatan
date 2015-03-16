package server.JSON;

import com.google.gson.Gson;

public class MonopolyRequest {
	private String type;
	private int playerIndex;
	private String resource;
	
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

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public MonopolyRequest() {
		
	}

	public static MonopolyRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, MonopolyRequest.class);
	}
}
