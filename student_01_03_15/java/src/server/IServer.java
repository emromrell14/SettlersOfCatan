package server;

import java.util.List;
import java.util.Map;
import models.IGame;

public interface IServer 
{
	/**
	 * Get the server's port number.
	 * @return the port number
	 */
	int getPortNumber();
	/**
	 * Get the host name.
	 * 
	 * @return the host name
	 */
	String getHost();
	
	/**
	 * Runs the server
	 * @pre none
	 * @post Runs the server on a specific port.
	 */
	void run();
	
	/**
	 * @pre the game id exists
	 * @post return the model that corresponds to the given id
	 * @param id
	 * @return the game model for the given id
	 */
	IGame getGame(int id);
	
	/**
	 * @pre the user id exists
	 * @post return the user that corresponds to the given id
	 * @param id
	 * @return
	 */
	IUser getUser(int id);
	
	/**
	 * Creates a new game and adds it to the list of games on the server
	 * @pre none
	 * @post create a new game and store it in the server
	 */
	void createGame();
	
	/**
	 * create a new user and store it in the server
	 * @pre none (see user class for rules about characters)
	 * @post New user will be registered with this server
	 * @param user new user
	 */
	void registerUser(User user);
	public Map<Integer, IGame> getGames();
	public void setGames(Map<Integer, IGame> games);
	public Map<Integer, IUser> getUsers();
	public void setUsers(Map<Integer, IUser> users);
	public Map<Integer, List<String>> getCommands();
	public void setCommands(Map<Integer, List<String>> commands);
	public void resetCommands(int gameID);
	public void addCommand(int gameID, String command);
	void createGame(String name, int id, boolean randomTiles, boolean randomNumbers, boolean randomPorts);
	public IUser getCurrentUser(String username);
	String getGameModelJSON(int version, int gameID);
	void updateVersion(int gameID);
	
	void loadGame(IGame game);
	void checkForWinner(int gameID);
}
