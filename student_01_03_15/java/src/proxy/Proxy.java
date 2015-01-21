package proxy;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import server.IServer;

public class Proxy implements IProxy
{
	private IServer server;
	private HttpURLConnection con;
	
	public Proxy(IServer server)
	{
		this.server = server;
	}
	
	public void post(String requestPath, String json)
	{
		String url ="http://"+server.getHost()+":"+server.getPortNumber() + requestPath;
		URL obj;
		try 
		{
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestMethod("POST");
			
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(json);
			wr.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void get()
	{
		
	}
}
