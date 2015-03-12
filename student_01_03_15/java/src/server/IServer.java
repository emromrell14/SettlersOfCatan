package server;

import models.IGame;

public interface IServer 
{
	/**
	 * Get the server's port number.
	 * 
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
	 * @pre none
	 * @post create a new game and store it in the server
	 */
	void createGame();
	
	/**
	 * create a new user and store it in the server
	 */
	void registerUser();
}
