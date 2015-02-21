package JSONmodels;

import com.google.gson.Gson;

import shared.definitions.CatanColor;

public class PlayerInfoJSON
{
	private int id;
//	private int playerIndex;
	private String name;
	private String color;
	
	public PlayerInfoJSON()
	{
		
	}
	
	public static PlayerInfoJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, PlayerInfoJSON.class);
	}
	public static PlayerInfoJSON[] getPlayersArrayFromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, PlayerInfoJSON[].class);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getPlayerIndex() {
//		return playerIndex;
//	}
//
//	public void setPlayerIndex(int playerIndex) {
//		this.playerIndex = playerIndex;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
}
