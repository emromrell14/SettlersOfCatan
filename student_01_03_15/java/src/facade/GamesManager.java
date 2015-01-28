package facade;

public class GamesManager extends MasterManager
{
	/**
	 * Creates a GamesManager object
	 * 
	 * @return a new GamesManager object
	 */
	public GamesManager()
	{
		
	}
	
	/**
	 * gets a list of all games in the progress
	 * 
	 * @return a String of JSON
	 */
	public String getGameList()
	{
		return null;
	}

	/**
	 * Creates a new game
	 * 
	 * @return a String of JSON
	 */
	public String createGame()
	{
		return null;
	}

	/**
	 * Adds (or re-adds) the player to the specified game, and sets their catan.game HTTP cookie
	 * 
	 * @return a String of JSON
	 */
	public String joinGame()
	{
		return null;
	}

	/**
	 * Saves the current state of the specified game to a file
	 * 
	 * @return a String of JSON
	 */
	public String saveGame()
	{
		return null;
	}

	/**
	 * Loads a previously saved game file to restore the state of the game
	 * 
	 * @return a String of JSON
	 */
	public String loadGame() 
	{
		return null;
	}
}
