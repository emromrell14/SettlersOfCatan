package server.handlers;

import com.sun.net.httpserver.HttpExchange;

public class Response 
{
	private String body;
	private int statusCode;
	
	public Response(int statusCode, String body)
	{
		this.statusCode = statusCode;
		this.body = body;
	}
	
	/**
	 * @pre exchange != null
	 * @post This function sends a response back to the Proxy.
	 * 
	 * @param exchange
	 */
	public void sendResponse(HttpExchange exchange)
	{
		
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
}
