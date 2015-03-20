package JUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import models.*;

import org.junit.Test;

import server.User;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import JSONmodels.*;

public class SerializeJSONTester {

	@Test
	public void test1() {
		//ClientModel Test 1
		
		try
		{
			IGame game = new Game();
					
			ResourceList bank = new ResourceList();
			bank.addBrick(20);
			bank.addOre(15);
			bank.addSheep(10);
			bank.addWheat(18);
			bank.addWood(9000);
			
			Board board = new Board();
			board.addCity(new Building(new Index(3), new VertexLocation(new HexLocation(2,-1), VertexDirection.East)));
			board.addSettlement(new Building(new Index(3), new VertexLocation(new HexLocation(-1,3), VertexDirection.NorthWest)));
			board.addRoad(new Road(new Index(3), new EdgeLocation(new HexLocation(2,-1), EdgeDirection.NorthWest)));
			board.addPort(new Port(PortType.THREE, new HexLocation(0,1), EdgeDirection.NorthEast));
			board.addHex(new Hex(new HexLocation(3,-2), HexType.ORE, new TokenValue(12)));
			board.addHex(new Hex(new HexLocation(-1,2), HexType.SHEEP, new TokenValue(7)));
			
			List<Message> messageList = new ArrayList<Message>();
			messageList.add(new Message("You can shut up!", "Eric"));
			messageList.add(new Message("Buckwheat!", "Eric"));
			messageList.add(new Message("Ok", "Paul"));
			
			List<Message> logList = new ArrayList<Message>();
			logList.add(new Message("Paul built a road", "Paul"));
			logList.add(new Message("Eric won the game", "Eric"));
			
			TurnTracker turnTracker = new TurnTracker();
			turnTracker.setCurrentTurn(new Index(2));
			turnTracker.setLargestArmy(new Index(3));
			turnTracker.setLongestRoad(new Index(2));
			turnTracker.setStatus(Status.ROBBING);
			
			List<DevCard> devCards = new ArrayList<DevCard>();
			devCards.add(new Monopoly());
			devCards.add(new Soldier());
			
			ResourceList offer = new ResourceList(3,-4,2,-1,5);
			
			Trade tradeObject = new Trade(new Index(0), new Index(1), offer);
			
			List<DevCard> playerDevCards = new ArrayList<DevCard>();
			YearOfPlenty yop1 = new YearOfPlenty();
			yop1.setNew(true);
			YearOfPlenty yop2 = new YearOfPlenty();
			yop2.setNew(true);
			Monument monument = new Monument();
			monument.setNew(false);
			
			playerDevCards.add(yop1);
			playerDevCards.add(yop2);
			playerDevCards.add(monument);
			
			Index index = new Index(2);
			int id = 4;
			
			int soldiers = 3;
			int victoryPoints = 7;
			int monuments = 2;
			int numSettlements = 4;
			int numCities = 1;
			int numRoads = 8;
			boolean playedDevCard = true;
			
			ResourceList playerResources = new ResourceList();
			playerResources.addBrick(4);
			playerResources.addOre(2);
			playerResources.addSheep(5);
			playerResources.addWheat(0);
			playerResources.addWood(1);
			
			Player p = new Player();
			
			p.setColor(CatanColor.GREEN);
			p.setName("Paul");
			p.setPlayerIndex(index);	
			p.setPlayerID(id);
			p.setResources(playerResources);
			p.setRoadCount(numRoads);
			p.setSettlementCount(numSettlements);
			p.setCityCount(numCities);
			p.setSoldierCount(soldiers);
			p.setVictoryPointCount(victoryPoints);
			p.setHasPlayedDevCard(playedDevCard);
			p.setHasDiscarded(false);
			p.setMonuments(monuments);
			p.setUser(new User(id));
			p.setDevCards(playerDevCards);
			
			game.addPlayer(p);
			game.setBank(bank);
			game.setBoard(board);
			game.setId(8);
			game.setChat(messageList);
			game.setLog(logList);
			game.setName("testing game");
			game.setRobber(new Robber(new HexLocation(0,1)));
			game.setVersion(555);
			game.setWinner(3);
			game.setTurnTracker(turnTracker);
			game.setDevCards(devCards);
			game.setTrade(tradeObject);
			
			
			ClientModelJSON model = new ClientModelJSON(game);
			String actualJson = model.toJSON();
			
			String expectedJson = "{\"bank\":{\"brick\":20,\"ore\":15,\"sheep\":10,\"wheat\":18,\"wood\":9000},"+
			"\"chat\":{\"lines\":[{\"message\":\"You can shut up!\",\"source\":\"Eric\"},{\"message\":\"Buckwheat!\",\"source\":\"Eric\"},{\"message\":\"Ok\",\"source\":\"Paul\"}]},"+
					"\"log\":{\"lines\":[{\"message\":\"Paul built a road\",\"source\":\"Paul\"},{\"message\":\"Eric won the game\",\"source\":\"Eric\"}]},"
					+ "\"map\":{\"hexes\":[{\"location\":{\"x\":3,\"y\":-2},\"resource\":\"ore\",\"number\":12},{\"location\":{\"x\":-1,\"y\":2},\"resource\":\"sheep\",\"number\":7}],"
					+ "\"ports\":[{\"resource\":\"three\",\"location\":{\"x\":0,\"y\":1},\"direction\":\"NE\",\"ratio\":3}],"
					+ "\"roads\":[{\"owner\":3,\"location\":{\"x\":2,\"y\":-1,\"direction\":\"NW\"}}],"
					+ "\"settlements\":[{\"owner\":3,\"location\":{\"x\":-1,\"y\":3,\"direction\":\"NW\"}}],"
					+ "\"cities\":[{\"owner\":3,\"location\":{\"x\":2,\"y\":-1,\"direction\":\"E\"}}],"
					+ "\"radius\":3,"
					+ "\"robber\":{\"x\":0,\"y\":1}},"
					+ "\"players\":[{\"color\":\"green\",\"discarded\":false,\"monuments\":2,\"name\":\"Paul\","
					+ 		"\"newDevCards\":{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":0,\"yearOfPlenty\":2},"
					+ 		"\"oldDevCards\":{\"monopoly\":0,\"monument\":1,\"roadBuilding\":0,\"soldier\":0,\"yearOfPlenty\":0},"
					+ 		"\"playerIndex\":2,\"playedDevCard\":true,\"playerID\":4,"
					+ 		"\"resources\":{\"brick\":4,\"ore\":2,\"sheep\":5,\"wheat\":0,\"wood\":1},"
					+ 		"\"roads\":8,\"settlements\":4,\"cities\":1,"
					+ 		"\"soldiers\":3,\"victoryPoints\":7}],"
					+ "\"tradeOffer\":{\"sender\":0,\"receiver\":1,\"offer\":{\"brick\":3,\"ore\":-4,\"sheep\":2,\"wheat\":-1,\"wood\":5}},"
					+ "\"turnTracker\":{\"currentTurn\":2,\"status\":\"ROBBING\",\"longestRoad\":2,\"largestArmy\":3},\"version\":555,\"winner\":3}";
			System.out.println(expectedJson);
			System.out.println(actualJson);
			
			
			assertTrue(actualJson.equals(expectedJson));
			

		}
		catch (Exception e)
		{
			System.out.println(e);
			System.out.println("there may have been a bad index in test 1");
			// Bad Index
		}
		
		/*	
		String JSONClientModel = "{\"bank\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"chat\":{\"lines\":[{\"message\":\"string\",\"source\":\"string\"}]},\"log\":{\"lines\":[{\"message\":\"string\",\"source\":\"string\"}]},\"map\":{\"hexes\":[{\"location\":{\"x\":0,\"y\":0},\"resource\":\"wood\",\"number\":0}],\"ports\":[{\"resource\":\"brick\",\"location\":{\"x\":0,\"y\":0},\"direction\":\"SE\",\"ratio\":2}],\"roads\":[{\"owner\":1,\"location\":{\"x\":0,\"y\":0,\"direction\":\"S\"}}],\"settlements\":[{\"owner\":1,\"location\":{\"x\":0,\"y\":0,\"direction\":\"SW\"}}],\"cities\":[{\"owner\":2,\"location\":{\"x\":0,\"y\":0,\"direction\":\"NE\"}}],\"radius\":5,\"robber\":{\"x\":0,\"y\":0}},\"players\":[{\"color\":\"blue\",\"discarded\":true,\"monuments\":4,\"name\":\"Eric\",\"newDevCards\":{\"monopoly\":1,\"monument\":0,\"roadBuilding\":0,\"soldier\":2,\"yearOfPlenty\":0},\"oldDevCards\":{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":3,\"yearOfPlenty\":0},\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"roads\":2,\"settlements\":0,\"cities\":3,\"soldiers\":0,\"victoryPoints\":0}],\"tradeOffer\":{\"sender\":0,\"receiver\":0,\"offer\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0}},\"turnTracker\":{\"currentTurn\":2,\"status\":\"PLAYING\",\"longestRoad\":3,\"largestArmy\":2},\"version\":644,\"winner\":-1}";
		ClientModelJSON clientModel = ClientModelJSON.fromJSON(JSONClientModel);
		assertTrue(clientModel.getMap().getCities()[0].getLocation().getDirection().equals("NE"));
		assertTrue(clientModel.getPlayers()[0].getName().equals("Eric"));
		assertTrue(clientModel.toJSON().equals(JSONClientModel));		
		
		
		
		
		//DevCardList
		String JSONDevCardList = "{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":2,\"yearOfPlenty\":0}";
		DevCardListJSON devCardList = DevCardListJSON.fromJSON(JSONDevCardList);
		assertTrue(devCardList.getMonument() == 0);
		assertTrue(devCardList.getSoldier() == 2);
		assertTrue(devCardList.toJSON().equals(JSONDevCardList));
		
		//EdgeLocation
		String JSONEdgeLocation = "{\"x\":360,\"y\":55,\"direction\":\"N\"}";
		EdgeLocationJSON edgeLocation = EdgeLocationJSON.fromJSON(JSONEdgeLocation);
		assertTrue(edgeLocation.getX() == 360);
		assertTrue(edgeLocation.getDirection().equals("N"));
		assertTrue(edgeLocation.toJSON().equals(JSONEdgeLocation));
		
		//EdgeValue
		String JSONEdgeValue = "{\"owner\":1,\"location\":{\"x\":0,\"y\":0,\"direction\":\"NW\"}}";
		EdgeValueJSON edgeValue = EdgeValueJSON.fromJSON(JSONEdgeValue);
		assertTrue(edgeValue.getOwner() == 1);
		assertTrue(edgeValue.getLocation().getX() == 0);
		assertTrue(edgeValue.getLocation().getDirection().equals("NW"));
		assertTrue(edgeValue.toJSON().equals(JSONEdgeValue));
		
		//Hex
		String JSONHex = "{\"location\":{\"x\":20,\"y\":1},\"resource\":\"wood\",\"number\":8}";
		HexJSON hex = HexJSON.fromJSON(JSONHex);
		assertTrue(hex.getNumber() == 8);
		assertTrue(hex.getLocation().getX() == 20);
		assertTrue(hex.toJSON().equals(JSONHex));
		
		//Map
		String JSONMap = "{\"hexes\":[{\"location\":{\"x\":0,\"y\":0},\"resource\":\"string\",\"number\":0}],\"ports\":[{\"resource\":\"string\",\"location\":{\"x\":0,\"y\":0},\"direction\":\"string\",\"ratio\":0}],\"roads\":[{\"owner\":2,\"location\":{\"x\":0,\"y\":0,\"direction\":\"string\"}}],\"settlements\":[{\"owner\":3,\"location\":{\"x\":0,\"y\":0,\"direction\":\"string\"}}],\"cities\":[{\"owner\":2,\"location\":{\"x\":0,\"y\":0,\"direction\":\"string\"}}],\"radius\":50,\"robber\":{\"x\":0,\"y\":0}}";
		MapJSON map = MapJSON.fromJSON(JSONMap);
		assertTrue(map.getRadius() == 50);
		assertTrue(map.getCities()[0].getOwner() == 2);
		assertTrue(map.toJSON().equals(JSONMap));		
		
		//MessageLine
		String JSONMessageLine = "{\"message\":\"message string\",\"source\":\"wikipedia\"}";
		MessageLineJSON messageLine = MessageLineJSON.fromJSON(JSONMessageLine);
		assertTrue(messageLine.getMessage().equals("message string"));
		assertTrue(messageLine.getSource().equals("wikipedia"));
		assertTrue(messageLine.toJSON().equals(JSONMessageLine));
		
		//MessageList
		String JSONMessageList = "{\"lines\":[{\"message\":\"new message\",\"source\":\"string\"},{\"message\":\"string2\",\"source\":\"string2\"}]}";
		MessageListJSON messageList = MessageListJSON.fromJSON(JSONMessageList);
		assertTrue(messageList.getLines().length == 2);
		assertFalse(messageList.getLines().length == 3);
		assertTrue(messageList.getLines()[0].getMessage().equals("new message"));
		assertTrue(messageList.toJSON().equals(JSONMessageList));
		
		//Player
		String JSONPlayer = "{\"color\":\"red\",\"discarded\":true,\"monuments\":2,\"name\":\"string\",\"newDevCards\":{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":0,\"yearOfPlenty\":0},\"oldDevCards\":{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":0,\"yearOfPlenty\":0},\"playerIndex\":2,\"playedDevCard\":true,\"playerID\":1325,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"roads\":0,\"settlements\":0,\"cities\":3,\"soldiers\":0,\"victoryPoints\":0}";
		PlayerJSON player = PlayerJSON.fromJSON(JSONPlayer);
		assertTrue(player.getColor().equals("red"));
		assertTrue(player.hasDiscarded());
		assertTrue(player.toJSON().equals(JSONPlayer));		
		
		//Port
		String JSONPort = "{\"resource\":\"brick\",\"location\":{\"x\":0,\"y\":0},\"direction\":\"NE\",\"ratio\":2}";
		PortJSON port = PortJSON.fromJSON(JSONPort);
		assertTrue(port.getResource().equals("brick"));
		assertTrue(port.getDirection().equals("NE"));
		assertTrue(port.toJSON().equals(JSONPort));
				
		//ResourceList
		String JSONResourceList = "{\"brick\":1,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":2}";
		ResourceListJSON resourceList = ResourceListJSON.fromJSON(JSONResourceList);
		assertTrue(resourceList.getBrick() == 1);
		assertTrue(resourceList.getWood() == 2);
		assertTrue(resourceList.toJSON().equals(JSONResourceList));
		
		//TradeOffer
		String JSONTradeOffer = "{\"sender\":2,\"receiver\":3,\"offer\":{\"brick\":1,\"ore\":1,\"sheep\":1,\"wheat\":1,\"wood\":1}}";
		TradeOfferJSON tradeOffer = TradeOfferJSON.fromJSON(JSONTradeOffer);
		assertTrue(tradeOffer.getSender() == 2);
		assertTrue(tradeOffer.getOffer().getBrick() == 1);
		assertTrue(tradeOffer.toJSON().equals(JSONTradeOffer));		
		
		//TurnTracker
		String JSONTurnTracker = "{\"currentTurn\":2,\"status\":\"ROLLING\",\"longestRoad\":-1,\"largestArmy\":1}";
		TurnTrackerJSON turnTracker = TurnTrackerJSON.fromJSON(JSONTurnTracker);
		assertTrue(turnTracker.getCurrentTurn() == 2);
		assertTrue(turnTracker.getLongestRoad() == -1);
		assertTrue(turnTracker.toJSON().equals(JSONTurnTracker));
		
		//VertexObject
		String JSONVertexObject = "{\"owner\":2,\"location\":{\"x\":0,\"y\":0,\"direction\":\"SE\"}}";
		VertexObjectJSON vertexObject = VertexObjectJSON.fromJSON(JSONVertexObject);
		assertTrue(vertexObject.getOwner() == 2);
		assertTrue(vertexObject.getLocation().getY() == 0);
		assertTrue(vertexObject.toJSON().equals(JSONVertexObject));
		*/
	}
	
