package JUnitTests.ServerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class CreateGamesHandlerTest {

	private IProxy proxy = new Proxy();
	
	@Before
	public void setup() {
		Proxy.port = "8081";
		proxy.post("/user/login", "{username: \"a\",password: \"a\"}");
	}
	
	@Test
	public void test() {
		System.out.print("Starting createGame tests...");

		String requestBody = "{randomTiles: true,randomNumbers:true,randomPorts:true,name:\"allrandomgame\"}";
		String response = proxy.post("/games/create", requestBody);
		assertTrue(response.equals("{\"title\": \"allrandomgame\", \"id\": 1, \"players\": [ {}, {}, {}, {} ] }"));
		
		requestBody = "{randomTiles: true,randomNumbers:true,randomPorts:true,name:\"allrandomgame\"}";
		response = proxy.post("/games/create", requestBody);
		assertTrue(response.startsWith("Failed"));

		System.out.println("Passed");
	}

}
