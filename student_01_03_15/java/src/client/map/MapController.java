package client.map;

import java.util.*;

import JSONmodels.EdgeLocationJSON;
import models.Index;
import models.Status;
import models.TurnTracker;
import shared.definitions.*;
import shared.locations.*;
import client.base.*;
import client.data.*;
import facade.MasterManager;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController {
	
	private IRobView robView;
	private MasterManager master;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		master = MasterManager.getInstance();
		
		setRobView(robView);
		
		initFromModel();
		
		master = MasterManager.getInstance();
	}
	
	public IMapView getView() {
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	protected void initFromModel() {
		
		//<temp>
		
		Random rand = new Random();

		for (int x = 0; x <= 3; ++x) {
			
			int maxY = 3 - x;			
			for (int y = -3; y <= maxY; ++y) {
				System.out.println("x: " + x + " y: " + y);
				HexLocation hexLoc = new HexLocation(x, y);
				int r = rand.nextInt(HexType.values().length);
				HexType hexType;
				if (Math.abs(y)==3 || Math.abs(x)==3)
				{
					hexType = HexType.WATER;
				}
				else
				{
					hexType = HexType.values()[r];
				}
				getView().addHex(hexLoc, hexType);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
						CatanColor.RED);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
						CatanColor.BLUE);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
						CatanColor.ORANGE);
				getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
				getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					int r = rand.nextInt(HexType.values().length);
					HexType hexType;
					if (Math.abs(y)==3 || Math.abs(x)==3)
					{
						hexType = HexType.WATER;
					}
					else
					{
						hexType = HexType.values()[r];
					}
					HexLocation hexLoc = new HexLocation(-x, y);
					getView().addHex(hexLoc, hexType);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
							CatanColor.RED);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
							CatanColor.BLUE);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
							CatanColor.ORANGE);
					getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
					getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
				}
			}
		}
		
		// Adding water tiles
		getView().addHex(new HexLocation(1,2), HexType.WATER);
		getView().addHex(new HexLocation(2,1), HexType.WATER);
		getView().addHex(new HexLocation(-1,-2), HexType.WATER);
		getView().addHex(new HexLocation(-2,-1), HexType.WATER);
		
		PortType portType = PortType.BRICK;
		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
		
		getView().placeRobber(new HexLocation(0, 0));
		
		getView().addNumber(new HexLocation(-2, 0), 2);
		getView().addNumber(new HexLocation(-2, 1), 3);
		getView().addNumber(new HexLocation(-2, 2), 4);
		getView().addNumber(new HexLocation(-1, 0), 5);
		getView().addNumber(new HexLocation(-1, 1), 6);
		getView().addNumber(new HexLocation(1, -1), 8);
		getView().addNumber(new HexLocation(1, 0), 9);
		getView().addNumber(new HexLocation(2, -2), 10);
		getView().addNumber(new HexLocation(2, -1), 11);
		getView().addNumber(new HexLocation(2, 0), 12);
		
		//</temp>
		
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		Index playerIndex = master.getCurrentModel().turnTracker().currentTurn();
		return master.canPlaceRoad(playerIndex, edgeLoc);
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		Index playerIndex = master.getCurrentModel().turnTracker().currentTurn();
		return master.canPlaceSettlement(playerIndex, vertLoc);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		
		Index playerIndex = master.getCurrentModel().turnTracker().currentTurn();
		return master.canPlaceCity(playerIndex, vertLoc);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		return master.canPlaceRobber(hexLoc);
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		Index playerIndex = master.getCurrentModel().turnTracker().currentTurn();
		Status status = master.getCurrentModel().turnTracker().status();
		//String type = status.toString();
		boolean free = (status == Status.FIRSTROUND || status == Status.SECONDROUND);
		master.buildRoad(playerIndex, edgeLoc, free);
		getView().placeRoad(edgeLoc, CatanColor.ORANGE);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		Index playerIndex = master.getPlayerIndex();
		Status status = master.getCurrentModel().turnTracker().status();
		//String type = status.toString();
		boolean free = (status == Status.FIRSTROUND || status == Status.SECONDROUND);
		master.buildSettlement(playerIndex, vertLoc, free);
		CatanColor color = master.getCurrentModel().getPlayer(playerIndex).color();
		getView().placeSettlement(vertLoc, color);
	}

	public void placeCity(VertexLocation vertLoc) {
		Index playerIndex = master.getPlayerIndex();
		Status status = master.getCurrentModel().turnTracker().status();
		//String type = status.toString();
		boolean free = (status == Status.FIRSTROUND || status == Status.SECONDROUND);
		master.buildCity(playerIndex, vertLoc, free);
		getView().placeCity(vertLoc, master.getCurrentModel().getPlayer(playerIndex).color());
	}

	public void placeRobber(HexLocation hexLoc) {
		getView().placeRobber(hexLoc);
		
		getRobView().setRobberLocation(hexLoc);
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	

		getView().startDrop(pieceType, master.getCurrentModel().getPlayer(master.getPlayerIndex()).color(), true);
	}
	
	public void cancelMove() {
		// We somehow need to close the modal...
	}
	
	public void playSoldierCard() {	
		// Why do we need this, if it is already on the DevCardController?		
	}
	
	public void playRoadBuildingCard() {	
		// Why do we need this, if it is already on the DevCardController?
	}
	
	public void robPlayer(RobPlayerInfo victim) {
		Index victimIndex = null;
		try {
			victimIndex = new Index(victim.getPlayerIndex());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		master.robPlayer(master.getPlayerIndex(), victimIndex, getRobView().getRobberLocation());
	}
	
}

