package JUnitTests;

import static org.junit.Assert.*;
import models.*;
import shared.definitions.*;
import shared.locations.*;

import org.junit.Before;
import org.junit.Test;



public class PlayerTester 
{

	private Player playerZero;
	private Player playerOne;
	private Player playerTwo;
	private Player playerThree;
	
	
	@Before
	public void initialize()
	{
		try 
		{
			playerZero = new Player(CatanColor.RED, "Mike", new Index(0), 10);
			playerOne = new Player(CatanColor.BLUE, "ERom", new Index(1), 11);
			playerTwo = new Player(CatanColor.GREEN, "ESea", new Index(2), 12);
			playerThree = new Player(CatanColor.PURPLE, "Cannon", new Index(3), 13);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		int brickThree = 3; int oreThree = 3; int sheepFour = 4; int wheatTwo = 2; int woodTwo = 2; int noResource = 0;
		
		playerZero.addResourcesToList(brickThree, noResource, noResource, noResource, woodTwo);
		playerOne.addResourcesToList(noResource, oreThree, sheepFour, wheatTwo, noResource);
		playerTwo.addResourcesToList(noResource, noResource, noResource, noResource, noResource);
		playerThree.addResourcesToList(brickThree, oreThree, sheepFour, wheatTwo, woodTwo);
		
	}
	
	
	@Test
	public void testNewResources() 
	{
		
		
		assertTrue(playerZero.resources().getTotal() == 5);
		assertTrue(playerOne.resources().getTotal() == 9);
		assertTrue(playerTwo.resources().getTotal() == 0);
		assertTrue(playerThree.resources().getTotal() == 14);
		
		assertTrue(playerZero.resources().brick() == 3);
		assertFalse(playerZero.resources().wood() < 2);
		
		assertTrue(playerOne.resources().ore() == 3);
		assertFalse(playerOne.resources().sheep() < 4);
		assertTrue(playerOne.resources().wheat() > 1);
		
		assertTrue(playerTwo.resources().brick() == 0);
		assertFalse(playerTwo.resources().sheep() > 0);
		assertTrue(playerTwo.resources().wood() < 1);
		
		assertTrue(playerThree.resources().brick() == 3);
		assertTrue(playerThree.resources().ore() == 3);
		assertTrue(playerThree.resources().sheep() == 4);
		assertTrue(playerThree.resources().wheat() == 2);
		assertTrue(playerThree.resources().wood() == 2);
		
	}

	@Test
	public void testCanAfford()
	{
		//Zero: 3B, 0O, 0S, 0W, 2Wo
		//One: 0B, 1O, 4S, 2W, 0Wo
		//Two: 0B, 0O, 0S, 0W, 0Wo
		//Three: 3B, 3O, 4S, 2W, 2Wo
		assertTrue(playerZero.canAffordRoad());
		assertTrue(playerOne.canBuyDevCard());
		assertFalse(playerTwo.canAffordSettlement());
		playerThree.buildSettlement(new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		//Three: 2B, 3O, 3S, 1W, 1Wo
		assertFalse(playerThree.canAffordCity());
		playerThree.addResourcesToList(1, 0, 1, 1, 1);
		//Three: 3B, 3O, 4S, 2W, 2Wo
		assertTrue(playerThree.canAffordCity());
		assertTrue(playerThree.canAffordSettlement());
	}
	
	@Test
	public void testCanPlace()
	{
		VertexLocation loc = new VertexLocation(new HexLocation(0,0), VertexDirection.SouthEast);
		System.out.println(loc);
		System.out.println(loc.getNormalizedLocation());
		playerZero.canPlaceSettlement(new VertexLocation(new HexLocation(0,0), VertexDirection.SouthEast));
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(new HexLocation(0,0), EdgeDirection.SouthEast)));
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(new HexLocation(0,0), EdgeDirection.South)));
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(new HexLocation(1,0), EdgeDirection.NorthEast)));
	}
	
	@Test
	public void testBuild()
	{
		//Zero: 3B, 0O, 0S, 0W, 2Wo
		//One: 0B, 1O, 4S, 2W, 0Wo
		//Two: 0B, 0O, 0S, 0W, 0Wo
		//Three: 3B, 3O, 4S, 2W, 2Wo
		
	}
}
