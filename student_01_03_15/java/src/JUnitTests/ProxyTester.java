package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import proxy.IProxy;
import proxy.Proxy;

public class ProxyTester 
{	
	@Test
	public void testProxyPosts() 
	{
		IProxy mProxy = new Proxy();
		System.out.println("PROXY POST TESTS:\n");
		String response = null;
		
		System.out.print("Testing /user/login - ");
		response = mProxy.post("/user/login", "{\"username\": \"Sam\",\"password\": \"sam\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /user/register - ");
		response = mProxy.post("/user/register", "{\"username\": \""+ Math.random() +"\",\"password\": \"string\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /games/create - ");
		response = mProxy.post("/games/create", "{\"randomTiles\": \"false\",\"randomNumbers\": \"false\",\"randomPorts\": \"false\",\"name\": \"test2\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /games/join - ");
		response = mProxy.post("/games/join", "{\"id\": 4,\"color\": \"blue\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");

		System.out.print("Testing /games/save - ");
		response = mProxy.post("/games/save", "{\"id\": 1,\"name\": \"AIGame\"}");
		assertTrue(response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /games/load - ");
		response = mProxy.post("/games/load", "{\"name\": \"AIGame\"}");
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
		
		System.out.print("Testing /game/addAI - ");
		response = mProxy.post("/game/addAI", "{\"AIType\": \"LARGEST_ARMY\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /moves/sendChat - ");
		response = mProxy.post("/moves/sendChat", "{\"type\": \"sendChat\",\"playerIndex\": 1,\"content\": \"string\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
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
		
		System.out.print("Testing /util/changeLogLevel - ");
		response = mProxy.post("/util/changeLogLevel", "{\"logLevel\": \"ALL\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
	}

	@Test
	public void testProxyGets()
	{
		IProxy mProxy = new Proxy();
		System.out.println("PROXY GET TESTS:\n");
		String response = null;

		System.out.print("Testing /user/login - ");
		response = mProxy.post("/user/login", "{\"username\": \"Sam\",\"password\": \"sam\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /games/create - ");
		response = mProxy.post("/games/create", "{\"randomTiles\": \"false\",\"randomNumbers\": \"false\",\"randomPorts\": \"false\",\"name\": \"test\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
		System.out.print("Testing /games/join - ");
		response = mProxy.post("/games/join", "{\"id\": 3,\"color\": \"red\"}");
		assertTrue(!response.split(" ")[0].equalsIgnoreCase("Failed"));
		System.out.println("PASSED");
		
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
		System.out.println("PASSED\n");
	}
}
