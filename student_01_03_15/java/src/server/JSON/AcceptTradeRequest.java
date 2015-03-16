package server.JSON;

import com.google.gson.Gson;

public class AcceptTradeRequest {
	private String type;
	private int playerIndex;
	private boolean willAccept;
	
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

	public boolean isWillAccept() {
		return willAccept;
	}

	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}

	public AcceptTradeRequest() {
		
	}	

	public static AcceptTradeRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, AcceptTradeRequest.class);
	}
}
