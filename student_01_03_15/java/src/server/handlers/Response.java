package server.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class Response 
{
	private String body;
	private int statusCode;
	private String cookieKey;
	private String cookieValue;
	
	public Response(int statusCode, String body)
	{
		this.statusCode = statusCode;
		this.body = body;
		this.cookieKey = null;
		this.cookieValue = null;
	}
	public Response()
	{
		this.cookieKey = null;
		this.cookieValue = null;
	}
	
	/**
	 * @pre exchange != null
	 * @post This function sends a response back to the Proxy.
	 * 
	 * @param exchange
	 */
	public void sendResponse(HttpExchange exchange)
	{
		int contentLen = body.length();
		try
		{
			Headers headers = exchange.getResponseHeaders();
			
			if(this.cookieKey != null && this.cookieValue != null)
			{
				List<String> list = new ArrayList<String>();
				list.add(this.cookieKey + "=" + this.cookieValue + ";Path=/;");
				headers.put("Set-cookie", list);
			}
			
			exchange.sendResponseHeaders(this.statusCode, contentLen);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			OutputStream os = exchange.getResponseBody();
			Writer w = new PrintWriter(os);
			w.write(this.body);
			// Send it back
			w.flush();
			w.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		exchange.close();
	}
	
	public void setCookie (String key, String value)
	{
		this.cookieKey = key;
		this.cookieValue = value;
	}
	
	/**
	 * @return the body
	 */
	public String getBody() 
	{
		return body;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode()
	{
		return statusCode;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	public void setStatusCode(int statusCode) 
	{
		this.statusCode = statusCode;
	}
}
