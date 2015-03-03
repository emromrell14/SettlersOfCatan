package facade;

import models.Game;
import JSONmodels.ClientModelJSON;
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
	public Game getGameModel(int version)
	{
		System.out.println("getGameModel");
		String response;
		String urlPath = "/game/model?version="+version;
		
		response = mProxy.get(urlPath);
		////System.out.println("Game Manager JSON: " + response);
		return (response.equals("\"true\"")) ? null : jsonToGame(response);
		
//		Hack for something paul did
//		Game g = jsonToGame(response);
//		if (g == null)
//		{
//			g = new Game();
//		}
//		return g;
	}
	
	/**
	 * Resets current game to the original state of initial placement round
	 * @pre none
	 * @post game is reset to initial state
	 * @return new GameModel json String
	 */
	public Game resetGame()
	{
		String response;
		String body;
		
		body = "";
		
		response = mProxy.post("/game/reset", body);
		return jsonToGame(response);
	}
	
	/**
	 * Executes list of commands in the current game
	 * @pre must be valid game commands
	 * @post list of commands is executed
	 * @return json String client model identical to GameModel
	 */
	public Game executeCommandList()
	{
		String body = "";
		String response = mProxy.post("game/commands", body);
		return jsonToGame(response);
	}

	/**
	 * Requests list of already executed commands
	 * @pre none
	 * @post A list of all commands is returned
	 * @return json String of list of executed commands in the current game
	 */
	public String getCommandList() 
	{
		return mProxy.get("/game/commands");
	}
	
	/**
	 * Adds an AI player to the game, only LARGEST_ARMY is supported at this time
	 * @pre must not have more than 3 total players already
	 * @post an AI player is added to the game
	 */
	public void addAIPlayer() 
	{
//		String response;
		String body;
		
		body = "{AIType:\"LARGEST_ARMY\"}";
		
		/*response = */mProxy.post("/game/addAI", body);
//		return response;
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
	
	private Game jsonToGame(String response)
	{
		Game game = null;
		if(!response.contains("Failed"))
		{
			ClientModelJSON model = ClientModelJSON.fromJSON(response);
			game = model.getGameObject();
		}
		return game;
	}
}
