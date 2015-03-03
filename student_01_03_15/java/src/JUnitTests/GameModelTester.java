package JUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import models.Game;
import models.Hex;
import models.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.HexType;
import facade.MasterManager;

public class GameModelTester 
{
	private List<Hex> hexes;

	@Before
	public void initialize() 
	{
		MasterManager.getInstance().communicateWithMockProxy();
		hexes = new ArrayList<Hex>();
	}

	@Test
	public void test() throws Exception
	{
		MasterManager.getInstance().getGameModel(0);
		Game game = MasterManager.getInstance().getCurrentModel();
		//System.out.print("Aquiring current game model");
		assertTrue(game.version() == 1);
		//System.out.println(" - PASSED");
		
		// Check PLAYER properties
		//System.out.print("Checking player name populated correctly");
		assertTrue(game.getPlayer(12).name().equals("eric"));
		//System.out.println(" - PASSED");
		
		//System.out.print("Checking player index populated correctly");
		assertTrue(game.getPlayer(12).playerIndex().value() == 0);
		//System.out.println(" - PASSED");
		
		//System.out.print("Checking player color populated correctly");
		assertTrue(game.getPlayer(12).color() == CatanColor.RED);
		//System.out.println(" - PASSED");
		
		//System.out.print("Checking player buildings and roads populated correctly");
		assertTrue(game.getPlayer(12).settlements().size() == 0);
		assertTrue(game.getPlayer(12).cities().size() == 0);
		assertTrue(game.getPlayer(12).roads().size() == 0);
		assertTrue(game.getPlayer(12).cityCount() == 4);
		assertTrue(game.getPlayer(12).settlementCount() == 5);
		assertTrue(game.getPlayer(12).roadCount() == 15);
		//System.out.println(" - PASSED");
		
		//System.out.print("Checking player DevCards populated correctly");
		assertTrue(game.getPlayer(12).devCards().size() == 0);
		//System.out.println(" - PASSED");
		
		// Should change mock proxy so that player has played a bulding or 2
		
		// Check BOARD properties
		//System.out.print("Checking bank populated correctly");
		assertTrue(game.bank().brick() == 24);
		assertTrue(game.bank().ore() == 24);
		assertTrue(game.bank().wheat() == 24);
		assertTrue(game.bank().sheep() == 24);
		assertTrue(game.bank().wood() == 24);
		//System.out.println(" - PASSED");
		
		//System.out.print("Checking turntracker populated correctly...");
		assertTrue(game.turnTracker().status() == Status.FIRSTROUND);
		assertTrue(game.turnTracker().currentTurn().value() == 0);
		assertTrue(game.turnTracker().largestArmy().value() == -1);
		assertTrue(game.turnTracker().longestRoad().value() == -1);
		//System.out.println(" - PASSED");
		
		//System.out.print("Checking game winner populated correctly");
		assertTrue(game.winner().value() == -1);
		//System.out.println(" - PASSED");
		
		//System.out.print("Checking board populated correctly");
		hexes = game.board().hexes();
		assertTrue(hexes.size() == 19);
		for(Hex hex : hexes)
		{
			assertTrue(hex.resource() ==  HexType.BRICK ||hex.resource() ==  HexType.WOOD || hex.resource() ==  HexType.ORE ||
								hex.resource() ==  HexType.WHEAT || hex.resource() ==  HexType.DESERT || hex.resource() ==  HexType.WATER
								|| hex.resource() ==  HexType.SHEEP);
			assertTrue(hex.number().value() >= 0 && hex.number().value() <= 12);
			assertTrue(hex.location() != null);
		}
		//System.out.println(" - PASSED");
	
	}
	
	@After
	public void tearDown()
	{
		return;
	}

}
