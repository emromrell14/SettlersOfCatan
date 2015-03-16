package server.JSON;

import com.google.gson.Gson;

public class SaveGamesRequest {
	private int id;
	private String name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public SaveGamesRequest() {
		
	}
	
	public static SaveGamesRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, SaveGamesRequest.class);
	}
}
