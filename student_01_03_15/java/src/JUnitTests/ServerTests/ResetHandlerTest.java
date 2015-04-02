package JUnitTests.ServerTests;

import static org.junit.Assert.*;

import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class ResetHandlerTest {
	
	private IProxy proxy = new Proxy();

	@Test
	public void test() {
		System.out.println("Testing Reset Handler");

		
		Proxy.port = "8081";
		proxy.post("/user/login", "{username: \"a\",password: \"a\"}");
		proxy.post("/games/join", "{id: 0,color:\"white\"}");
		
		String response = proxy.post("/game/reset", "");
		System.out.println(response);
		assertTrue(response.equals("Success"));
		
		System.out.println("Passed Reset Handler Tests");
	}

}
