package server.handlers;

import server.IServer;
import server.JSON.ChangeLogLevelRequest;

public class LogHandler  extends Handler
{
	public LogHandler(IServer server) 
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Sets the server's log level (ALL, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, OFF).
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	@Override
	public Response processRequest(Request req) 
	{
		ChangeLogLevelRequest c = ChangeLogLevelRequest.fromJSON(req.getBody());
		String log = c.getLogLevel();
		
		if(log.equalsIgnoreCase("ALL") ||
			log.equalsIgnoreCase("SEVERE") ||
			log.equalsIgnoreCase("WARNING") ||
			log.equalsIgnoreCase("INFO") ||
			log.equalsIgnoreCase("CONFIG") ||
			log.equalsIgnoreCase("FINE") ||
			log.equalsIgnoreCase("FINER") ||
			log.equalsIgnoreCase("FINEST") ||
			log.equalsIgnoreCase("OFF"))
		{
			return new Response(200,"Success");
		}
		
		return new Response(400,"Failed");
	}

}
