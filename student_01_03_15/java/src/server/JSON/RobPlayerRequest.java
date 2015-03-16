package server.JSON;

import JSONmodels.HexLocationJSON;

import com.google.gson.Gson;

public class RobPlayerRequest {
	private String type;
	private int playerIndex;
	private int victimIndex;
	private HexLocationJSON location;
	
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

	public int getVictimIndex() {
		return victimIndex;
	}

	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}

	public HexLocationJSON getLocation() {
		return location;
	}

	public void setLocation(HexLocationJSON location) {
		this.location = location;
	}

	public RobPlayerRequest() {
		
	}	

	public static RobPlayerRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, RobPlayerRequest.class);
	}
}
