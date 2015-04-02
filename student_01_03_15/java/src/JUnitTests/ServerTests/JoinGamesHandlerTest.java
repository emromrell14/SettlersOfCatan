package JUnitTests.ServerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class JoinGamesHandlerTest {

	private IProxy proxy = new Proxy();
	
	@Before
	public void setup() {
		Proxy.port = "8081";
	}
	
	@Test
	public void test() {
		System.out.print("Starting joinGame tests...");

		proxy.post("/user/login", "{username: \"a\",password: \"a\"}");
		
		String requestBody = "{id: 0,color:\"white\"}";
		String response = proxy.post("/games/join", requestBody);
		assertTrue(response.equals("Success"));
		
		requestBody = "{id: -1,color:\"white\"}";
		response = proxy.post("/games/join", requestBody);
		assertTrue(response.startsWith("Failed"));
		
		requestBody = "{id: 0,color:\"orange\"}";
		response = proxy.post("/games/join", requestBody);
		assertTrue(response.equals("Success"));
		
		requestBody = "{id: 0,color:\"yellow\"}";
		response = proxy.post("/games/join", requestBody);
		assertTrue(response.startsWith("Failed"));
		
		proxy.post("/user/register", "{username: \"hello\",password: \"worldy\"}");
		
		requestBody = "{id: 0,color:\"white\"}";
		response = proxy.post("/games/join", requestBody);
		assertTrue(response.startsWith("Failed"));
		System.out.println("Passed");
	}

}
