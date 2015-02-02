package proxy;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import cookie.Cookie;

public class Proxy implements IProxy
{
	private HttpURLConnection mCon;
	
	public Proxy()
	{
	}
	
	public String post(String requestPath, String json, Cookie cookie)
	{
		String url ="http://localhost:8080" + requestPath;
		URL obj;
		try 
		{
			obj = new URL(url);
			mCon = (HttpURLConnection) obj.openConnection();
			mCon.setDoOutput(true);
			mCon.setDoInput(true);
			mCon.setRequestProperty("Content-Type", "application/json");
			mCon.setRequestProperty("Accept", "application/json");
			mCon.setRequestMethod("POST");
			
			OutputStreamWriter wr = new OutputStreamWriter(mCon.getOutputStream());
			wr.write(json);
			wr.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String get(String requestPath, Cookie cookie)
	{
		return null;
	}
}
