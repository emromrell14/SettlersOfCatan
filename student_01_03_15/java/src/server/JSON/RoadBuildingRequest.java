package server.JSON;

import JSONmodels.EdgeLocationJSON;

import com.google.gson.Gson;

public class RoadBuildingRequest {
	private String type;
	private int playerIndex;
	private EdgeLocationJSON spot1;
	private EdgeLocationJSON spot2;
	
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

	public EdgeLocationJSON getSpot1() {
		return spot1;
	}

	public void setSpot1(EdgeLocationJSON spot1) {
		this.spot1 = spot1;
	}

	public EdgeLocationJSON getSpot2() {
		return spot2;
	}

	public void setSpot2(EdgeLocationJSON spot2) {
		this.spot2 = spot2;
	}

	public RoadBuildingRequest() {
		
	}	
	
	public static RoadBuildingRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, RoadBuildingRequest.class);
	}
}
