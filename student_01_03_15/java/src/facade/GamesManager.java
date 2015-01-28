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
	 * @pre none
	 * @return a String of JSON
	 */
	public String getGameList()
	{
		return null;
	}

	/**
	 * Creates a new game
	 * @pre none
	 * @return a String of JSON
	 */
	public String createGame()
	{
		return null;
	}

	/**
	 * Adds (or re-adds) the player to the specified game, and sets their catan.game HTTP cookie
	 * @pre There must not be more than 3 players already in the specified game
	 * @post Player is added to the game
	 * @return a String of JSON
	 */
	public String joinGame()
	{
		return null;
	}

	/**
	 * Saves the current state of the specified game to a file
	 * @pre none
	 * @return a String of JSON
	 */
	public String saveGame()
	{
		return null;
	}

	/**
	 * Loads a previously saved game file to restore the state of the game
	 * pre saved game file must be in parsable JSON format
	 * @return a String of JSON
	 */
	public String loadGame() 
	{
		return null;
	}
}
