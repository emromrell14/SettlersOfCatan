package facade;

import proxy.IProxy;

public class GamesManager
{
	protected IProxy mProxy;
	
	/**
	 * Creates a GamesManager object
	 * 
	 * @return a new GamesManager object
	 */
	public GamesManager()
	{
	}
	
	public void setProxy(IProxy proxy)
	{
		mProxy = proxy;
	}
	
	/**
	 * gets a list of all games in the progress
	 * @pre none
	 * @return a String of JSON
	 */
	public String getGameList()
	{
		String response;
		response = mProxy.get("/games/list");
		
		return response;
	}

	/**
	 * Creates a new game
	 * @pre none
	 * @return a String of JSON
	 */
	public String createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name)
	{
		String response;
		String body;
		
		body = "{randomTiles:" + randomTiles + ",randomNumbers:" + randomNumbers +
				",randomPorts:" + randomPorts + ",name:\"" + name + "\"}";
		
		response = mProxy.post("/games/create", body);
		return response;
	}

	/**
	 * Adds (or re-adds) the player to the specified game, and sets their catan.game HTTP cookie
	 * @pre There must not be more than 3 players already in the specified game
	 * @post Player is added to the game
	 * @return a String of JSON
	 */
	public String joinGame(int id, String color)
	{	
		String response;
		String body;
		
		body = "{id:" + id + ",color:\"" + color + "\"}";
		response = mProxy.post("/games/join", body);
		return response;
	}

	/**
	 * Saves the current state of the specified game to a file
	 * @pre none
	 * @return a String of JSON
	 */
	public String saveGame(int gameId, String fileName)
	{
		String response;
		String body;
		
		body = "{id:" + gameId + ",name:\"" + fileName + "\"}";
		
		response = mProxy.post("/games/save", body);
		return response;
	}

	/**
	 * Loads a previously saved game file to restore the state of the game
	 * pre saved game file must be in parsable JSON format
	 * @return a String of JSON
	 */
	public String loadGame(String fileName) 
	{
		String response;
		String body;
		
		body = "{name:\"" + fileName + "\"}";
		
		response = mProxy.post("/games/load", body);
		return response;
	}
}
