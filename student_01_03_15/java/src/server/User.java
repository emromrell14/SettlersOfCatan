package server;

public class User implements IUser
{

	private String username;
	private String password;
	private int id;
	
	public User(int id)
	{
		this.id = id;
	}
	
	@Override
	public String getUsername() 
	{
		return username;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
