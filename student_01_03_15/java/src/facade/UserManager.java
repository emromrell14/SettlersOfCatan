package facade;

import proxy.IProxy;

public class UserManager 
{
	protected IProxy mProxy;
	/**
	 * Creates UserManager object
	 * 
	 * @return a new UserManager object
	 */
	public UserManager()
	{
	}
	
	public void setProxy(IProxy proxy)
	{
		mProxy = proxy;
	}
	
	/**
	 * Validates the player's credentials, and logs them into the server. (i.e., sets their catan.user HTTP cookie)
	 * @pre Username and password != null, canLogin returns true
	 * @post Cookie will be set.
	 * @return a String of JSON
	 */
	public boolean login(String username, String password)
	{
		String response;
		String body;
		
		body = "{username:\"" + username + "\",password:\"" + password + "\"}";
		response = mProxy.post("/user/login", body);
		
		return response.equalsIgnoreCase("Success");
	}

	/**
	 * Creates a new player account, and logs them into the server. (i.e., sets up their catan.user HTTP cookie)
	 * @pre Username and password not null. 
	 * @post Player is logged in and cookie set.
	 * @return a String of JSON
	 */
	public boolean register(String username, String password) 
	{
		String response;
		String body;
		
		body = "{username:\"" + username + "\",password:\"" + password + "\"}";
		response = mProxy.post("/user/register", body);
		
		return response.equalsIgnoreCase("Success");
	}
}
