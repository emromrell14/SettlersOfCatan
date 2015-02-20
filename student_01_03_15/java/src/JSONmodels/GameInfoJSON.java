package JSONmodels;

import java.util.List;

import com.google.gson.Gson;

import client.data.PlayerInfo;

public class GameInfoJSON 
{
	private int id;
	private String title;
	private List<PlayerInfo> players;
	
	public GameInfoJSON()
	{
		
	}
	public static GameInfoJSON[] getGamesArrayFromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, GameInfoJSON[].class);
	}
	
	public static GameInfoJSON fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, GameInfoJSON.class);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<PlayerInfo> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerInfo> players) {
		this.players = players;
	}
	
}
