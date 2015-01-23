package facade;

public class GameManager 
{
	
	/**
	 * Creates a GameManager object
	 * @return GameManager object
	 */
	public GameManager()
	{
		
	}
	
	/**
	 * Requests json String with game model info
	 * 
	 * @return json String with game model in it
	 */
	public String getGameModel()
	{
		return null;
	}
	
	/**
	 * Resets current game to the original state of initial placement round
	 * 
	 * @return new GameModel json String
	 */
	public String resetGame()
	{
		return null;
	}
	
	/**
	 * Executes list of commands in the current game
	 * 
	 * @return json String client model identical to GameModel
	 */
	public String executeCommandList()
	{
		return null;
	}

	/**
	 * Requests list of already executed commands
	 * 
	 * @return json String of list of executed commands in the current game
	 */
	public String getCommandList() 
	{
		return null;
	}
	
	/**
	 * Adds an AI player to the game
	 *
	 */
	public void addAIPlayer() 
	{
		
	}

	/**
	 * Requests list of AI Players
	 * 
	 * @return json String of list of AI Players
	 */
	public String getAIPlayers()
	{
		return null;
	}
}
