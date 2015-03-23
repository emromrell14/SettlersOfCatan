package server.handlers;

import server.IServer;

public class ModelHandler  extends Handler
{
	public ModelHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Returns the current state of the game in JSON format.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	@Override
	public Response processRequest(Request req)
	{
		Response res = new Response();
		int gameID = req.getCookie().getGameID();
		
		if(gameID == -1)
		{
			res.setBody("Failed - there is no game cookie");
			res.setStatusCode(400);
			return res;
		}
		
		int version = parseVersion(req.getRequestURI());
		res.setBody(server.getGameModelJSON(version,gameID));
		
		res.setStatusCode(200);
		return res;
	}

	private int parseVersion(String requestURI) 
	{
		String[] parts = requestURI.split("\\?");
		String version = parts[1].replace("version=", "");
		version = version.trim();
		return Integer.parseInt(version);
	}

}
