package server.JSON;

import JSONmodels.VertexLocationJSON;

import com.google.gson.Gson;

public class BuildSettlementRequest {
	private String type;
	private int playerIndex;
	private VertexLocationJSON vertexLocation;
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

	public VertexLocationJSON getVertexLocation() {
		return vertexLocation;
	}

	public void setVertexLocation(VertexLocationJSON vertexLocation) {
		this.vertexLocation = vertexLocation;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public BuildSettlementRequest() {
		
	}
	
	public static BuildSettlementRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, BuildSettlementRequest.class);
	}
}
