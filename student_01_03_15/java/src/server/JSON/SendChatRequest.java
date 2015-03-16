package server.JSON;

import com.google.gson.Gson;

public class SendChatRequest {
	private String type;
	private int playerIndex;
	private String content;
	
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SendChatRequest() {
		
	}	

	public static SendChatRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, SendChatRequest.class);
	}
}
