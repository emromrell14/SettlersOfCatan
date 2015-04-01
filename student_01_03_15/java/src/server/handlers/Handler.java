package server.handlers;

import java.io.IOException;

import server.IServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class Handler implements HttpHandler
{
	protected IServer server;
	/**
	 * @pre none
	 * @post Handles the HTTP request sent from the Proxy.
	 * @post Send a response back via the HttpExchange
	 * 
	 * @param exchange	The request sent from the Proxy.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException 
	{
		Request req;
		Response res;
		
		req = new Request(exchange);
//		System.out.println("requestURI:"+req.getRequestURI());
		res = processRequest(req);
		res.sendResponse(exchange);
	}
	
	/**
	 * @pre Request != null
	 * @post Processes the given Request.
	 * @post Creates an appropriate Response from the given Request.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	public abstract Response processRequest(Request req);
}
