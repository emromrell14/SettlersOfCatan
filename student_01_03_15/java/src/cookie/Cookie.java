package cookie;

public class Cookie 
{
	private String catanUser;
	private String catanGame;
	
	public Cookie()
	{
		catanUser = "";
		catanGame = "";
	}

	/**
	 * @return the catanUser
	 */
	public String getCatanUser() 
	{
		return catanUser;
	}

	/**
	 * @param catanUser the catanUser to set
	 */
	public void setCatanUser(String catanUser)
	{
		this.catanUser = catanUser;
	}

	/**
	 * @return the catanGame
	 */
	public String getCatanGame() 
	{
		return catanGame;
	}

	/**
	 * @param catanGame the catanGame to set
	 */
	public void setCatanGame(String catanGame) 
	{
		this.catanGame = catanGame;
	}
	
	public String getCookie()
	{
		String cookie = "";
		if(!catanUser.equalsIgnoreCase(""))
		{
			cookie = "catan.user="+catanUser;
			if(!catanGame.equalsIgnoreCase(""))
			{
				cookie += "; catan.game="+catanGame;
			}
		}
		return cookie;
	}
}
