package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class Handler implements HttpHandler
{
	/**
	 * Handles the HTTP request sent from the Proxy.
	 * 
	 * @param exchange	The request sent from the Proxy.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException 
	{
		Request req;
		Response res;
		
		req = new Request(exchange);
		res = processRequest(req);
		res.sendResponse(exchange);
	}
	
	/**
	 * Processes the given Request.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	public abstract Response processRequest(Request req);
}
