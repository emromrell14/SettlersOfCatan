package server.JSON;

import com.google.gson.Gson;

public class LoadGamesRequest {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LoadGamesRequest() {
		
	}
	
	public static LoadGamesRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, LoadGamesRequest.class);
	}
}
