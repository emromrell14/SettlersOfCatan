package proxy;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import cookie.Cookie;
import server.IServer;

public class Proxy implements IProxy
{
	private IServer mServer;
	private HttpURLConnection mCon;
	
	public Proxy(IServer server)
	{
		this.mServer = server;
	}
	
	public String post(String requestPath, String json, Cookie cookie)
	{
		String url ="http://"+mServer.getHost()+":"+mServer.getPortNumber() + requestPath;
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
	
	public String get(Cookie cookie)
	{
		return null;
	}
}