	@Test
	public void test2() {
		//ClientModel Test 2
		
		try
		{
			IGame game = new Game();
			
			ResourceList bank = new ResourceList();
			bank.addBrick(353);
			bank.addOre(10);
			bank.addSheep(22);
			bank.addWheat(33);
			bank.addWood(72);
			
			Board board = new Board();
			board.addCity(new Building(new Index(1), new VertexLocation(new HexLocation(3,-1), VertexDirection.NorthWest)));
			board.addCity(new Building(new Index(2), new VertexLocation(new HexLocation(-2,1), VertexDirection.SouthEast)));
			board.addSettlement(new Building(new Index(0), new VertexLocation(new HexLocation(1,2), VertexDirection.SouthWest)));
			board.addSettlement(new Building(new Index(0), new VertexLocation(new HexLocation(1,-3), VertexDirection.East)));
			board.addRoad(new Road(new Index(0), new EdgeLocation(new HexLocation(0,0), EdgeDirection.South)));
			board.addPort(new Port(PortType.WHEAT, new HexLocation(-2,1), EdgeDirection.South));
			board.addHex(new Hex(new HexLocation(3,-2), HexType.DESERT, new TokenValue(7)));
			board.addHex(new Hex(new HexLocation(-1,2), HexType.BRICK, new TokenValue(1)));
			
			List<Message> messageList = new ArrayList<Message>();
			messageList.add(new Message("", "Eric"));
			messageList.add(new Message("A", "Cannon"));
			messageList.add(new Message("B", "Michael"));
			
			List<Message> logList = new ArrayList<Message>();
			logList.add(new Message("Michael bought a dev card", "Michael"));
			logList.add(new Message("Eric won the game", "Eric"));
			
			TurnTracker turnTracker = new TurnTracker();
			turnTracker.setCurrentTurn(new Index(3));
			turnTracker.setLargestArmy(new Index(2));
			turnTracker.setLongestRoad(new Index(1));
			turnTracker.setStatus(Status.DISCARDING);
			
			List<DevCard> devCards = new ArrayList<DevCard>();
			devCards.add(new YearOfPlenty());
			devCards.add(new RoadBuild());
			
			ResourceList offer = new ResourceList(-1,25,21,20,43);
			
			Trade tradeObject = new Trade(new Index(2), new Index(3), offer);
			
			List<DevCard> playerDevCards = new ArrayList<DevCard>();
			Monopoly monopoly = new Monopoly();
			monopoly.setNew(false);
			Soldier soldier = new Soldier();
			soldier.setNew(true);

			
			playerDevCards.add(monopoly);
			playerDevCards.add(soldier);
			
			Index index = new Index(1);
			int id = 4444;
			
			int soldiers = 55;
			int victoryPoints = 11;
			int monuments = 0;
			int numSettlements = 2;
			int numCities = 4;
			int numRoads = 1;
			boolean playedDevCard = false;
			
			ResourceList playerResources = new ResourceList();
			playerResources.addBrick(2);
			playerResources.addOre(3);
			playerResources.addSheep(4);
			playerResources.addWheat(5);
			playerResources.addWood(6);
			
			Player p = new Player();
			
			p.setColor(CatanColor.PUCE);
			p.setName("Eric");
			p.setPlayerIndex(index);	
			p.setPlayerID(id);
			p.setResources(playerResources);
			p.setRoadCount(numRoads);
			p.setSettlementCount(numSettlements);
			p.setCityCount(numCities);
			p.setSoldierCount(soldiers);
			p.setVictoryPointCount(victoryPoints);
			p.setHasPlayedDevCard(playedDevCard);
			p.setHasDiscarded(true);
			p.setMonuments(monuments);
			p.setUser(new User(id));
			p.setDevCards(playerDevCards);
			
			//game.addPlayer(p);
			game.setBank(bank);
			game.setBoard(board);
			game.setId(63);
			game.setChat(messageList);
			game.setLog(logList);
			game.setName("testing game 2");
			game.setRobber(new Robber(new HexLocation(3,3)));
			game.setVersion(2000);
			game.setWinner(1);
			game.setTurnTracker(turnTracker);
			game.setDevCards(devCards);
			game.setTrade(tradeObject);
			
			
			ClientModelJSON model = new ClientModelJSON(game);
			String actualJson = model.toJSON();
			
			String expectedJson = "{\"bank\":{\"brick\":353,\"ore\":10,\"sheep\":22,\"wheat\":33,\"wood\":72},"+
			"\"chat\":{\"lines\":[{\"message\":\"\",\"source\":\"Eric\"},{\"message\":\"A\",\"source\":\"Cannon\"},{\"message\":\"B\",\"source\":\"Michael\"}]},"
					+ "\"log\":{\"lines\":[{\"message\":\"Michael bought a dev card\",\"source\":\"Michael\"},{\"message\":\"Eric won the game\",\"source\":\"Eric\"}]},"
					+ "\"map\":{\"hexes\":[{\"location\":{\"x\":3,\"y\":-2},\"resource\":\"desert\",\"number\":7},{\"location\":{\"x\":-1,\"y\":2},\"resource\":\"brick\",\"number\":1}],"
					+ "\"ports\":[{\"resource\":\"wheat\",\"location\":{\"x\":-2,\"y\":1},\"direction\":\"S\",\"ratio\":2}],"
					+ "\"roads\":[{\"owner\":0,\"location\":{\"x\":0,\"y\":0,\"direction\":\"S\"}}],"
					+ "\"settlements\":[{\"owner\":0,\"location\":{\"x\":1,\"y\":2,\"direction\":\"SW\"}},{\"owner\":0,\"location\":{\"x\":1,\"y\":-3,\"direction\":\"E\"}}],"
					+ "\"cities\":[{\"owner\":1,\"location\":{\"x\":3,\"y\":-1,\"direction\":\"NW\"}},{\"owner\":2,\"location\":{\"x\":-2,\"y\":1,\"direction\":\"SE\"}}],"
					+ "\"radius\":3,"
					+ "\"robber\":{\"x\":3,\"y\":3}},"
					+ "\"players\":"
					+ 	"["
					/*+ 		
					  		"{\"color\":\"puce\",\"discarded\":true,\"monuments\":0,\"name\":\"Eric\","
					+ 		"\"newDevCards\":{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":1,\"yearOfPlenty\":0},"
					+ 		"\"oldDevCards\":{\"monopoly\":1,\"monument\":0,\"roadBuilding\":0,\"soldier\":0,\"yearOfPlenty\":0},"
					+ 		"\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":4444,"
					+ 		"\"resources\":{\"brick\":2,\"ore\":3,\"sheep\":4,\"wheat\":5,\"wood\":6},"
					+ 		"\"roads\":1,\"settlements\":2,\"cities\":4,"
					+ 		"\"soldiers\":55,\"victoryPoints\":11}"
					*/
					+ 	"],"
					+ "\"tradeOffer\":{\"sender\":2,\"receiver\":3,\"offer\":{\"brick\":-1,\"ore\":25,\"sheep\":21,\"wheat\":20,\"wood\":43}},"
					+ "\"turnTracker\":{\"currentTurn\":3,\"status\":\"DISCARDING\",\"longestRoad\":1,\"largestArmy\":2},\"version\":2000,\"winner\":1}";
			System.out.println(expectedJson);
			System.out.println(actualJson);
			
			
			assertTrue(actualJson.equals(expectedJson));
			

		}
		catch (Exception e)
		{
			System.out.println(e);
			System.out.println("there may have been a bad index in test 2");
			// Bad Index
		}
	}
	

}
