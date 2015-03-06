package JUnitTests;

import static org.junit.Assert.*;
import models.*;
import shared.definitions.*;
import shared.locations.*;

import org.junit.After;
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
		////System.out.println("\nRESOURCES TEST:\n");
		
		////System.out.print("Testing load resources and getters");
		//Simple tests to make sure that the resources were all loaded in correctly and the getters works
		assertTrue(playerZero.resources().getTotal() == 5);
		////System.out.print(" . ");
		assertTrue(playerOne.resources().getTotal() == 9);
		////System.out.print(" . ");
		assertTrue(playerTwo.resources().getTotal() == 0);
		////System.out.print(" . ");
		assertTrue(playerThree.resources().getTotal() == 14);
		////System.out.println(" - PASSED");

		////System.out.print("Testing can discard");
		//these should work unless we find out we need to change the canDiscard function
		assertFalse(playerZero.canDiscard());
		////System.out.print(" . ");
		assertTrue(playerOne.canDiscard());
		////System.out.print(" . ");
		assertFalse(playerTwo.canDiscard());
		////System.out.print(" . ");
		assertTrue(playerThree.canDiscard());
		////System.out.print(" . ");
		
		assertTrue(playerZero.resources().brick() == 3);
		////System.out.print(" . ");
		assertFalse(playerZero.resources().wood() < 2);
		////System.out.print(" . ");
		
		assertTrue(playerOne.resources().ore() == 3);
		////System.out.print(" . ");
		assertFalse(playerOne.resources().sheep() < 4);
		////System.out.print(" . ");
		assertTrue(playerOne.resources().wheat() > 1);
		////System.out.print(" . ");
		
		assertTrue(playerTwo.resources().brick() == 0);
		////System.out.print(" . ");
		assertFalse(playerTwo.resources().sheep() > 0);
		////System.out.print(" . ");
		assertTrue(playerTwo.resources().wood() < 1);
		////System.out.print(" . ");
		
		assertTrue(playerThree.resources().brick() == 3);
		////System.out.print(" . ");
		assertTrue(playerThree.resources().ore() == 3);
		////System.out.print(" . ");
		assertTrue(playerThree.resources().sheep() == 4);
		////System.out.print(" . ");
		assertTrue(playerThree.resources().wheat() == 2);
		////System.out.print(" . ");
		assertTrue(playerThree.resources().wood() == 2);
		////System.out.println(" - PASSED");
		
	}

	@Test
	public void testCanAffordRoad()
	{
		////System.out.println("\nCAN AFFORD ROAD TEST:\n");
		
		////System.out.print("Testing build road with no resources");
		//Can I build a road when I have no resources?
		assertFalse(playerTwo.canAffordRoad());
		////System.out.println(" - PASSED");

		////System.out.print("Testing build road without wood");
		//Can I build a road if I have just brick?
		playerTwo.addResourcesToList(1, 0, 0, 0, 0);
		assertFalse(playerTwo.canAffordRoad());
		////System.out.println(" - PASSED");

		////System.out.print("Testing build road with brick and wood");
		//Can I build a road if I have a brick and a wood?
		playerTwo.addResourcesToList(0, 0, 0, 0, 1);
		assertTrue(playerTwo.canAffordRoad());
		////System.out.println(" - PASSED");

		////System.out.print("Testing build another road after spending all resources on last road");
		//If I then build a road (and use those cards), can I afford another one?
		playerTwo.buildRoad(null);
		assertFalse(playerTwo.canAffordRoad());
		////System.out.println(" - PASSED");
		
		////System.out.println("Expending all roads...");
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
		
		////System.out.print("Testing build road after I've already built 14 (Should pass)");
		//Can I still play one (I have 1 more road left)
		assertTrue(playerZero.canAffordRoad());
		////System.out.println(" - PASSED");		
		
		////System.out.print("Testing build road after I've already built 15 (Should fail)");
		//How about if I build one more road? (And I don't have any more in my inventory)
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthEast));
		assertFalse(playerZero.canAffordRoad());
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceRoad()
	{
		////System.out.println("\nCAN PLACE ROAD TEST\n");
		HexLocation hex_10 = new HexLocation(-1,0);
		//HexLocation hex_11 = new HexLocation(-1,1);
		HexLocation hex0_1 = new HexLocation(0,-1);
		HexLocation hex00 = new HexLocation(0,0);
		//HexLocation hex01 = new HexLocation(0,1);
		//HexLocation hex1_1 = new HexLocation(1,-1);
		//HexLocation hex10 = new HexLocation(1,0);
		
		////System.out.print("Testing placing on an empty board (Should fail - no settlements");
		//Can you place a road on an empty board?
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.North)));
		////System.out.println(" - PASSED");

		////System.out.print("Testing place road next to another road (On various edges)");
		//Can you place a road next to another road? (Testing some normalized, others not)
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthWest));
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.North)));
		////System.out.print(" . ");
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.SouthWest)));
		////System.out.print(" . ");
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(hex0_1, EdgeDirection.SouthWest)));
		////System.out.print(" . ");
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(hex_10, EdgeDirection.South)));
		////System.out.print(" - PASSED");
		
		////System.out.print("Testing place road where another already exists (Should fail)");
		//Can you place a road where another road already is?
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.NorthWest)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing using a different address for the same edge (Should also fail)");
		//What if I call it a different place (but it's still occupied)?
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex_10, EdgeDirection.SouthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing place road on same hex but not attached to your road (should fail)");
		//Can you place a road on the same hex, but not attached to your road?
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.SouthEast)));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceRoadFirstOrSecondRound()
	{
		////System.out.println("\nCAN PLACE ROAD FIRST OR SECOND ROUND TEST:\n");
		//The Player.canPlaceRoad(Edge,Vertex) function will only be used when the turn tracker has been checked, and it is the first or second turn
		//HexLocation hex_10 = new HexLocation(-1,0);
		//HexLocation hex_11 = new HexLocation(-1,1);
		HexLocation hex0_1 = new HexLocation(0,-1);
		HexLocation hex00 = new HexLocation(0,0);
		//HexLocation hex01 = new HexLocation(0,1);
		//HexLocation hex1_1 = new HexLocation(1,-1);
		//HexLocation hex10 = new HexLocation(1,0);
		
		////System.out.print("Testing place road next to settlement (Should succeed)");
		//Can you place a road next to a settlement?
		assertTrue(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.North), new VertexLocation(hex00, VertexDirection.NorthWest)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing place road not next to settlement (First Round - Should fail)");
		//Can you place a road not next to another settlement? (Testing some normalized, others not)
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.North), new VertexLocation(hex00, VertexDirection.SouthWest)));
		////System.out.print(" . ");
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex00, EdgeDirection.SouthWest), new VertexLocation(hex0_1, VertexDirection.SouthEast)));
		////System.out.print(" . ");
		assertFalse(playerZero.canPlaceRoad(new EdgeLocation(hex0_1, EdgeDirection.SouthWest), new VertexLocation(hex00, VertexDirection.East)));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanAffordSettlement()
	{
		////System.out.println("\nCAN AFFORD SETTLEMENT TEST:\n");
		////System.out.print("Testing build settlement with no resources (Should fail)");
		//Can I build a settlement when I have no resources?
		assertFalse(playerTwo.canAffordSettlement());
		////System.out.println(" - PASSED");

		////System.out.print("Testing build settlement with just brick and wheat (Should fail)");
		//Can I build a settlement if I have just brick and wheat?
		playerTwo.addResourcesToList(1, 0, 0, 1, 0);
		assertFalse(playerTwo.canAffordRoad());
		////System.out.println(" - PASSED");

		////System.out.print("Testing build settlement with brick, sheep, wheat, and wood (Should succeed)");
		//Can I build a settlement if I have a brick, wheat, sheep and a wood?
		playerTwo.addResourcesToList(0, 0, 1, 0, 1);
		assertTrue(playerTwo.canAffordSettlement());
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing build another settlement after spending all resources on previous one (Should fail)");
		//If I then build a settlement (and use those cards), can I afford another one?
		playerTwo.buildSettlement(null);
		assertFalse(playerTwo.canAffordSettlement());
		////System.out.println(" - PASSED");
		
		////System.out.println("Expending all settlements...");
		//Waste all of my settlements...
		playerZero.addResourcesToList(100, 0, 100, 100, 100);
		playerZero.buildSettlement(null);
		playerZero.buildSettlement(null);
		playerZero.buildSettlement(null);
		playerZero.buildSettlement(null);

		////System.out.print("Testing can build settlement with only 1 settlement left to build (Should succeed)");
		//Can I still play one (I have 1 more road left)
		assertTrue(playerZero.canAffordSettlement());
		////System.out.println(" - PASSED");

		////System.out.print("Testing can build settlement after building all 5 settlements (Should fail)");
		//How about if I build one more road? (And I don't have any more in my inventory)
		playerZero.buildSettlement(null);
		assertFalse(playerZero.canAffordSettlement());
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceSettlement()
	{
		////System.out.println("\nCAN PLACE SETTLEMENT TEST:\n");
		//HexLocation hex_10 = new HexLocation(-1,0);
		HexLocation hex_11 = new HexLocation(-1,1);
		//HexLocation hex0_1 = new HexLocation(0,-1);
		HexLocation hex00 = new HexLocation(0,0);
		//HexLocation hex01 = new HexLocation(0,1);
		//HexLocation hex1_1 = new HexLocation(1,-1);
		HexLocation hex10 = new HexLocation(1,0);
		
		////System.out.print("Testing place settlement on an empty board (Should fail)");
		//Can I place a settlement on an empty board?
		assertFalse(playerZero.canPlaceSettlement(new VertexLocation(hex00, VertexDirection.NorthEast)));
		////System.out.println(" - PASSED");

		////System.out.print("Testing place settlement on a board after second round (Should succeed)");
		//Can I build a settlement on a board that is loaded?
		playerZero.buildSettlement(new VertexLocation(hex00, VertexDirection.NorthEast));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.North));
		playerZero.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthWest));
		assertTrue(playerZero.canPlaceSettlement(new VertexLocation(hex_11, VertexDirection.NorthEast)));
		////System.out.println(" - PASSED");

		////System.out.print("Testing place settlement on vertex connected by roads (Should succeed)");
		//Can I build a settlement on a vertex that is connected by roads, but not two away? (This should return true, because the board will check the distances)
		assertTrue(playerZero.canPlaceSettlement(new VertexLocation(hex00, VertexDirection.NorthWest)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing place settlement on vertex not connected by roads (Should fail)");
		//Can I build a settlement on a vertex that is not connected by roads?
		assertFalse(playerZero.canPlaceSettlement(new VertexLocation(hex00, VertexDirection.SouthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing from a different direction) (Should succeed)");
		//Try it on a different hex and direction
		playerZero.buildSettlement(new VertexLocation(hex10, VertexDirection.SouthWest));
		playerZero.buildRoad(new EdgeLocation(hex10, EdgeDirection.South));
		playerZero.buildRoad(new EdgeLocation(hex10, EdgeDirection.SouthEast));
		assertTrue(playerZero.canPlaceSettlement(new VertexLocation(hex10.getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest)));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanAffordCity()
	{
		////System.out.println("\nCAN AFFORD CITY TEST:\n");
		////System.out.print("Testing afford city with no resources (Should fail)");
		//Can I afford a city with no resources?
		assertFalse(playerTwo.canAffordCity());
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing afford city with 2 wheat and 2 ore (Should fail)");
		//Can I afford a city with 2 wheat and 2 ore?
		playerTwo.addResourcesToList(0, 2, 0, 2, 0);
		assertFalse(playerTwo.canAffordCity());
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing afford city with 2 wheat and 3 ore but without a settlement (Should fail)");
		//Can I afford a city with 2 wheat and 3 ore? (but I haven't built a settlement yet)
		playerTwo.addResourcesToList(0,1,0,0,0);
		assertFalse(playerTwo.canAffordCity());
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing build city, having a settlement already (Should succeed)");
		//If a build a settlement, would I THEN be able to build a city?
		playerTwo.addResourcesToList(1,0,1,1,1);
		playerTwo.buildSettlement(null);
		assertTrue(playerTwo.canAffordCity());
		////System.out.println(" - PASSED");

		////System.out.println("Expending all cities...");
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

		////System.out.print("Testing build city with one left to build (Should succeed)");
		//Can I still build a city? (I have one left in my inventory)
		assertTrue(playerZero.canAffordCity());
		////System.out.println(" - PASSED");
		playerZero.buildCity(vertex);
	
		////System.out.print("Testing build city with 4 cities already built (Should fail)");
		//Now can I build one? (I have used them all up)
		assertFalse(playerZero.canAffordCity());
		////System.out.println(" - PASSED");

		////System.out.print("Testing if all settlements were replenished (Should be, after turning them into cities)");
		//Do I still have all of my settlements? (Did they get replaced)
		assertTrue(playerZero.settlementCount() == 5);
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceCity()
	{
		////System.out.println("\nCAN PLACE CITY TEST:\n");
		//HexLocation hex_10 = new HexLocation(-1,0);
		//HexLocation hex_11 = new HexLocation(-1,1);
		//HexLocation hex0_1 = new HexLocation(0,-1);
		HexLocation hex00 = new HexLocation(0,0);
		//HexLocation hex01 = new HexLocation(0,1);
		//HexLocation hex1_1 = new HexLocation(1,-1);
		//HexLocation hex10 = new HexLocation(1,0);
	
		////System.out.print("Testing place city on empty board (Should fail)");
		//Can I place a city on an empty board
		assertFalse(playerThree.canPlaceCity(new VertexLocation(hex00, VertexDirection.SouthWest)));
		////System.out.println(" - PASSED");

		////System.out.print("Testing place city next to roads but not on a settlement (Should fail)");
		//Can I place a city if I have roads attached to it? (but no settlement)
		playerThree.addResourcesToList(100, 100, 100, 100, 100);
		playerThree.buildRoad(new EdgeLocation(hex00, EdgeDirection.NorthWest));
		playerThree.buildRoad(new EdgeLocation(hex00, EdgeDirection.SouthWest));
		assertFalse(playerThree.canPlaceCity(new VertexLocation(hex00, VertexDirection.SouthWest)));
		////System.out.println(" - PASSED");

		////System.out.print("Testing place city next to roads and on a settlement (Should succeed)");
		//Can I place a city if I have roads attached AND a settlement?
		playerThree.buildSettlement(new VertexLocation(hex00, VertexDirection.SouthWest));
		assertTrue(playerThree.canPlaceCity(new VertexLocation(hex00, VertexDirection.SouthWest)));
		////System.out.println(" - PASSED");

		////System.out.print("Testing place city on top of an existing city (Should fail)");
		//Can I place a city if I have a city already there?
		playerThree.buildCity(new VertexLocation(hex00, VertexDirection.SouthWest));
		assertFalse(playerThree.canPlaceCity(new VertexLocation(hex00, VertexDirection.SouthWest)));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void canPlayDevCard()
	{
		////System.out.println("\nCAN PLAY DEV CARD TEST:\n");
		Monument monu = new Monument(); Monopoly monop = new Monopoly(); YearOfPlenty yearOf = new YearOfPlenty();
		Soldier soldier = new Soldier(); RoadBuild rBuilder = new RoadBuild();
		
		monop.setNew(false); yearOf.setNew(false); soldier.setNew(false); rBuilder.setNew(false);
		
		////System.out.println("Adding Monument card...");
		////System.out.println("Adding Monopoly card...");
		////System.out.println("Adding Year of Plenty card...");
		////System.out.println("Adding Soldier card...");
		////System.out.println("Adding Road Builder card...");

		playerZero.addDevCard(monu);
		playerOne.addDevCard(monop);
		playerTwo.addDevCard(yearOf);
		playerThree.addDevCard(soldier);
		playerThree.addDevCard(rBuilder);
		
		//Assert Trues
		////System.out.print("Testing canPlayMonument");
		assertTrue(playerZero.canPlayMonument());
		////System.out.println(" - PASSED");
		////System.out.print("Testing canPlayMonopoly");
		assertTrue(playerOne.canPlayMonopoly());
		////System.out.println(" - PASSED");
		////System.out.print("Testing canPlayYearOfPlenty");
		assertTrue(playerTwo.canPlayYearOfPlenty());
		////System.out.println(" - PASSED");
		////System.out.print("Testing canPlaySoldier");
		assertTrue(playerThree.canPlaySoldier());
		////System.out.println(" - PASSED");
		////System.out.print("Testing canPlayRoadBuilder");
		assertTrue(playerThree.canPlayRoadBuilder());
		////System.out.println(" - PASSED");
		
		//Assert False
		////System.out.print("Testing canPlayRoadBuilder again (Should fail)");		
		assertFalse(playerZero.canPlayRoadBuilder());
		////System.out.println(" - PASSED");
		////System.out.print("Testing canPlaySoldier again (Should fail)");		
		assertFalse(playerOne.canPlaySoldier());
		////System.out.println(" - PASSED");
		////System.out.print("Testing canPlayMonopoly again (Should fail)");		
		assertFalse(playerTwo.canPlayMonopoly());
		////System.out.println(" - PASSED");
		////System.out.print("Testing canPlayMonument again (Should fail)");		
		assertFalse(playerTwo.canPlayMonument());
		////System.out.println(" - PASSED");
		////System.out.print("Testing canPlayYearOfPlenty again (Should fail)");		
		assertFalse(playerThree.canPlayYearOfPlenty());
		////System.out.println(" - PASSED");		
	}
	
	
	
	
	
	@After
	public void tearDown()
	{
		////System.out.println("TESTS PASSED!");
	}
	
	
	
	
}
