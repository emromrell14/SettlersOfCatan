package server.JSON;

import JSONmodels.EdgeLocationJSON;

import com.google.gson.Gson;

public class BuildRoadRequest {
	private String type;
	private int playerIndex;
	private EdgeLocationJSON roadLocation;
	private boolean free;
	
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

	public EdgeLocationJSON getRoadLocation() {
		return roadLocation;
	}

	public void setRoadLocation(EdgeLocationJSON roadLocation) {
		this.roadLocation = roadLocation;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public BuildRoadRequest() {
		
	}	

	public static BuildRoadRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, BuildRoadRequest.class);
	}
}
