package JUnitTests.ServerTests;

import static org.junit.Assert.*;

import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class LoginHandlerTest {

	private IProxy proxy = new Proxy();
	@Test
	public void test() {
		Proxy.port = "8081";
		
		System.out.println("Testing Login Handler");

		
		String requestBody = "{username: \"a\",password: \"a\"}";
		String response = proxy.post("/user/login", requestBody);
		assertTrue(response.equals("Success"));
		
		requestBody = "{username: \"doesnt\",password: \"exist\"}";
		response = proxy.post("/user/login", requestBody);
		assertTrue(response.startsWith("Failed"));
		
		System.out.println("Passed Login Handler Tests");

	}

}
