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
	private int mGameID;
	
	public Cookie()
	{
		mGameID = -1;
		mUserMap = null;
		mCatanUser = "";
		mCatanGame = "";
	}
	
	public int getPlayerID()
	{
		if (mUserMap == null)
		{
			return -1;
		}
		else
		{
			Object o = (mUserMap.get("playerID"));
			Double d = (Double)(o);
			int i = d.intValue();
			return i;
		}
	}
	public String getPlayerName()
	{
		return mUserMap == null ? null : (String)mUserMap.get("name");
	}
	
	public void setCatanGame(int gameID)
	{
		mCatanGame = String.valueOf(gameID);
//		mGameID = gameID;
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
	public void setCatanGame(String catanGame) 
	{
		this.mCatanGame = catanGame;
		this.mGameID = Integer.parseInt(catanGame);
	}
	
	public int getGameID()
	{
		return mGameID;
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
	
	public void parseServerCookie(String encodedCookie)
	{
		String[] parts = encodedCookie.split(";");
		String userCookie = parts[0].replace("catan.user=", "");
		setCatanUser(userCookie.trim());
		
		if(parts.length > 1)
		{
			String gameCookie = parts[1].replace("catan.game=", "");
			setCatanGame(gameCookie.trim());
		}
	}
}
