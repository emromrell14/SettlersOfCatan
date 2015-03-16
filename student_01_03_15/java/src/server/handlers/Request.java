package server.handlers;

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
