package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import JSONmodels.*;

public class JSONTester {

	@Test
	public void test() {
		//ClientModel
		String JSONClientModel = "{\"bank\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"chat\":{\"lines\":[{\"message\":\"string\",\"source\":\"string\"}]},\"log\":{\"lines\":[{\"message\":\"string\",\"source\":\"string\"}]},\"map\":{\"hexes\":[{\"location\":{\"x\":0,\"y\":0},\"resource\":\"wood\",\"number\":0}],\"ports\":[{\"resource\":\"brick\",\"location\":{\"x\":0,\"y\":0},\"direction\":\"SE\",\"ratio\":2}],\"roads\":[{\"owner\":1,\"location\":{\"x\":0,\"y\":0,\"direction\":\"S\"}}],\"settlements\":[{\"owner\":1,\"location\":{\"x\":0,\"y\":0,\"direction\":\"SW\"}}],\"cities\":[{\"owner\":2,\"location\":{\"x\":0,\"y\":0,\"direction\":\"NE\"}}],\"radius\":5,\"robber\":{\"x\":0,\"y\":0}},\"players\":[{\"cities\":3,\"color\":\"blue\",\"discarded\":true,\"monuments\":4,\"name\":\"Eric\",\"newDevCards\":{\"monopoly\":1,\"monument\":0,\"roadBuilding\":0,\"soldier\":2,\"yearOfPlenty\":0},\"oldDevCards\":{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":3,\"yearOfPlenty\":0},\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"roads\":2,\"settlements\":0,\"soldiers\":0,\"victoryPoints\":0}],\"tradeOffer\":{\"sender\":0,\"receiver\":0,\"offer\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0}},\"turnTracker\":{\"currentTurn\":2,\"status\":\"PLAYING\",\"longestRoad\":3,\"largestArmy\":2},\"version\":644,\"winner\":-1}";
		ClientModel clientModel = ClientModel.fromJSON(JSONClientModel);
		assertTrue(clientModel.getMap().getCities()[0].getLocation().getDirection().equals("NE"));
		assertTrue(clientModel.getPlayers()[0].getName().equals("Eric"));
		assertTrue(clientModel.toJSON().equals(JSONClientModel));		
		
		//DevCardList
		String JSONDevCardList = "{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":2,\"yearOfPlenty\":0}";
		DevCardList devCardList = DevCardList.fromJSON(JSONDevCardList);
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
		EdgeValue edgeValue = EdgeValue.fromJSON(JSONEdgeValue);
		assertTrue(edgeValue.getOwner() == 1);
		assertTrue(edgeValue.getLocation().getX() == 0);
		assertTrue(edgeValue.getLocation().getDirection().equals("NW"));
		assertTrue(edgeValue.toJSON().equals(JSONEdgeValue));
		
		//Hex
		String JSONHex = "{\"location\":{\"x\":20,\"y\":1},\"resource\":\"wood\",\"number\":8}";
		Hex hex = Hex.fromJSON(JSONHex);
		assertTrue(hex.getNumber() == 8);
		assertTrue(hex.getLocation().getX() == 20);
		assertTrue(hex.toJSON().equals(JSONHex));
		
		//Map
		String JSONMap = "{\"hexes\":[{\"location\":{\"x\":0,\"y\":0},\"resource\":\"string\",\"number\":0}],\"ports\":[{\"resource\":\"string\",\"location\":{\"x\":0,\"y\":0},\"direction\":\"string\",\"ratio\":0}],\"roads\":[{\"owner\":2,\"location\":{\"x\":0,\"y\":0,\"direction\":\"string\"}}],\"settlements\":[{\"owner\":3,\"location\":{\"x\":0,\"y\":0,\"direction\":\"string\"}}],\"cities\":[{\"owner\":2,\"location\":{\"x\":0,\"y\":0,\"direction\":\"string\"}}],\"radius\":50,\"robber\":{\"x\":0,\"y\":0}}";
		Map map = Map.fromJSON(JSONMap);
		assertTrue(map.getRadius() == 50);
		assertTrue(map.getCities()[0].getOwner() == 2);
		assertTrue(map.toJSON().equals(JSONMap));		
		
		//MessageLine
		String JSONMessageLine = "{\"message\":\"message string\",\"source\":\"wikipedia\"}";
		MessageLine messageLine = MessageLine.fromJSON(JSONMessageLine);
		assertTrue(messageLine.getMessage().equals("message string"));
		assertTrue(messageLine.getSource().equals("wikipedia"));
		assertTrue(messageLine.toJSON().equals(JSONMessageLine));
		
		//MessageList
		String JSONMessageList = "{\"lines\":[{\"message\":\"new message\",\"source\":\"string\"},{\"message\":\"string2\",\"source\":\"string2\"}]}";
		MessageList messageList = MessageList.fromJSON(JSONMessageList);
		assertTrue(messageList.getLines().length == 2);
		assertFalse(messageList.getLines().length == 3);
		assertTrue(messageList.getLines()[0].getMessage().equals("new message"));
		assertTrue(messageList.toJSON().equals(JSONMessageList));
		
		//Player
		String JSONPlayer = "{\"cities\":3,\"color\":\"red\",\"discarded\":true,\"monuments\":2,\"name\":\"string\",\"newDevCards\":{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":0,\"yearOfPlenty\":0},\"oldDevCards\":{\"monopoly\":0,\"monument\":0,\"roadBuilding\":0,\"soldier\":0,\"yearOfPlenty\":0},\"playerIndex\":2,\"playedDevCard\":true,\"playerID\":1325,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"roads\":0,\"settlements\":0,\"soldiers\":0,\"victoryPoints\":0}";
		Player player = Player.fromJSON(JSONPlayer);
		assertTrue(player.getColor().equals("red"));
		assertTrue(player.hasDiscarded());
		assertTrue(player.toJSON().equals(JSONPlayer));		
		
		//Port
		String JSONPort = "{\"resource\":\"brick\",\"location\":{\"x\":0,\"y\":0},\"direction\":\"NE\",\"ratio\":2}";
		Port port = Port.fromJSON(JSONPort);
		assertTrue(port.getResource().equals("brick"));
		assertTrue(port.getDirection().equals("NE"));
		assertTrue(port.toJSON().equals(JSONPort));
				
		//ResourceList
		String JSONResourceList = "{\"brick\":1,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":2}";
		ResourceList resourceList = ResourceList.fromJSON(JSONResourceList);
		assertTrue(resourceList.getBrick() == 1);
		assertTrue(resourceList.getWood() == 2);
		assertTrue(resourceList.toJSON().equals(JSONResourceList));
		
