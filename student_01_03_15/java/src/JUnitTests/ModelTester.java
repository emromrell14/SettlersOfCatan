package JUnitTests;

import models.*;
import facade.*;
import shared.definitions.*;
import shared.locations.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTester 
{
	private ModelManager mm;

	@Before
	public void initialize() 
	{
		mm = new ModelManager();
		mm.updateModel(new Game());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
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
		Player p = mm.gameModel().getPlayer(10);
		System.out.println("\nTesting canAffordRoad\n");
		p.addResourcesToList(0, 0, 0, 0, 0);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		System.out.print("Testing without any resources");
		assertFalse(mm.canAffordRoad(10));
		System.out.println(" - PASSED");
		
		System.out.print("Testing with some resources, but not enough?");
		p.addResourcesToList(1, 0, 0, 0, 0);
		assertFalse(mm.canAffordRoad(10));
		System.out.println(" - PASSED");
		
		System.out.print("Testing with sufficient resources");
		p.addResourcesToList(0, 0, 0, 0, 1);
		assertTrue(mm.canAffordRoad(10));
		System.out.println(" - PASSED");
		
		System.out.print("Testing on the first/second round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertTrue(mm.canAffordRoad(10));
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertTrue(mm.canAffordRoad(10));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		System.out.println(" - PASSED");
		
		System.out.print("Testing after building a lot of roads, and only having one left");
		p.addResourcesToList(100,100,100,100,100);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		mm.buildRoad(10, null);
		assertTrue(mm.canAffordRoad(10));
		System.out.println(" - PASSED");
		
		System.out.print("Testing after using up all roads");
		mm.buildRoad(10, null);
		assertFalse(mm.canAffordRoad(10));
		System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceRoad()
	{
		System.out.println("\nTesting canPlaceRoad\n");
		Player p = mm.gameModel().getPlayer(11);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		System.out.print("Testing placing a road on an empty board");
		assertFalse(mm.canPlaceRoad(p.playerID(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.North)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a road during the first round before a settlement is played");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canPlaceRoad(p.playerID(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a road during the first round next to a settlement");
		mm.buildSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		assertTrue(mm.canPlaceRoad(p.playerID(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a road during the second round next to a settlement");
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertTrue(mm.canPlaceRoad(p.playerID(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.North)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a road during the second round NOT next to a settlement");
		assertFalse(mm.canPlaceRoad(p.playerID(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.SouthEast)));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a road next to another road");
		mm.buildRoad(p.playerID(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.North));
		assertTrue(mm.canPlaceRoad(p.playerID(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing place a road on top of another road");
		assertFalse(mm.canPlaceRoad(p.playerID(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.North)));
		System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanAffordSettlement()
	{
		System.out.println("\nTesting canAffordSettlement\n ");
		Player p = mm.gameModel().getPlayer(11);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());

		System.out.print("Testing with too few resources");
		assertFalse(mm.canAffordSettlement(11));
		System.out.println(" - PASSED");
		
		System.out.print("Testing with enough resources");
		p.addResourcesToList(2, 0, 2, 2, 2);
		assertTrue(mm.canAffordSettlement(11));
		System.out.println(" - PASSED");
		
		System.out.print("Testing on first/second round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertTrue(mm.canAffordSettlement(11));
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertTrue(mm.canAffordSettlement(11));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		System.out.println(" - PASSED");
		
		System.out.print("Test with enough resources, but no more settlements");
		p.buildSettlement(null);
		p.buildSettlement(null);
		p.buildSettlement(null);
		p.buildSettlement(null);
		p.buildSettlement(null);
		assertFalse(mm.canAffordSettlement(11));
		System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceSettlement()
	{
		System.out.println("\nTesting canPlaceSettlement\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		System.out.print("Testing placing a settlement on an empty board during the PLAYING phase");
		assertFalse(mm.canPlaceSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a settlement during the first round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertTrue(mm.canPlaceSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a settlement during the second round right next to the other settlement");
		mm.buildSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertFalse(mm.canPlaceSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthWest)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a settlement during the second round NOT next to the other settlement");
		assertTrue(mm.canPlaceSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.SouthEast)));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a settlement right next to another settlement in the PLAYING phase");
		assertFalse(mm.canPlaceSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthWest)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing place a settlement on top of another settlement");
		assertFalse(mm.canPlaceSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanAffordCity()
	{
		System.out.println("\nTesting canAffordCity:\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		System.out.print("Test with too few resources ");
		p.addResourcesToList(0, 0, 0, 0, 0);
		assertFalse(mm.canAffordCity(12)); //false cause it has no resources and no settlement to place on
		System.out.println(" - PASSED");
		
		System.out.print("Test with no settlements to build on");
		p.addResourcesToList(0, 3, 0, 2, 0);
		assertFalse(mm.canAffordCity(12)); //false cause it has no settlements to replace with city
		System.out.println(" - PASSED");
		
		System.out.print("Test on first/second round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canAffordCity(12));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		System.out.println(" - PASSED");
		
		System.out.print("Test with resources, and settlement to build on");
		p.addResourcesToList(1, 1, 1, 1, 1);
		VertexLocation loc = new VertexLocation(new HexLocation(1,1), VertexDirection.East);
		p.buildSettlement(loc);
		assertTrue(mm.canAffordCity(12));
		System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceCity()
	{
		System.out.println("\nTesting canPlaceCity\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		System.out.print("Testing placing a city on an empty board during the PLAYING phase");
		assertFalse(mm.canPlaceCity(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a city during the first round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canPlaceCity(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a city during the second round");
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertFalse(mm.canPlaceCity(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthWest)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a city on top of a settlement during a PLAYING phase");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		mm.buildSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		assertTrue(mm.canPlaceCity(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		System.out.println(" - PASSED");
		
		System.out.print("Testing placing a city on top of another city");
		mm.buildCity(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		assertFalse(mm.canPlaceSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanBuyDevCard()
	{
		System.out.println("\nTesting canBuyDevCard\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		System.out.print("Test with too few resources");
		p.addResourcesToList(0, 0, 0, 0, 0);
		assertFalse(mm.canBuyDevCard(12));
		System.out.println(" - PASSED");
		
		System.out.print("Testing with sufficient resources");
		p.addResourcesToList(0, 1, 1, 1, 0);
		assertTrue(mm.canBuyDevCard(12));
		System.out.println(" - PASSED");
		
		System.out.print("Testing where dev cards ran out");
		mm.gameModel().devCards().clear();
		assertFalse(mm.canBuyDevCard(12));
		System.out.println(" - PASSED");
		
		System.out.print("Test with it being the FIRST ROUND");
		mm.gameModel().devCards().add(new Monopoly());
		p.addResourcesToList(0,1,1,1,0);
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canBuyDevCard(12));
		System.out.println(" - PASSED");

		System.out.print("Test with it being the SECOND ROUND");
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertFalse(mm.canBuyDevCard(12));
		System.out.println(" - PASSED");
		
		System.out.print("Test with the turn status: ROLLING");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canBuyDevCard(12));
		System.out.println(" - PASSED");
		
		System.out.print("Test with the turn status: PLAYING");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canBuyDevCard(12));
		System.out.println(" - PASSED");
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
		System.out.println("\nTesting canPlayDevCard\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		Monopoly monop = new Monopoly();
		
		p.addDevCard(monop);
		System.out.print("Testing canPlayDevCard with card that is status:new -");
		assertFalse(mm.canPlayDevCard(12));	//false because the card is new
		System.out.println("Passed");
		monop.setNew(false);
		
		p.addDevCard(monop);
		System.out.print("Testing canPlayDevCard with card that is status:old -");
		assertTrue(mm.canPlayDevCard(12));	//holds 2 monopoly cards, one new one not. 
		System.out.println("Passed");
		
		System.out.print("Testing canPlayDevCard with it being the FIRST ROUND");
		p.addDevCard(monop);
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canPlayDevCard(12));
		System.out.println(" - PASSED");
		
		System.out.print("Testing canPlayDevCard with it being the SECOND ROUND");
		p.addDevCard(monop);
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertFalse(mm.canPlayDevCard(12));
		System.out.println(" - PASSED");
		
		System.out.print("Testing canPlayDevCard with the turn status: ROLLING");
		p.addDevCard(monop);
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayDevCard(12));
		System.out.println(" - PASSED");
		
		System.out.print("Testing canPlayDevCard with the turn status: PLAYING");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayDevCard(12));
		System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanOfferTrade()
	{
		System.out.println("\nTesting canOfferTrade\n");
		Player p = mm.gameModel().getPlayer(12);
		assertFalse(mm.canOfferTrade(p.playerID()));
		
		
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
		System.out.println("Testing testCanFinishTurn\n");

		try
		{
			mm.gameModel().turnTracker().setCurrentTurn(new Index(0));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canFinishTurn(11));
		System.out.println("PASSED");
		
		System.out.print("Testing when status is PLAYING, but not the player's turn - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canFinishTurn(11));
		System.out.println("PASSED");
		
		System.out.print("Testing when status is PLAYING and is player's turn - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canFinishTurn(11));
		System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlayYearOfPlenty()
	{
		System.out.println("\nTesting testCanPlayYearOfPlenty:\n");
		Player p = mm.gameModel().getPlayer(12);
		
		System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayYearOfPlenty(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlayYearOfPlenty(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when status is PLAYING, but player has no YearOfPlenty - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlayYearOfPlenty(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when player has new YearOfPlenty - ");
		mm.gameModel().getPlayer(12).addDevCard(new YearOfPlenty());
		assertFalse(mm.canPlayYearOfPlenty(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when player has old YearOfPlenty - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayYearOfPlenty(12));
		System.out.println("PASSED");
		
		System.out.print("Testing whe it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlayYearOfPlenty(12));
		System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlaySoldier()
	{
		System.out.println("\nTesting testCanPlaySoldier:\n");
		Player p = mm.gameModel().getPlayer(12);
		
		System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlaySoldier(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlaySoldier(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when status is PLAYING, but player has no Soldier - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlaySoldier(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when player has new Soldier - ");
		mm.gameModel().getPlayer(12).addDevCard(new Soldier());
		assertFalse(mm.canPlaySoldier(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when player has old Soldier - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlaySoldier(12));
		System.out.println("PASSED");
		
		System.out.print("Testing whe it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlaySoldier(12));
		System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlayMonopoly()
	{
		System.out.println("\nTesting testCanPlayMonopoly:\n");
		Player p = mm.gameModel().getPlayer(12);
		
		System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayMonopoly(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlayMonopoly(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when status is PLAYING, but player has no Monopoly - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlayMonopoly(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when player has new Monopoly - ");
		mm.gameModel().getPlayer(12).addDevCard(new Monopoly());
		assertFalse(mm.canPlayMonopoly(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when player has old Monopoly - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayMonopoly(12));
		System.out.println("PASSED");
		
		System.out.print("Testing whe it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlayMonopoly(12));
		System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlayMonument()
	{
		System.out.println("\nTesting testCanPlayMonument:\n");
		Player p = mm.gameModel().getPlayer(12);
		
		System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayMonument(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlayMonument(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when status is PLAYING, but player has no Monument - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlayMonument(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when player has new Monument - ");
		mm.gameModel().getPlayer(12).addDevCard(new Monument());
		assertTrue(mm.canPlayMonument(12));
		System.out.println("PASSED");
		
		System.out.print("Testing whe it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlayMonument(12));
		System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlaceRobber()
	{
		System.out.println("Testing testCanPlaceRobber\n");
		mm.gameModel().setRobber(new Robber(new HexLocation(0,0)));
		
		System.out.print("Testing when not during ROBBING status - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlaceRobber(new HexLocation(1,0)));
		System.out.println("PASSED");
		
		System.out.print("Testing during ROBBING status and when Robber is not placed at same location - ");
		mm.gameModel().turnTracker().setStatus(Status.ROBBING);
		assertTrue(mm.canPlaceRobber(new HexLocation(1,0)));
		System.out.println("PASSED");
		
		System.out.print("Testing when Robber is placed at same location - ");
		assertFalse(mm.canPlaceRobber(new HexLocation(0,0)));
		System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlayRoadBuilder()
	{
		System.out.println("\nTesting testCanPlayRoadBuilder:\n");
		Player p = mm.gameModel().getPlayer(12);
		
		System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayRoadBuilder(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlayRoadBuilder(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when status is PLAYING, but player has no RoadBuilder - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlayRoadBuilder(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when player has new RoadBuilder - ");
		mm.gameModel().getPlayer(12).addDevCard(new RoadBuild());
		assertFalse(mm.canPlayRoadBuilder(12));
		System.out.println("PASSED");
		
		System.out.print("Testing when player has old RoadBuilder - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayRoadBuilder(12));
		System.out.println("PASSED");
		
		System.out.print("Testing whe it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlayRoadBuilder(12));
		System.out.println("PASSED\n");
	}
	
	@After
	public void tearDown()
	{
		return;
	}
}
