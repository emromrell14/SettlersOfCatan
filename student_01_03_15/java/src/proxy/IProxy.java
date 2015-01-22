package proxy;

import cookie.Cookie;

public interface IProxy
{
	/**Sends a POST request to the IServer.
	 * 
	 * @param requestPath 	The request's path being sent to the server.
	 * @param jsonBody		The JSON string that will be sent in the request body.
	 * @param cookie		The user's cookie that will be sent in the request header.
	 * @return Response from the server.
	 */
	String post(String requestPath, String jsonBody, Cookie cookie);
	
	/**
	 * 
	 * @param cookie	The user's cooke that will be sent in the request header.
	 * @return Response from the server.
	 */
	String get(Cookie cookie);
}
