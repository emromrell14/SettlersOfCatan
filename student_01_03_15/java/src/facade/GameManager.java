package facade;

import proxy.IProxy;

public class GameManager
{
	private IProxy mProxy;
	/**
	 * Creates a GameManager object
	 * @return GameManager object
	 */
	public GameManager()
	{
	}
	
	public void setProxy(IProxy proxy)
	{
		mProxy = proxy;
	}
	
	/**
	 * Requests json String with game model info
	 * @pre none
	 * @return json String with game model in it
	 */
	public String getGameModel(int version)
	{
		String response;
		
		response = mProxy.get("/game/model");
		return response;
	}
	
	/**
	 * Resets current game to the original state of initial placement round
	 * @pre none
	 * @post game is reset to initial state
	 * @return new GameModel json String
	 */
	public String resetGame()
	{
		String response;
		String body;
		
		body = "";
		
		response = mProxy.post("/game/reset", body);
		return response;
	}
	
	/**
	 * Executes list of commands in the current game
	 * @pre must be valid game commands
	 * @post list of commands is executed
	 * @return json String client model identical to GameModel
	 */
	public String executeCommandList()
	{
		return null;
	}

	/**
	 * Requests list of already executed commands
	 * @pre none
	 * @post A list of all commands is returned
	 * @return json String of list of executed commands in the current game
	 */
	public String getCommandList() 
	{
		return null;
	}
	
	/**
	 * Adds an AI player to the game, only LARGEST_ARMY is supported at this time
	 * @pre must not have more than 3 total players already
	 * @post an AI player is added to the game
	 */
	public String addAIPlayer() 
	{
		String response;
		String body;
		
		body = "{AIType:\"LARGEST_ARMY\"}";
		
		response = mProxy.post("/game/addAI", body);
		return response;
	}

	/**
	 * Requests list of AI Players
	 * @pre none
	 * @post a list of the current AI players is returned
	 * @return json String of list of AI Players
	 */
	public String getAIPlayers()
	{
		String response;
		
		response = mProxy.get("/game/listAI");
		return response;
	}
}
