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
		//Simple tests to make sure that the resources were all loaded in correctly and the getters works
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
	public void testCanAffordRoad()
	{
		//Can I build a road when I have no resources?
		assertFalse(playerTwo.canAffordRoad());
		
		//Can I build a road if I have just brick?
		playerTwo.addResourcesToList(1, 0, 0, 0, 0);
		assertFalse(playerTwo.canAffordRoad());
		
		//Can I build a road if I have a brick and a wood?
		playerTwo.addResourcesToList(0, 0, 0, 0, 1);
		assertTrue(playerTwo.canAffordRoad());
		
		//If I then build a road (and use those cards), can I afford another one?
		playerTwo.buildRoad(null);
		assertFalse(playerTwo.canAffordRoad());
		
		//Waste all of my roads...
		HexLocation hex00 = new HexLocation(0,0);
		playerZero.addResourcesToList(100, 0, 0, 0, 100);
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		
		//Can I still play one (I have 1 more road left)
		assertTrue(playerZero.canAffordRoad());
		
		//How about if I build one more road? (And I don't have any more in my inventory)
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		assertFalse(playerZero.canAffordRoad());
	}
	
	@Test
	public void testCanPlaceRoad()
	{
		HexLocation hex_10 = new HexLocation(-1,0);
		HexLocation hex_11 = new HexLocation(-1,1);
		HexLocation hex0_1 = new HexLocation(0,-1);
		HexLocation hex00 = new HexLocation(0,0);
		HexLocation hex01 = new HexLocation(0,1);
		HexLocation hex1_1 = new HexLocation(1,-1);
		HexLocation hex10 = new HexLocation(1,0);
		
		//Can you place a road on an empty board?
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.North)));
		
		//Can you place a road next to another road? (Testing some normalized, others not)
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthWest));
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.North)));
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.SouthWest)));
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(hex0_1, EdgeDirection.SouthWest)));
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(hex_10, EdgeDirection.South)));
		
		//Can you place a road where another road already is?
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.NorthWest)));
		
		//What if I call it a different place (but it's still occupied)?
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex_10, EdgeDirection.SouthEast)));
		
		//Can you place a road on the same hex, but not attached to your road?
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.SouthEast)));
	}
	
	@Test
	public void testCanAffordSettlement()
	{
		//Can I build a settlement when I have no resources?
		assertFalse(playerTwo.canAffordSettlement());
		
		//Can I build a settlement if I have just brick and wheat?
		playerTwo.addResourcesToList(1, 0, 0, 1, 0);
		assertFalse(playerTwo.canAffordRoad());
		
		//Can I build a settlement if I have a brick, wheat, sheep and a wood?
		playerTwo.addResourcesToList(0, 0, 1, 0, 1);
		assertTrue(playerTwo.canAffordSettlement());
		
		//If I then build a road (and use those cards), can I afford another one?
		playerTwo.buildSettlement(null);
		assertFalse(playerTwo.canAffordSettlement());
		
		//Waste all of my roads...
		playerZero.addResourcesToList(100, 0, 100, 100, 100);
		playerZero.buildSettlement(null);
		playerZero.buildSettlement(null);
		playerZero.buildSettlement(null);
		playerZero.buildSettlement(null);
		
		//Can I still play one (I have 1 more road left)
		assertTrue(playerZero.canAffordSettlement());
		
		//How about if I build one more road? (And I don't have any more in my inventory)
		playerZero.buildSettlement(null);
		assertFalse(playerZero.canAffordSettlement());
	}
	
	@Test
	public void testCanPlaceSettlement()
	{
		HexLocation hex_10 = new HexLocation(-1,0);
		HexLocation hex_11 = new HexLocation(-1,1);
		HexLocation hex0_1 = new HexLocation(0,-1);
		HexLocation hex00 = new HexLocation(0,0);
		HexLocation hex01 = new HexLocation(0,1);
		HexLocation hex1_1 = new HexLocation(1,-1);
		HexLocation hex10 = new HexLocation(1,0);
		
		//Can I place a settlement on an empty board?
		assertFalse(playerZero.canPlaceSettlement(new VertexLocation(hex00, VertexDirection.NorthEast)));
		
		//Can I build a settlement on a board that is loaded?
		playerZero.buildSettlement(new VertexLocation(hex00, VertexDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.North));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthWest));
		assertTrue(playerZero.canPlaceSettlement(new VertexLocation(hex_11, VertexDirection.NorthEast)));
		
		//Can I build a settlement on a vertex that is connected by roads, but not two away? (This should return true, because the board will check the distances)
		assertTrue(playerZero.canPlaceSettlement(new VertexLocation(hex00, VertexDirection.NorthWest)));
		
		//Can I build a settlement on a vertex that is not connected by roads?
		assertFalse(playerZero.canPlaceSettlement(new VertexLocation(hex00, VertexDirection.SouthEast)));
		
		//Try it on a different hex and direction
		playerZero.buildSettlement(new VertexLocation(hex10, VertexDirection.SouthWest));
		playerZero.buildRoad(new EdgeLocation(hex10, EdgeDirection.South));
		playerZero.buildRoad(new EdgeLocation(hex10, EdgeDirection.SouthEast));
		assertTrue(playerZero.canPlaceSettlement(new VertexLocation(hex10.getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest)));
	}
	
	@Test
	public void testCanAffordCity()
	{
		//Can I afford a city with no resources?
		assertFalse(playerTwo.canAffordCity());
		
		//Can I afford a city with 2 wheat and 2 ore?
		playerTwo.addResourcesToList(0, 2, 0, 2, 0);
		assertFalse(playerTwo.canAffordCity());
		
		//Can I afford a city with 2 wheat and 3 ore? (but I haven't built a settlement yet)
		playerTwo.addResourcesToList(0,1,0,0,0);
		assertFalse(playerTwo.canAffordCity());
		
		//If a build a settlement, would I THEN be able to build a city?
		playerTwo.addResourcesToList(1,0,1,1,1);
		playerTwo.buildSettlement(null);
		assertTrue(playerTwo.canAffordCity());
		
		//Waste all my cities...
		HexLocation hex00 = new HexLocation(0,0);
		VertexLocation vertex = new VertexLocation(hex00, VertexDirection.East);
		playerZero.addResourcesToList(1, 30, 1, 21, 1);
		playerZero.buildSettlement(vertex);
		playerZero.buildCity(vertex);
		playerZero.buildSettlement(vertex);
		playerZero.buildCity(vertex);
		playerZero.buildSettlement(vertex);
		playerZero.buildCity(vertex);
		playerZero.buildSettlement(vertex);
		
		//Can I still build a city? (I have one left in my inventory)
		assertTrue(playerZero.canAffordCity());
		playerZero.buildCity(vertex);
		
		//Now can I build one? (I have used them all up)
		assertFalse(playerZero.canAffordCity());
		
		//Do I still have all of my settlements? (Did they get replaced)
		assertTrue(playerZero.settlementCount() == 5);
	}
	
	@Test
	public void testCanPlaceCity()
	{
		HexLocation hex_10 = new HexLocation(-1,0);
		HexLocation hex_11 = new HexLocation(-1,1);
		HexLocation hex0_1 = new HexLocation(0,-1);
		HexLocation hex00 = new HexLocation(0,0);
		HexLocation hex01 = new HexLocation(0,1);
		HexLocation hex1_1 = new HexLocation(1,-1);
		HexLocation hex10 = new HexLocation(1,0);
		
		//Can I place a city on an empty board
		assertFalse(playerThree.canPlaceCity(new VertexLocation(hex00, VertexDirection.SouthWest)));
		
		//Can I place a city if I have roads attached to it? (but no settlement)
		playerThree.addResourcesToList(100, 100, 100, 100, 100);
		playerThree.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthWest));
		playerThree.buildRoad(new EdgeLocation(hex00, EdgeDirection.SouthWest));
		assertFalse(playerThree.canPlaceCity(new VertexLocation(hex00, VertexDirection.SouthWest)));
		
		//Can I place a city if I have roads attached AND a settlement?
		playerThree.buildSettlement(new VertexLocation(hex00, VertexDirection.SouthWest));
		assertTrue(playerThree.canPlaceCity(new VertexLocation(hex00, VertexDirection.SouthWest)));
		
		//Can I place a city if I have a city already there?
		playerThree.buildCity(new VertexLocation(hex00, VertexDirection.SouthWest));
		assertFalse(playerThree.canPlaceCity(new VertexLocation(hex00, VertexDirection.SouthWest)));
	}
}
