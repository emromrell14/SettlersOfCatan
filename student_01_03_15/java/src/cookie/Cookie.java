package cookie;

import java.net.URLDecoder;
import java.util.Map;

import com.google.gson.Gson;

@SuppressWarnings("rawtypes")
public class Cookie 
{
	private String mCatanUser;
	private String mCatanGame;
	private Map mUserMap;
	private Map mGameMap;
	
	public Cookie()
	{
		mUserMap = null;
		setGameMap(null);
		mCatanUser = "";
		mCatanGame = "";
	}
	
	public int getPlayerID()
	{
		return mUserMap == null?-1:(int) mUserMap.get("playerID");
	}
	public String getPlayerName()
	{
		return mUserMap == null ? null : (String)mUserMap.get("name");
	}

	/**
	 * @return the catanUser
	 */
	public String getCatanUser() 
	{
		return mCatanUser;
	}

	/**
	 * @param catanUser the catanUser to set
	 */
	@SuppressWarnings("deprecation")
	public void setCatanUser(String catanUser)
	{
		this.mCatanUser = catanUser;
		String json = URLDecoder.decode(catanUser);
		mUserMap = new Gson().fromJson(json, Map.class);
	}

	/**
	 * @return the catanGame
	 */
	public String getCatanGame() 
	{
		return mCatanGame;
	}

	/**
	 * @param catanGame the catanGame to set
	 */
	@SuppressWarnings("deprecation")
	public void setCatanGame(String catanGame) 
	{
		this.mCatanGame = catanGame;
		String json = URLDecoder.decode(catanGame);
		setGameMap(new Gson().fromJson(json, Map.class));
	}
	
	public Map gameMap() {
		return mGameMap;
	}

	public void setGameMap(Map gameMap) {
		this.mGameMap = gameMap;
	}

	public String getCookie()
	{
		String cookie = "";
		if(!mCatanUser.equalsIgnoreCase(""))
		{
			cookie = "catan.user="+mCatanUser;
			if(!mCatanGame.equalsIgnoreCase(""))
			{
				cookie += "; catan.game="+mCatanGame;
			}
		}
		return cookie;
	}
}
