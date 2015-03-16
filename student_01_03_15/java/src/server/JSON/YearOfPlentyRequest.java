package server.JSON;

import com.google.gson.Gson;

public class YearOfPlentyRequest {
	private String type;
	private int playerIndex;
	private String resource1;
	private String resource2;
	
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

	public String getResource1() {
		return resource1;
	}

	public void setResource1(String resource1) {
		this.resource1 = resource1;
	}

	public String getResource2() {
		return resource2;
	}

	public void setResource2(String resource2) {
		this.resource2 = resource2;
	}

	public YearOfPlentyRequest() {
		
	}

	public static YearOfPlentyRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, YearOfPlentyRequest.class);
	}
}
