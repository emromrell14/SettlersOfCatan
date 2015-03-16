package server.JSON;

import com.google.gson.Gson;

public class JoinGamesRequest {
	private int id;
	private String color;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public JoinGamesRequest() {
		
	}
	
	public static JoinGamesRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, JoinGamesRequest.class);
	}
}
