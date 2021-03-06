package JUnitTests.ServerTests;

import static org.junit.Assert.*;

import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class LoginHandlerTest {

	private IProxy proxy = new Proxy();
	@Test
	public void test() {
		System.out.print("Starting login tests...");

		Proxy.port = "8081";
		
		String requestBody = "{username: \"a\",password: \"a\"}";
		String response = proxy.post("/user/login", requestBody);
		assertTrue(response.equals("Success"));
		
		requestBody = "{username: \"doesnt\",password: \"exist\"}";
		response = proxy.post("/user/login", requestBody);
		assertTrue(response.startsWith("Failed"));

		System.out.print("Passed");
	}
}
