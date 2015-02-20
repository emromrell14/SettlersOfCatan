package proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import cookie.Cookie;

public class Proxy implements IProxy
{
	private HttpURLConnection mCon;
	private Cookie mCookie;
	
	public Proxy()
	{
		mCookie = new Cookie();
	}
	
	public int getPlayerID()
	{
		return mCookie.getPlayerID();
	}
	public String getPlayerName()
	{
		return mCookie.getPlayerName();
	}
	
	public synchronized String post(String requestPath, String json)
	{
		String url ="http://localhost:8081" + requestPath;
		URL obj;
		try 
		{
			obj = new URL(url);
			mCon = (HttpURLConnection) obj.openConnection();
			mCon.setDoOutput(true);
			mCon.setDoInput(true);
			mCon.setRequestProperty("Content-Type", "application/json");
			mCon.setRequestProperty("Accept", "application/json");
			mCon.setRequestProperty("Cookie", mCookie.getCookie());
			mCon.setRequestMethod("POST");
			
			OutputStreamWriter wr = new OutputStreamWriter(mCon.getOutputStream());
			wr.write(json);
			wr.flush();
			wr.close();
			
			int responseCode = mCon.getResponseCode();
			if(responseCode != 200)
			{
				System.out.println("responseCode:"+responseCode);
				return "Failed with a " + responseCode + " response from server.";
			}
			System.out.println("Proxy responseCode:"+responseCode + " " + requestPath);
			BufferedReader in = new BufferedReader(new InputStreamReader(mCon.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
			
			in.close();
//			System.out.println("PROXY POST RESPONSE BODY:"+response);
			if(requestPath.equalsIgnoreCase("/user/login"))
			{
				String cookieResponse = mCon.getHeaderField("Set-cookie");
				cookieResponse = cookieResponse.replace("catan.user=","");
				cookieResponse = cookieResponse.replace(";Path=/;", "");
				mCookie.setCatanUser(cookieResponse);
			}
			else if(requestPath.equalsIgnoreCase("/games/join"))
			{
				String cookieResponse = mCon.getHeaderField("Set-cookie");
				cookieResponse = cookieResponse.replace("catan.game=","");
				cookieResponse = cookieResponse.replace(";Path=/;", "");
				mCookie.setCatanGame(cookieResponse);
			}
			return response.toString();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return "Shouldn't get here1";
	}
	
	public synchronized String get(String requestPath)
	{
		String url ="http://localhost:8081" + requestPath;
		URL obj;
		try 
		{
			obj = new URL(url);
			mCon = (HttpURLConnection) obj.openConnection();
			mCon.setRequestProperty("Content-Type", "application/json");
			mCon.setRequestProperty("Accept", "application/json");
			mCon.setRequestProperty("Cookie", mCookie.getCookie());
			mCon.setRequestMethod("GET");
			
			int responseCode = mCon.getResponseCode();
			if(responseCode != 200)
			{
				return "Failed with a " + responseCode + " response from server.";
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(mCon.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return "Shouldn't get here2";
	}
}
