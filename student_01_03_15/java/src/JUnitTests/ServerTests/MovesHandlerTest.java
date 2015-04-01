package JUnitTests.ServerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class MovesHandlerTest {
	
	private IProxy proxy = new Proxy();
	
	@Before
	public void setup() {
		Proxy.port = "8081";
		proxy.post("/user/login", "{username: \"a\",password: \"a\"}");
		proxy.post("/games/join", "{id: 0,color:\"white\"}");
	}

	@Test
	public void sendChat() {
		String requestBody = "{\"type\": \"sendChat\",\"playerIndex\": 0,\"content\": \"This is a message to be sent\"}";
		String response = proxy.post("/moves/sendChat", requestBody);
		assertTrue(response.contains("This is a message to be sent"));

		requestBody = "{\"type\": \"sendChat\",\"playerIndex\": 0,\"content\": \"This is a message to be sent that is too long.ddddThis is a message to be sent that is too long.ddddThis is a message to be sent that is too long.ddddThis is a message to be sent that is too long.dddd!\"}";
		response = proxy.post("/moves/sendChat", requestBody);
		assertFalse(response.contains("!"));
	}
	
	@Test
	public void rollNumber() {
		String requestBody = "{\"type\": \"rollNumber\",\"playerIndex\": 0,\"number\": 5}";
		String response = proxy.post("/moves/rollNumber", requestBody);
		assertTrue(response.contains("PLAYING"));
	}
	
	@Test
	public void robPlayer() {
		proxy.post("/moves/rollNumber", "{\"type\": \"rollNumber\",\"playerIndex\": 0,\"number\": 7}");
	}

}
