package server.JSON;

import com.google.gson.Gson;

public class ChangeLogLevelRequest {
	private String logLevel;
	
	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	
	public ChangeLogLevelRequest() {
		
	}	

	public static ChangeLogLevelRequest fromJSON(String JSON) {
		Gson gson = new Gson();
		return gson.fromJson(JSON, ChangeLogLevelRequest.class);
	}
}
