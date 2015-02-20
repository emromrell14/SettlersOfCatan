package JSONmodels;

import com.google.gson.Gson;

public class GamesInfoJSON 
{
	private GameInfoJSON[] games;
	
	public GamesInfoJSON()
	{
		
	}
	
	public static GamesInfoJSON[] fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, GamesInfoJSON[].class);
	}

	public GameInfoJSON[] getGames() {
		return games;
	}

	public void setGames(GameInfoJSON[] games) {
		this.games = games;
	}
	
}
