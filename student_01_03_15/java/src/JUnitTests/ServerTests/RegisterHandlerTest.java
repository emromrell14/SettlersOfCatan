package JUnitTests.ServerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class RegisterHandlerTest {

	private IProxy proxy = new Proxy();
	
	@Before
	public void setup() {
		Proxy.port = "8081";
	}
	@Test
	public void test() {
		System.out.print("Starting register tests...");

		String requestBody = "{username: \"string\",password: \"string\"}";
		String response = proxy.post("/user/register", requestBody);
		assertTrue(response.equals("Success"));
		
		requestBody = "{username: \"hello\",password: \"world\"}";
		response = proxy.post("/user/register", requestBody);
		assertTrue(response.equals("Success"));
		
		requestBody = "{username: \"e\",password: \"e\"}";
		response = proxy.post("/user/register", requestBody);
		assertTrue(response.startsWith("Failed"));
		
		requestBody = "{username: \"aaaaaaaa\",password: \"aaaaaaaa\"}";
		response = proxy.post("/user/register", requestBody);
		assertTrue(response.startsWith("Failed"));
		System.out.println("Passed");
	}

}
