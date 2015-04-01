package JUnitTests.ServerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class CommandsHandlerTest {
	
	private IProxy proxy = new Proxy();
	
	@Before
	public void setup() {
		Proxy.port = "8081";
	}

	@Test
	public void test() {
		String requestBody = "{randomTiles: true,randomNumbers:true,randomPorts:true,name:\"allrandomgame\"}";
		String response = proxy.post("/games/create", requestBody);
		System.out.println(response);
		assertTrue(response.equals("{title: \"allrandomgame\", id: 1, players: [ {}, {}, {}, {} ] }"));
		
	}

}
