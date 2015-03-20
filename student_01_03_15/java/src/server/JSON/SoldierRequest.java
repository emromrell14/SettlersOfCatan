package server.JSON;

import shared.locations.HexLocation;
import JSONmodels.HexLocationJSON;

import com.google.gson.Gson;

public class SoldierRequest {
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

	public HexLocation getLocation() {
		return new HexLocation(location.getX(),location.getY());
	}

	public void setLocation(HexLocationJSON location) {
		this.location = location;
	}

	public SoldierRequest() {
		
	}	
	
	public static SoldierRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, SoldierRequest.class);
	}
}
