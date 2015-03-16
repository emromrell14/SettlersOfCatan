package server.JSON;

import com.google.gson.Gson;

public class RegisterRequest {
	private String username;
	private String password;
	
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
	public RegisterRequest() {
		
	}
	public static RegisterRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, RegisterRequest.class);
	}
	
}
