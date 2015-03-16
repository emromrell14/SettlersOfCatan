package server.JSON;

import com.google.gson.Gson;

public class RollNumberRequest {
	private String type;
	private int playerIndex;
	private int number;
	
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public RollNumberRequest() {
		
	}	

	public static RollNumberRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, RollNumberRequest.class);
	}
}
