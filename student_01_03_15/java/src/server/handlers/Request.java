package server.handlers;

import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import cookie.Cookie;

public class Request
{
	private String method;
	private String requestURI;
	private Headers headers;
	private Cookie cookie;
	private String body;

	public Request(HttpExchange req)
	{
		method = req.getRequestMethod();
		requestURI = req.getRequestURI().toString();
		headers = req.getRequestHeaders();
		cookie = new Cookie();
		
		//Parse the cookies into objects.
		
		parseCookieHeader();
		
		//Read in the request body...
		
		final char[] buffer = new char[1024];
		final StringBuilder out = new StringBuilder();
		
		try 
		{
			final InputStreamReader in = new InputStreamReader(req.getRequestBody(), "UTF-8");
			try 
			{
				while (true) 
				{
					int rsz = in.read(buffer, 0, buffer.length);
					if (rsz < 0)
					{
						break;
					}
					out.append(buffer, 0, rsz);
				}
			}
			finally 
			{
				in.close();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		//Successfully read in the body.
		this.body = out.toString();
	}
	
	private void parseCookieHeader()
	{
		for(String key : headers.keySet())
		{
			if(key.equals("Cookie"))
			{
//				catan.user=%7Busername%3A%22seamane%22%2Cpassword%3A%22seamane%22%2C%22playerID%22%3A0%7D; catan.game=2
				cookie.parseServerCookie(headers.getFirst(key));
			}
		}
	}
	
	/**
	 * @return the method
	 */
	public String getMethod() 
	{
		return method;
	}

	/**
	 * @return the requestURI
	 */
	public String getRequestURI() 
	{
		return requestURI;
	}

	/**
	 * @return the headers
	 */
	public Headers getHeaders() 
	{
		return headers;
	}

	/**
	 * @return the cookie
	 */
	public Cookie getCookie() 
	{
		return cookie;
	}

	/**
	 * @return the body
	 */
	public String getBody() 
	{
		return body;
	}
}
