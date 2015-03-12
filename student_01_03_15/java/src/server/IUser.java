package server;

public interface IUser 
{
	/**
	 * @pre none
	 * @post return the user's username
	 * @return the user's username
	 */
	String getUsername();
	/**
	 * @pre none
	 * @post return the user's password
	 * @return the user's password
	 */
	String getPassword();
	/**
	 * @pre username != null and consists of valid characters with an appropriate length
	 * @post set the user's username
	 * @param username
	 */
	void setUsername(String username);
	/**
	 * @pre password != null and consists of valid characters with an appropriate length
	 * @post set the user's password
	 * @param password
	 */
	void setPassword(String password);
	/**
	 * @pre none
	 * @post return the user's id
	 * @return the user's id
	 */
	int getID();
}
