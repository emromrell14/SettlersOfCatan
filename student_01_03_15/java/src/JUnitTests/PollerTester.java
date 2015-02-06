package JUnitTests;

import static org.junit.Assert.*;
import models.Building;
import models.Game;
import models.Index;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facade.MasterManager;
import poller.Poller;
import proxy.IProxy;
import proxy.MockProxy;
import proxy.Proxy;
import shared.definitions.BuildingType;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class PollerTester 
{
	private Poller poller;
	private IProxy proxy;

	@Before
	public void initialize() 
	{
		poller = new Poller();
		MasterManager.getInstance().communicateWithMockProxy();
		proxy = new MockProxy();
	}
	
	
	@Test
	public void test() 
	{
		System.out.println("POLLER TEST:\n");
		int beforeVersion = poller.getVersion();

		poller.run();
		try 
		{
			System.out.println("Going to sleep, version:" + beforeVersion);
		    Thread.sleep(5000);                 //1000 milliseconds is one second.
		} 
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		System.out.println("Awake, version: " + poller.getVersion());
		assertTrue(poller.getVersion() != beforeVersion);
		System.out.println("Poller's getGameModel() query passed.");
			
	}

	
	@After
	public void tearDown()
	{
		return;
	}

}
