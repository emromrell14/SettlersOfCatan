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
		
		//Parse the cookies into objects.
		
		
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
