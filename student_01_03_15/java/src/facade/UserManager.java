package facade;

public class UserManager extends MasterManager
{
	/**
	 * Creates UserManager object
	 * 
	 * @return a new UserManager object
	 */
	public UserManager()
	{
		
	}
	
	/**
	 * Validates the player's credentials, and logs them into the server. (i.e., sets their catan.user HTTP cookie)
	 * 
	 * @return a String of JSON
	 */
	public String login()
	{
		return null;
	}

	/**
	 * Creates a new player account, and logs them into the server. (i.e., sets up their catan.user HTTP cookie)
	 * 
	 * @return a String of JSON
	 */
	public String register() 
	{
		return null;
	}
}
