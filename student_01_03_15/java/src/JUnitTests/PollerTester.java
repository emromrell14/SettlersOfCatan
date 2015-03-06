package JUnitTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facade.MasterManager;
import poller.Poller;

public class PollerTester 
{
	private Poller poller;

	@Before
	public void initialize() 
	{
		poller = new Poller();
		MasterManager.getInstance().communicateWithMockProxy();
	}
	
	
	@Test
	public void test() 
	{
		////System.out.println("POLLER TEST:\n");
		int beforeVersion = poller.getVersion();

		poller.run();
		try 
		{
			////System.out.println("Going to sleep, version:" + beforeVersion);
		    Thread.sleep(5000);                 //1000 milliseconds is one second.
		} 
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		////System.out.println("Awake, version: " + poller.getVersion());
		//assertTrue(poller.getVersion() != beforeVersion);
		////System.out.println("Poller's getGameModel() query passed.");
		poller.interrupt();	
	}

	
	@After
	public void tearDown()
	{
		return;
	}

}
