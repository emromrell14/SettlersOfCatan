package proxy;

public interface IProxy
{
	void post(String request, String json);
	
	void get();
}
