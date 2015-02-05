package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class ProxyTester 
{
	private IProxy mProxy;
	
	@Before
	public void initialize()
	{
		mProxy = new Proxy();
	}
	
	@Test
	public void testProxyPosts() 
	{
		System.out.println("PROXY POST TESTS:\n");
		String response = null;
		
		System.out.print("Testing /user/login with valid input - ");
		response = mProxy.post("/user/login", "{\"username\": \"Sam\",\"password\": \"sam\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		System.out.print("Testing /user/login with invalid input - ");
		response = mProxy.post("/user/login", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /user/register with valid input - ");
		response = mProxy.post("/user/register", "{\"username\": \""+ Math.random() +"\",\"password\": \"string\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		System.out.print("Testing /user/register with invalid input - ");
		response = mProxy.post("/user/register", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /games/create with valid input - ");
		response = mProxy.post("/games/create", "{\"randomTiles\": \"false\",\"randomNumbers\": \"false\",\"randomPorts\": \"false\",\"name\": \"string\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		System.out.print("Testing /games/create with invalid input - ");
		response = mProxy.post("/games/create", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /games/join with valid input - ");
		response = mProxy.post("/games/join", "{\"id\": 4,\"color\": \"red\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		System.out.print("Testing /games/join with invalid input - ");
		response = mProxy.post("/games/join", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");

		System.out.print("Testing /games/save - ");
		response = mProxy.post("/games/save", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /games/load - ");
		response = mProxy.post("/games/load", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /game/reset - ");
		response = mProxy.post("/game/reset", "");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /game/commands - ");
		response = mProxy.post("/game/commands", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /game/addAI with valid input - ");
		response = mProxy.post("/game/addAI", "{\"AIType\": \"LARGEST_ARMY\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		System.out.print("Testing /game/addAI with valid input - ");
		response = mProxy.post("/game/addAI", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/sendChat with valid input - ");
		response = mProxy.post("/moves/sendChat", "{\"type\": \"sendChat\",\"playerIndex\": 1,\"content\": \"string\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		System.out.print("Testing /moves/sendChat with invalid input - ");
		response = mProxy.post("/moves/sendChat", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/rollNumber - ");
		response = mProxy.post("/moves/rollNumber", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/robPlayer - ");
		response = mProxy.post("/moves/robPlayer", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/finishTurn - ");
		response = mProxy.post("/moves/finishTurn", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/buyDevCard - ");
		response = mProxy.post("/moves/buyDevCard", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/Year_of_Plenty - ");
		response = mProxy.post("/moves/Year_of_Plenty", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/Road_Building - ");
		response = mProxy.post("/moves/Road_Building", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/Soldier - ");
		response = mProxy.post("/moves/Soldier", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/Monopoly - ");
		response = mProxy.post("/moves/Monopoly", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/Monument - ");
		response = mProxy.post("/moves/Monument", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/buildRoad - ");
		response = mProxy.post("/moves/buildRoad", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/buildSettlement - ");
		response = mProxy.post("/moves/buildSettlement", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/buildCity - ");
		response = mProxy.post("/moves/buildCity", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/offerTrade - ");
		response = mProxy.post("/moves/offerTrade", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/acceptTrade - ");
		response = mProxy.post("/moves/acceptTrade", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/maritimeTrade - ");
		response = mProxy.post("/moves/maritimeTrade", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/discardCards - ");
		response = mProxy.post("/moves/discardCards", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /util/changeLogLevel with valid input - ");
		response = mProxy.post("/util/changeLogLevel", "{\"logLevel\": \"ALL\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		System.out.print("Testing /util/changeLogLevel with invalid input - ");
		response = mProxy.post("/util/changeLogLevel", "");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
	}

	@Test
	public void testProxyGets()
	{
		System.out.println("PROXY GET TESTS:\n");
		String response = null;
		
		System.out.print("Testing /games/list - ");
		response = mProxy.get("/games/list");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /game/model - ");
		response = mProxy.get("/game/model");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /game/commands - ");
		response = mProxy.get("/game/commands");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /game/listAI - ");
		response = mProxy.get("/game/listAI");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
	}
}
