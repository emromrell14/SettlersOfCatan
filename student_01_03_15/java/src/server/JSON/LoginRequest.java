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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
