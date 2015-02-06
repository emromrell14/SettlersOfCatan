package JUnitTests;

import static org.junit.Assert.*;
import models.Game;
import models.Index;
import models.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import poller.Poller;
import proxy.IProxy;
import proxy.MockProxy;
import shared.definitions.CatanColor;
import facade.MasterManager;

public class GameModelTester 
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
	public void test() throws Exception
	{
		// Get most recent game
		Game game = poller.getGameModel(0);
		System.out.println("Aquiring current game model...");
		assertTrue(game.version() == 1);
		System.out.println("PASSED");
		
		// Check PLAYER properties
		System.out.println("Checking player name populated correctly...");
		assertTrue(game.getPlayer(12).name().equals("eric"));
		System.out.println("PASSED");
		
		System.out.println("Checking player index populated correctly...");
		assertTrue(game.getPlayer(12).playerIndex().value() == 0);
		System.out.println("PASSED");
		
		System.out.println("Checking player color populated correctly...");
		assertTrue(game.getPlayer(12).color() == CatanColor.RED);
		System.out.println("PASSED");
		
		System.out.println("Checking player buildings and roads populated correctly...");
		assertTrue(game.getPlayer(12).settlements().size() == 0);
		assertTrue(game.getPlayer(12).cities().size() == 0);
		assertTrue(game.getPlayer(12).roads().size() == 0);
		assertTrue(game.getPlayer(12).cityCount() == 4);
		assertTrue(game.getPlayer(12).settlementCount() == 5);
		assertTrue(game.getPlayer(12).roadCount() == 15);
		System.out.println("PASSED");
		
		System.out.println("Checking player DevCards populated correctly...");
		assertTrue(game.getPlayer(12).devCards().size() == 0);
		System.out.println("PASSED");
		
		// Should change mock proxy so that player has played a bulding or 2
		
		// Check BOARD properties
		System.out.println("Checking bank populated correctly...");
		assertTrue(game.bank().brick() == 24);
		assertTrue(game.bank().ore() == 24);
		assertTrue(game.bank().wheat() == 24);
		assertTrue(game.bank().sheep() == 24);
		assertTrue(game.bank().wood() == 24);
		System.out.println("PASSED");
		
		System.out.println("Checking turntracker populated correctly...");
		assertTrue(game.turnTracker().status() == Status.FIRSTROUND);
		assertTrue(game.turnTracker().currentTurn().value() == 0);
		assertTrue(game.turnTracker().largestArmy().value() == -1);
		assertTrue(game.turnTracker().longestRoad().value() == -1);
		System.out.println("PASSED");
		
		System.out.println("Checking game winner populated correctly...");
		assertTrue(game.winner().value() == -1);
		System.out.println("PASSED");
		
		System.out.println("Checking board populated correctly...");
		// insert code here
		System.out.println("PASSED");
		
		
		
		
		
		
	}
	
	@After
	public void tearDown()
	{
		return;
	}

}
