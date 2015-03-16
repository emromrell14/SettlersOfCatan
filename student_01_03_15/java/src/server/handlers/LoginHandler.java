package server.handlers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import server.IServer;
import server.User;
import server.JSON.LoginRequest;

public class LoginHandler extends Handler
{
	public LoginHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Validates the player's credentials.
	 * @post Logs player in to the server.
	 * 
	 * @param req	The Request object from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	@Override
	public Response processRequest(Request req) 
	{
		Response resp = new Response();
		String urlEncoded;
		String body = req.getBody();
		LoginRequest loginRequest = LoginRequest.fromJSON(body);
		
		Iterator it = server.getUsers().entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			String loginUsername = loginRequest.getUsername();
			String loginPassword = loginRequest.getPassword();
			
			if(loginUsername.equals(((User) pair.getValue()).getUsername()) && 
					loginPassword.equals(((User) pair.getValue()).getPassword()))
			{
				String jsonText = toString(loginUsername, loginPassword, (int)pair.getKey());
				try 
				{
					urlEncoded = URLEncoder.encode(jsonText, "UTF-8");
					resp.setCookie("catan.user", urlEncoded);
					resp.setStatusCode(200);
					resp.setBody("Success");
					return resp;
				} 
				catch (UnsupportedEncodingException e) 
				{
					e.printStackTrace();
				}
			}
		}
		// Set fail response
		resp.setStatusCode(400);
		resp.setBody("Failed to login - bad username or password.");
		
		return resp;
	}

	public String toString(String username, String password, int playerId)
	{
		String body;
		body = "{username:\"" + username + "\",password:\"" + password + "\",\"playerID\":" + playerId + "}";
		
		return body;
	}
}
