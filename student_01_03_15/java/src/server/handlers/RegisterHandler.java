package server.handlers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import server.IServer;
import server.User;
import server.JSON.RegisterRequest;

public class RegisterHandler extends Handler
{
	public RegisterHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Creates a new player account.
	 * @post Logs player in to the server.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	
	@SuppressWarnings("rawtypes")
	@Override
	public Response processRequest(Request req) 
	{
		Response resp = new Response();
		String urlEncoded;
		String body = req.getBody();
		RegisterRequest rr = RegisterRequest.fromJSON(body);
		String registerUsername = rr.getUsername();
		String registerPassword = rr.getPassword();
		int largestPlayerID = -1;
		
		//Check the length of the username
		if(registerUsername.length() < 3 || registerUsername.length() > 7 || !registerUsername.matches("[a-zA-Z0-9_]+")) 
		{
			resp.setStatusCode(400);
			resp.setBody("Failed to register - invalid username");
			return resp;
		}
		
		//Check the length of the password
		if(registerPassword.length() < 5 || !registerPassword.matches("[a-zA-Z0-0_]+"))
		{
			resp.setStatusCode(400);
			resp.setBody("Failed to register - invalid password");
			return resp;
		}
		
		//Check if this username already exists
		Iterator it = server.getUsers().entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			largestPlayerID = (int) pair.getKey();
			
			if(registerUsername.equals(((User) pair.getValue()).getUsername()))
			{
				// Set fail response
				resp.setStatusCode(400);
				resp.setBody("Failed to register - duplicate username.");
				return resp;
			}
		}
		
		User u = new User(largestPlayerID+1);
		u.setPassword(registerPassword);
		u.setUsername(registerUsername);
		server.registerUser(u);
		
		String jsonText = toString(registerUsername, registerPassword, u.getID());
		try 
		{
			urlEncoded = URLEncoder.encode(jsonText, "UTF-8");
			resp.setCookie("catan.user", urlEncoded);
			resp.setStatusCode(200);
			resp.setBody("Success");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		
		return resp;
	}

	public String toString(String username, String password, int playerId)
	{
		String body;
		body = "{username:\"" + username + "\",password:\"" + password + "\",\"playerID\":" + playerId + "}";
		
		return body;
	}
}