		//TradeOffer
		String JSONTradeOffer = "{\"sender\":2,\"receiver\":3,\"offer\":{\"brick\":1,\"ore\":1,\"sheep\":1,\"wheat\":1,\"wood\":1}}";
		TradeOffer tradeOffer = TradeOffer.fromJSON(JSONTradeOffer);
		assertTrue(tradeOffer.getSender() == 2);
		assertTrue(tradeOffer.getOffer().getBrick() == 1);
		assertTrue(tradeOffer.toJSON().equals(JSONTradeOffer));		
		
		//TurnTracker
		String JSONTurnTracker = "{\"currentTurn\":2,\"status\":\"ROLLING\",\"longestRoad\":-1,\"largestArmy\":1}";
		TurnTracker turnTracker = TurnTracker.fromJSON(JSONTurnTracker);
		assertTrue(turnTracker.getCurrentTurn() == 2);
		assertTrue(turnTracker.getLongestRoad() == -1);
		assertTrue(turnTracker.toJSON().equals(JSONTurnTracker));
		
		//VertexObject
		String JSONVertexObject = "{\"owner\":2,\"location\":{\"x\":0,\"y\":0,\"direction\":\"SE\"}}";
		VertexObject vertexObject = VertexObject.fromJSON(JSONVertexObject);
		assertTrue(vertexObject.getOwner() == 2);
		assertTrue(vertexObject.getLocation().getY() == 0);
		assertTrue(vertexObject.toJSON().equals(JSONVertexObject));
	}

}
