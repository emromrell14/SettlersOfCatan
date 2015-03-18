package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facade.MasterManager;

public class JoinGameTester 
{
	private MasterManager master;
	@Before
	public void setUp() throws Exception 
	{
		master = MasterManager.getInstance();
	}

	@After
	public void tearDown() throws Exception
	{
		
	}

	@Test
	public void joinTest() 
	{
		master.login("Sam", "sam");
		master.createGame(true, true, true, "test");
		master.joinGame(3, "red");
		fail("Not yet implemented");
	}

}
