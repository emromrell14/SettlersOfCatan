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
		mm.gameModel().board().addHex(new Hex(new HexLocation(0,0), HexType.BRICK, new TokenValue(5)));
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
		////System.out.println("\nTesting canAffordRoad\n");
		p.addResourcesToList(0, 0, 0, 0, 0);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		////System.out.print("Testing without any resources");
		assertFalse(mm.canAffordRoad(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing with some resources, but not enough?");
		p.addResourcesToList(1, 0, 0, 0, 0);
		assertFalse(mm.canAffordRoad(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing with sufficient resources");
		p.addResourcesToList(0, 0, 0, 0, 1);
		assertTrue(mm.canAffordRoad(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing on the first/second round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertTrue(mm.canAffordRoad(p.playerIndex()));
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertTrue(mm.canAffordRoad(p.playerIndex()));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing after building a lot of roads, and only having one left");
		p.addResourcesToList(100,100,100,100,100);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		mm.buildRoad(p.playerIndex(), null);
		assertTrue(mm.canAffordRoad(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing after using up all roads");
		mm.buildRoad(p.playerIndex(), null);
		assertFalse(mm.canAffordRoad(p.playerIndex()));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceRoad()
	{
		////System.out.println("\nTesting canPlaceRoad\n");
		Player p = mm.gameModel().getPlayer(11);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		////System.out.print("Testing placing a road on an empty board");
		assertFalse(mm.canPlaceRoad(p.playerIndex(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.North)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a road during the first round before a settlement is played");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canPlaceRoad(p.playerIndex(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a road during the first round next to a settlement");
		mm.buildSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		assertTrue(mm.canPlaceRoad(p.playerIndex(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a road during the second round next to a settlement");
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertTrue(mm.canPlaceRoad(p.playerIndex(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.North)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a road during the second round NOT next to a settlement");
		assertFalse(mm.canPlaceRoad(p.playerIndex(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.SouthEast)));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a road next to another road");
		mm.buildRoad(p.playerIndex(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.North));
		assertTrue(mm.canPlaceRoad(p.playerIndex(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing place a road on top of another road");
		assertFalse(mm.canPlaceRoad(p.playerIndex(), new EdgeLocation(new HexLocation(0,0), EdgeDirection.North)));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanAffordSettlement()
	{
		////System.out.println("\nTesting canAffordSettlement\n ");
		Player p = mm.gameModel().getPlayer(11);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());

		////System.out.print("Testing with too few resources");
		assertFalse(mm.canAffordSettlement(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing with enough resources");
		p.addResourcesToList(2, 0, 2, 2, 2);
		assertTrue(mm.canAffordSettlement(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing on first/second round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertTrue(mm.canAffordSettlement(p.playerIndex()));
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertTrue(mm.canAffordSettlement(p.playerIndex()));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		////System.out.println(" - PASSED");
		
		////System.out.print("Test with enough resources, but no more settlements");
		p.buildSettlement(null);
		p.buildSettlement(null);
		p.buildSettlement(null);
		p.buildSettlement(null);
		p.buildSettlement(null);
		assertFalse(mm.canAffordSettlement(p.playerIndex()));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceSettlement()
	{
		////System.out.println("\nTesting canPlaceSettlement\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		////System.out.print("Testing placing a settlement on an empty board during the PLAYING phase");
		assertFalse(mm.canPlaceSettlement(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a settlement during the first round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertTrue(mm.canPlaceSettlement(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a settlement during the second round right next to the other settlement");
		mm.buildSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertFalse(mm.canPlaceSettlement(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthWest)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a settlement during the second round NOT next to the other settlement");
		assertTrue(mm.canPlaceSettlement(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.SouthEast)));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a settlement right next to another settlement in the PLAYING phase");
		assertFalse(mm.canPlaceSettlement(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthWest)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing place a settlement on top of another settlement");
		assertFalse(mm.canPlaceSettlement(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanAffordCity()
	{
		////System.out.println("\nTesting canAffordCity\n");

		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		////System.out.print("Test with too few resources ");
		p.addResourcesToList(0, 0, 0, 0, 0);
		assertFalse(mm.canAffordCity(p.playerIndex())); //false cause it has no resources and no settlement to place on
		////System.out.println(" - PASSED");
		
		////System.out.print("Test with no settlements to build on");
		p.addResourcesToList(0, 3, 0, 2, 0);
		assertFalse(mm.canAffordCity(p.playerIndex())); //false cause it has no settlements to replace with city
		////System.out.println(" - PASSED");
		
		////System.out.print("Test on first/second round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canAffordCity(p.playerIndex()));
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		////System.out.println(" - PASSED");
		
		////System.out.print("Test with resources, and settlement to build on");
		p.addResourcesToList(1, 1, 1, 1, 1);
		VertexLocation loc = new VertexLocation(new HexLocation(1,1), VertexDirection.East);
		p.buildSettlement(loc);
		assertTrue(mm.canAffordCity(p.playerIndex()));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanPlaceCity()
	{
		////System.out.println("\nTesting canPlaceCity\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		
		////System.out.print("Testing placing a city on an empty board during the PLAYING phase");
		assertFalse(mm.canPlaceCity(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a city during the first round");
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canPlaceCity(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a city during the second round");
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertFalse(mm.canPlaceCity(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthWest)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a city on top of a settlement during a PLAYING phase");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		mm.buildSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		assertTrue(mm.canPlaceCity(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing placing a city on top of another city");
		mm.buildCity(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		assertFalse(mm.canPlaceSettlement(p.playerIndex(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast)));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanBuyDevCard()
	{
		////System.out.println("\nTesting canBuyDevCard\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		////System.out.print("Test with too few resources");
		p.addResourcesToList(0, 0, 0, 0, 0);
		assertFalse(mm.canBuyDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing with sufficient resources");
		p.addResourcesToList(0, 1, 1, 1, 0);
		assertTrue(mm.canBuyDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing where dev cards ran out");
		mm.gameModel().devCards().clear();
		assertFalse(mm.canBuyDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Test with it being the FIRST ROUND");
		mm.gameModel().devCards().add(new Monopoly());
		p.addResourcesToList(0,1,1,1,0);
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canBuyDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");

		////System.out.print("Test with it being the SECOND ROUND");
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertFalse(mm.canBuyDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Test with the turn status: ROLLING");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canBuyDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Test with the turn status: PLAYING");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canBuyDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
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
		////System.out.println("\nTesting canPlayDevCard\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		Monopoly monop = new Monopoly();
		
		p.addDevCard(monop);
		////System.out.print("Testing canPlayDevCard with card that is status:new -");
		assertFalse(mm.canPlayDevCard(p.playerIndex()));	//false because the card is new
		////System.out.println("Passed");
		monop.setNew(false);
		
		p.addDevCard(monop);
		
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		////System.out.print("Testing playDevCard on Status.Rolling: ");
		assertFalse(mm.canPlayDevCard(p.playerIndex()));
		////System.out.println("Passed");
		
		////System.out.print("Testing canPlayDevCard with card that is status:old -");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayDevCard(p.playerIndex()));	//holds 2 monopoly cards, one new one not. 
		////System.out.println("Passed");
		
		Player p2 = mm.gameModel().getPlayer(11);
		p2.addDevCard(new Monument());
		mm.gameModel().turnTracker().setCurrentTurn(p2.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		////System.out.print("Testing monument cards (dont have to wait a turn to play) on Status.Rolling: ");
		assertFalse(mm.canPlayDevCard(p2.playerIndex()));
		////System.out.println("Passed");
		
		////System.out.print("Testing monument cards (dont have to wait a turn to play): ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayDevCard(p2.playerIndex()));	//true cause monuments dont have to wait a turn to play
		////System.out.println("Passed");

		////System.out.print("Testing canPlayDevCard with it being the FIRST ROUND");
		p.addDevCard(monop);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.FIRSTROUND);
		assertFalse(mm.canPlayDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing canPlayDevCard with it being the SECOND ROUND");
		p.addDevCard(monop);
		mm.gameModel().turnTracker().setStatus(Status.SECONDROUND);
		assertFalse(mm.canPlayDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing canPlayDevCard with the turn status: ROLLING");
		p.addDevCard(monop);
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing canPlayDevCard with the turn status: PLAYING");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayDevCard(p.playerIndex()));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanOfferTrade()
	{
		////System.out.println("\nTesting canOfferTrade\n");
		Player p = mm.gameModel().getPlayer(12);
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		////System.out.print("canOfferTrade with no resource cards: ");
		//assertFalse(mm.canOfferTrade(p.playerIndex()));
		////System.out.println("Passed");
		
		p.addResourcesToList(1, 1, 1, 1, 1);
		////System.out.print("canOfferTrade with only player offering holding resource cards: ");
		//assertFalse(mm.canOfferTrade(p.playerIndex()));
		////System.out.println("Passed");
		
		Player p2 = mm.gameModel().getPlayer(11);
		p2.addResourcesToList(1, 1, 1, 1, 1);
		////System.out.print("canOfferTrade with two players holding resource cards: ");
		assertTrue(mm.canOfferTrade(p.playerIndex()));
		////System.out.println("Passed");
		
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		////System.out.print("canOfferTrade with two players holding resource cards(with Status.Rolling): ");
		assertFalse(mm.canOfferTrade(p.playerIndex()));
		////System.out.println("Passed");
		
		p2.addResourcesToList(1, 1, 1, 1, 1);
		mm.gameModel().turnTracker().setCurrentTurn(p2.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		////System.out.print("canOfferTrade with two players holding resource cards and isn't player offering's turn: ");
		assertFalse(mm.canOfferTrade(p.playerIndex()));
		////System.out.println("Passed");
		
		Player p3 = mm.gameModel().getPlayer(10);
		p3.addResourcesToList(0, 0, 0, 0, 0);
		mm.gameModel().turnTracker().setCurrentTurn(p3.playerIndex());
		////System.out.print("canOfferTrade with offering player having no cards and offered player having cards: ");
		//assertFalse(mm.canOfferTrade(p3.playerIndex()));
		////System.out.println("Passed");
		
	}
	
	@Test
	public void testCanAcceptTrade()
	{
		Player p = mm.gameModel().getPlayer(12);
		Player p2 = mm.gameModel().getPlayer(13);
		Player p3 = mm.gameModel().getPlayer(10);
		p.addResourcesToList(0, 0, 0, 0, 0);
		p2.addResourcesToList(5, 0, 0, 0, 5);
		p3.addResourcesToList(1, 1, 1, 1, 1);
		
		////System.out.println("\nTesting canAcceptTrade\n");
		
		////System.out.print("canAcceptTrade with player offering nothing: ");
//		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
//		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
//		assertFalse(mm.canAcceptTrade(p2.playerIndex(), p.resources()));	//asking for nothing
//		////System.out.println("Passed");
//		
//		////System.out.print("canAcceptTrade with player offering trade for cards offered player doesn't have: ");
//		mm.gameModel().turnTracker().setCurrentTurn(p2.playerIndex());
//		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
//		assertFalse(mm.canAcceptTrade(p.playerIndex(), p2.resources()));	//asking for 5 brick, 5 wood
//		////System.out.println("Passed");
//		
//		////System.out.print("canAcceptTrade with player asking for cards offered player has, but more than he/she has: ");
//		assertFalse(mm.canAcceptTrade(p3.playerIndex(), p2.resources()));	//asking for 5 brick, 5 wood
//		////System.out.println("Passed");
//		
//		////System.out.print("canAcceptTrade with turn being player offered: ");
//		ResourceList tradeOffer = new ResourceList();
//		tradeOffer.updateResourceList(1, 0, 0, 0, 1);
//		assertFalse(mm.canAcceptTrade(p2.playerIndex(), tradeOffer));
//		////System.out.println("Passed");
//		
//		
//		////System.out.print("canAcceptTrade with Status.Rolling: ");
//		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
//		assertFalse(mm.canAcceptTrade(p3.playerIndex(), tradeOffer));
//		////System.out.println("Passed");
//		
//		////System.out.print("canAcceptTrade with player offering cards that fits criteria: ");
//		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
//		assertTrue(mm.canAcceptTrade(p3.playerIndex(), tradeOffer));
//		////System.out.println("Passed");
		
		
	}
	
	@Test
	public void testCanMaritimeTrade()
	{
		////System.out.println("\nTesting canMaritimeTrade\n");
		Player p = mm.gameModel().getPlayer(11);
		Player p2 = mm.gameModel().getPlayer(12);
		
		////System.out.print("Testing when it's not your turn");
		mm.gameModel().turnTracker().setCurrentTurn(p2.playerIndex());
		assertFalse(mm.canMaritimeTrade(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing when it is your turn, but not enough resources");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canMaritimeTrade(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing when you have enough resources for a 4:1");
		p.addResourcesToList(4, 0, 0, 0, 0);
		assertTrue(mm.canMaritimeTrade(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing when you only have 3 like resources, and no port");
		p.addResourcesToList(-1, 0, 0, 0, 0);
		assertFalse(mm.canMaritimeTrade(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing when you only have 3 like resources, and a 3:1 port");
		p.addResourcesToList(1, 0, 1, 1, 1);
		mm.buildSettlement(p.playerID(), new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast));
		//p.settlements().get(0).setPort(new Port(PortType.THREE, new HexLocation(0,0), EdgeDirection.NorthEast));
		//assertTrue(mm.canMaritimeTrade(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing when you only have 2 brick, and a 2:1 sheep port");
		p.addResourcesToList(-1, 0, 0, 0, 0);
		//p.settlements().get(0).port().setResource(PortType.SHEEP);
		//assertFalse(mm.canMaritimeTrade(p.playerIndex()));
		////System.out.println(" - PASSED");
		
		////System.out.print("Testing when you only have 2 brick, and a 2:1 brick port");
		//p.settlements().get(0).port().setResource(PortType.BRICK);
		//assertTrue(mm.canMaritimeTrade(p.playerIndex()));
		////System.out.println(" - PASSED");
	}
	
	@Test
	public void testCanRollDice()
	{
		////System.out.println("Testing testCanRollDice:\n");
		Player p = mm.gameModel().getPlayer(10);
		Player p2 = mm.gameModel().getPlayer(11);
		
		try
		{
			mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		////System.out.print("Testing when it's not the player's turn - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canRollDice(p2.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it's player's turn and is ROLLING status - ");
		mm.gameModel().endTurn();
		assertTrue(mm.canRollDice(p2.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it's player's turn and is not ROLLING status - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canRollDice(p2.playerIndex()));
		////System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanDiscard()
	{
		////System.out.println("Testing testCanDiscard:\n");
		Player p = mm.gameModel().getPlayer(12);
		
		////System.out.print("Testing when player has <= 7 cards - ");
		assertFalse(mm.canDiscard(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has > 7 cards, but not DISCARDING status - ");
		p.addResourcesToList(0, 3, 4, 1, 0);
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canDiscard(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has > 7 cards and in DISCARDING status - ");
		mm.gameModel().turnTracker().setStatus(Status.DISCARDING);
		assertTrue(mm.canDiscard(p.playerIndex()));
		////System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanFinishTurn()
	{
		////System.out.println("Testing testCanFinishTurn\n");
		Player p = mm.gameModel().getPlayer(10);
		Player p2 = mm.gameModel().getPlayer(11);

		try
		{
			mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		////System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canFinishTurn(p2.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when status is PLAYING, but not the player's turn - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canFinishTurn(p2.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when status is PLAYING and is player's turn - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canFinishTurn(p2.playerIndex()));
		////System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlayYearOfPlenty()
	{
		// This will only be called if canPlayDevCard() returns true, which
		// checks that only one dev card is played per turn
		
		////System.out.println("\nTesting testCanPlayYearOfPlenty\n");

		Player p = mm.gameModel().getPlayer(12);
		
		////System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayYearOfPlenty(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlayYearOfPlenty(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when status is PLAYING, but player has no YearOfPlenty - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlayYearOfPlenty(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has new YearOfPlenty - ");
		mm.gameModel().getPlayer(12).addDevCard(new YearOfPlenty());
		assertFalse(mm.canPlayYearOfPlenty(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has old YearOfPlenty - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayYearOfPlenty(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlayYearOfPlenty(p.playerIndex()));
		////System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlaySoldier()
	{
		// This will only be called if canPlayDevCard() returns true, which
		// checks that only one dev card is played per turn
		
		////System.out.println("\nTesting testCanPlaySoldier\n");

		Player p = mm.gameModel().getPlayer(12);
		
		////System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlaySoldier(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlaySoldier(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when status is PLAYING, but player has no Soldier - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlaySoldier(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has new Soldier - ");
		mm.gameModel().getPlayer(12).addDevCard(new Soldier());
		assertFalse(mm.canPlaySoldier(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has old Soldier - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlaySoldier(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlaySoldier(p.playerIndex()));
		////System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlayMonopoly()
	{
		// This will only be called if canPlayDevCard() returns true, which
		// checks that only one dev card is played per turn
		////System.out.println("\nTesting testCanPlayMonopoly\n");

		Player p = mm.gameModel().getPlayer(12);
		
		////System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayMonopoly(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlayMonopoly(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when status is PLAYING, but player has no Monopoly - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlayMonopoly(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has new Monopoly - ");
		mm.gameModel().getPlayer(12).addDevCard(new Monopoly());
		assertFalse(mm.canPlayMonopoly(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has old Monopoly - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayMonopoly(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlayMonopoly(p.playerIndex()));
		////System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlayMonument()
	{
		// This will only be called if canPlayDevCard() returns true, which
		// checks that only one dev card is played per turn
		////System.out.println("\nTesting testCanPlayMonument\n");

		Player p = mm.gameModel().getPlayer(12);
		
		////System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayMonument(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlayMonument(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when status is PLAYING, but player has no Monument - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlayMonument(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has new Monument - ");
		mm.gameModel().getPlayer(12).addDevCard(new Monument());
		assertTrue(mm.canPlayMonument(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlayMonument(p.playerIndex()));
		////System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlaceRobber()
	{
		////System.out.println("Testing testCanPlaceRobber\n");
		mm.gameModel().setRobber(new Robber(new HexLocation(0,0)));
		
		////System.out.print("Testing when not during ROBBING status - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlaceRobber(new HexLocation(1,0)));
		////System.out.println("PASSED");
		
		////System.out.print("Testing during ROBBING status and when Robber is not placed at same location - ");
		mm.gameModel().turnTracker().setStatus(Status.ROBBING);
		assertTrue(mm.canPlaceRobber(new HexLocation(1,0)));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when Robber is placed at same location - ");
		assertFalse(mm.canPlaceRobber(new HexLocation(0,0)));
		////System.out.println("PASSED\n");
	}
	
	@Test
	public void testCanPlayRoadBuilder()
	{
		// This will only be called if canPlayDevCard() returns true, which
		// checks that only one dev card is played per turn
		////System.out.println("\nTesting testCanPlayRoadBuilder\n");

		Player p = mm.gameModel().getPlayer(12);
		
		////System.out.print("Testing when status is not PLAYING - ");
		mm.gameModel().turnTracker().setStatus(Status.ROLLING);
		assertFalse(mm.canPlayRoadBuilder(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is player's turn, but not in PLAYING status - ");
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		assertFalse(mm.canPlayRoadBuilder(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when status is PLAYING, but player has no RoadBuilder - ");
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertFalse(mm.canPlayRoadBuilder(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has new RoadBuilder - ");
		mm.gameModel().getPlayer(12).addDevCard(new RoadBuild());
		assertFalse(mm.canPlayRoadBuilder(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when player has old RoadBuilder - ");
		mm.gameModel().endTurn();
		mm.gameModel().turnTracker().setCurrentTurn(p.playerIndex());
		mm.gameModel().turnTracker().setStatus(Status.PLAYING);
		assertTrue(mm.canPlayRoadBuilder(p.playerIndex()));
		////System.out.println("PASSED");
		
		////System.out.print("Testing when it is not player's turn - ");
		mm.gameModel().turnTracker().endTurn();
		assertFalse(mm.canPlayRoadBuilder(p.playerIndex()));
		////System.out.println("PASSED\n");
	}
	
	@After
	public void tearDown()
	{
		return;
	}
}
