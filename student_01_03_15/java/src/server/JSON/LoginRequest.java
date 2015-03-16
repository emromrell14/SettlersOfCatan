package server.JSON;

import com.google.gson.Gson;

public class LoginRequest {
	private String username;
	private String password;
	
	public LoginRequest() {
		
	}
	
	public static LoginRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, LoginRequest.class);
	}
}
