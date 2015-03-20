package server.JSON;

import shared.locations.EdgeLocation;
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

	public EdgeLocation getSpot1() {
		return new EdgeLocation(spot1.getHexLocation(),spot1.getDirection());
	}

	public void setSpot1(EdgeLocationJSON spot1) {
		this.spot1 = spot1;
	}

	public EdgeLocation getSpot2() {
		return new EdgeLocation(spot2.getHexLocation(),spot2.getDirection());
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
