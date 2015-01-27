package server.handlers;

public class CommandsHandler extends Handler
{
	@Override
	public Response processRequest(Request req) 
	{
		if(req.getMethod().equalsIgnoreCase("POST"))
		{
			return processPost(req);
		}
		return processGet(req);
	}
	
	/**
	 * @pre Request != null
	 * @post Executes the specified command list in the current game.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return	The Response for the given Request.
	 */
	private Response processPost(Request req)
	{
		return null;
		
	}
	
	/**
	 * @pre Request != null
	 * @post Returns a list of commands that have been executed in the current game.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return	The Response for the given Request.
	 */
	private Response processGet(Request req)
	{
		return null;
		
	}
}
