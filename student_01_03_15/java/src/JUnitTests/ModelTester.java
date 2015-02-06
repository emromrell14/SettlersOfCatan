package JUnitTests;

import models.*;
import facade.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.definitions.CatanColor;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class ModelTester 
{
	private ModelManager mm;

	@Before
	public void initialize() 
	{
		mm = new ModelManager();
		mm.updateModel(new Game());
		try
		{
			mm.gameModel().addPlayer(new Player(CatanColor.RED, "Mike", new Index(0), 10));
			mm.gameModel().addPlayer(new Player(CatanColor.BLUE, "ERom", new Index(1), 11));
			mm.gameModel().addPlayer(new Player(CatanColor.GREEN, "ESea", new Index(2), 12));
			mm.gameModel().addPlayer(new Player(CatanColor.PURPLE, "Cannon", new Index(3), 13));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCanAffordRoad()
	{
		System.out.println("testCanAffordRoad");
		mm.gameModel().getPlayer(10).addResourcesToList(0, 0, 0, 0, 0);
		
		System.out.println("Can I build a road without resources");
		assertFalse(mm.canAffordRoad(10));
		System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceRoad()
	{
		
	}
	
	@Test
	public void testCanAffordSettlement()
	{
		System.out.println("Testing canAffordSettlement\n ");
		
		System.out.print("Test with too few resources - ");
		Player p = mm.gameModel().getPlayer(11);
		assertFalse(mm.canAffordSettlement(11));
		System.out.println("PASSED");
		
		p.addResourcesToList(2, 0, 2, 2, 2);
		System.out.print("Test with enough resources - ");
		assertTrue(mm.canAffordSettlement(11));
		System.out.println("PASSED");
		
		System.out.print("Test with enough resources, but no more settlements - ");
		p.buildSettlement(null);
		p.buildSettlement(null);
		p.buildSettlement(null);
		p.buildSettlement(null);
		p.buildSettlement(null);
		assertFalse(mm.canAffordSettlement(11));
		System.out.println("PASSED");
	}
	
	@Test
	public void testCanPlaceSettlement()
	{
		
	}
	
	@Test
	public void testCanAffordCity()
	{
		System.out.println("Testing canAffordCity\n");
		System.out.print("Test with too few resources: ");
		mm.gameModel().getPlayer(12).addResourcesToList(0, 0, 0, 0, 0);
		assertFalse(mm.canAffordCity(12));	//false cause it has no resources and no settlement to place on
		System.out.println("Passed");
		
		System.out.print("Test with no settlements to build on");
		mm.gameModel().getPlayer(12).addResourcesToList(0, 3, 0, 2, 0);
		assertFalse(mm.canAffordCity(12));	//false cause it has no settlements to replace with city
		System.out.println("Passed");
		
		System.out.print("Test with resources, and settlement to build on");
		mm.gameModel().getPlayer(12).addResourcesToList(1, 1, 1, 1, 1);
		VertexLocation loc = new VertexLocation(new HexLocation(1,1), VertexDirection.East);
		mm.gameModel().getPlayer(12).buildSettlement(loc);
		assertTrue(mm.canAffordCity(12));
		System.out.println("Passed");
	}
	
	@Test
	public void testCanBuyDevCard()
	{
		System.out.println("Testing canBuyDevCard/n");
		System.out.print("Test with too few resources");
		mm.gameModel().getPlayer(12).addResourcesToList(0, 0, 0, 0, 0);
		assertFalse(mm.canBuyDevCard(12));
		System.out.println("Passed");
		
		System.out.print("Testing with sufficient resources");
		mm.gameModel().getPlayer(12).addResourcesToList(0, 1, 1, 1, 0);
		assertTrue(mm.canBuyDevCard(12));
		System.out.println("Passed");
	}
	
	/* NOTE TO ERIC R.
	 * MOST OF THE REMAINING TESTS HERE ARE REALLY ONLY USING THE MODEL
	 * IN ORDER TO CHECK THE TURN TRACKER AND VERIFY THAT IT IS THIS 
	 * PLAYER'S TURN
	 * 
	 * THEN THEY CHECK THE OTHER CONDITIONS USING THE CORRESPONDING METHODS
	 * IN THE PLAYER CLASS
	 */
	@Test
	public void testCanPlayDevCard()
	{
		
	}
	
	@Test
	public void testCanOfferTrade()
	{
		
	}
	
	@Test
	public void testCanAcceptTrade()
	{
		
	}
	
	@Test
	public void testCanMaritimeTrade()
	{
		
	}
	
	@Test
	public void testCanRollDice()
	{
		
	}
	
	@Test
	public void testCanDiscard()
	{
		
	}
	
	@Test
	public void testCanFinishTurn()
	{
		
	}
	
	@Test
	public void testCanPlayYearOfPlenty()
	{
		
	}
	
	@Test
	public void testCanPlaySoldier()
	{
		
	}
	
	@Test
	public void testCanPlayMonopoly()
	{
		
	}
	
	@Test
	public void testCanPlayMonument()
	{
		
	}
	
	@Test
	public void testCanPlaceRobber()
	{
		
	}
	
	@Test
	public void testCanPlayRoadBuilder()
	{
		
	}
	
	
	@After
	public void tearDown()
	{
		return;
	}
}
