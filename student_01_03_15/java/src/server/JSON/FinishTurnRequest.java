package server.JSON;

import com.google.gson.Gson;

public class FinishTurnRequest {
	private String type;
	private int playerIndex;
	
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

	public FinishTurnRequest() {
		
	}	
	
	public static FinishTurnRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, FinishTurnRequest.class);
	}
}
