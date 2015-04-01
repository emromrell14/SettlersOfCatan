package server.JSON;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class CommandList {
	String[] commands;
	
	public CommandList(List<String> commands) {
		if(commands == null) {
			this.commands = null;
		}
		else
		{
			this.commands = new String[commands.size()];
			for(int i=0; i<commands.size(); i++) {
				this.commands[i] = commands.get(i);
			}			
		}
	}

	public String[] getCommands() {
		return commands;
	}

	public void setCommands(String[] commands) {
		this.commands = commands;
	}
	
	public List<String> getCommandsList() 
	{
		List<String> list = new ArrayList<String>();
		for(String command : this.commands)
		{
			list.add(command);
		}
		return list;
	}
	
	/**
	 * Creates a CommandList object from a JSON string
	 * 
	 * @param Valid JSON string
	 * @return New CommandList object
	 */
	public static CommandList fromJSON(String JSON)
	{
		Gson gson = new Gson();
		return gson.fromJson(JSON, CommandList.class);
	}
	/**
	 * Creates the JSON code from this object
	 * 
	 * @return JSON string representation of this object
	 */
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
