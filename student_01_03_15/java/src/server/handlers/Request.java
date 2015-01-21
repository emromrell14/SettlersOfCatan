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
	
	public Request(HttpExchange req)
	{
		method = req.getRequestMethod();
		requestURI = req.getRequestURI().toString();
		headers = req.getRequestHeaders();
		
		
	}
}
