package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class IHandler implements HttpHandler
{

	@Override
	public void handle(HttpExchange exchange) throws IOException 
	{
		Request req;
		Response res;
		
		req = new Request(exchange);
		res = processRequest(req);
		res.sendResponse(exchange);
	}
	
	public abstract Response processRequest(Request req);
}
