package proxy;

//import cookie.Cookie;

public interface IProxy
{
	/**Sends a POST request to the IServer.
	 * 
	 * @pre	requestPath is a valid path recognized by the server
	 * @pre Cookie != null
	 * @post sends request to server
	 * @post returns response from server
	 * @param requestPath 	The request's path being sent to the server.
	 * @param jsonBody		The JSON string that will be sent in the request body.
	 * @param cookie		The user's cookie that will be sent in the request header.
	 * @return Response from the server.
	 */
	String post(String requestPath, String jsonBody);
	
	/**
	 * Sends a GET request to the IServer
	 * 
	 * @pre Cookie != null
	 * @post sends request to server 
	 * @post returns response from server
	 * @param requestPath 	The request's path being sent to the server.
	 * @param cookie	The user's cookie that will be sent in the request header.
	 * @return Response from the server.
	 */
	String get(String requestPath);

	int getPlayerID();
}
