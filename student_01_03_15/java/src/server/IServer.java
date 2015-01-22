package server;

public interface IServer 
{
	int getPortNumber();
	
	String getHost();
	
	void run();
}
