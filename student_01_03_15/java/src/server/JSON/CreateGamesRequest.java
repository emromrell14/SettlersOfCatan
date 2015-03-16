package server.JSON;

import com.google.gson.Gson;

public class CreateGamesRequest {
	private boolean randomTiles;
	private boolean randomNumbers;
	private boolean randomPorts;
	private String name;
	
	public boolean isRandomTiles() {
		return randomTiles;
	}
	public void setRandomTiles(boolean randomTiles) {
		this.randomTiles = randomTiles;
	}
	public boolean isRandomNumbers() {
		return randomNumbers;
	}
	public void setRandomNumbers(boolean randomNumbers) {
		this.randomNumbers = randomNumbers;
	}
	public boolean isRandomPorts() {
		return randomPorts;
	}
	public void setRandomPorts(boolean randomPorts) {
		this.randomPorts = randomPorts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public CreateGamesRequest() {
		
	}
	
	public static CreateGamesRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, CreateGamesRequest.class);
	}
}
