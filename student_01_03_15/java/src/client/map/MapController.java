package client.map;

import java.util.*;

import models.Board;
import models.Building;
import models.Game;
import models.Hex;
import models.Index;
import models.Port;
import models.Road;
import models.Robber;
import models.Status;
import models.TokenValue;
import shared.definitions.*;
import shared.locations.*;
import states.IState;
import client.base.*;
import client.data.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {
	
	private IRobView robView;
	private IMasterManager master;
	private IState state;
	
	public MapController(IMapView view, IRobView robView) 
	{
		
		super(view);
		
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
		setRobView(robView);
		
		initFromModel();
		
		master = MasterManager.getInstance();
	}
	
	public IMapView getView() 
	{
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() 
	{
		return robView;
	}
	private void setRobView(IRobView robView) 
	{
		this.robView = robView;
	}
	
	protected void initFromModel() 
	{
		Game game = master.getCurrentModel();
		/*Game game = new Game();
		Board testBoard = new Board();
		
		for (int x = 0; x <= 3; ++x) 
		{	
			int maxY = 3 - x;
			for (int y = -3; y <= maxY; ++y) 
			{
				System.out.println("x: " + x + " y: " + y);
				HexLocation hexLoc = new HexLocation(x, y);
				HexType hexType;
				if (Math.abs(y)==3 || Math.abs(x)==3)
				{
					hexType = HexType.WATER;
				}
				else
				{
					hexType = HexType.WHEAT;
				}
				testBoard.addHex(new Hex(hexLoc, hexType, new TokenValue(2)));
			}
			
			if (x != 0) 
			{
				System.out.println("x: " + x + " y: " + y);

				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) 
				{
					HexType hexType;
					if (Math.abs(y)==3 || Math.abs(x)==3)
					{
						hexType = HexType.WATER;
					}
					else
					{
						hexType = HexType.WHEAT;
					}
					HexLocation hexLoc = new HexLocation(-x, y);
					testBoard.addHex(new Hex(hexLoc, hexType, new TokenValue(2)));
				}
			}
		}
		
		
		testBoard.addHex(new Hex(new HexLocation(1,2), HexType.WATER,null));
		testBoard.addHex(new Hex(new HexLocation(-1,-2), HexType.WATER,null));
		testBoard.addHex(new Hex(new HexLocation(2,1), HexType.WATER,null));
		testBoard.addHex(new Hex(new HexLocation(-2,-1), HexType.WATER,null));	
		
		game.setBoard(testBoard);
		game.setRobber(new Robber(new HexLocation(0,0)));
		*/
		if (game != null)
		{
			Board b = game.board();
			
	
			for (Hex h : b.hexes())
			{
				getView().addHex(h.location(),h.resource());
				TokenValue num = h.number();
				if (num != null && num.value() != 0)
				{
					System.out.println(num.value());
					getView().addNumber(h.location(),num.value());
				}
				else
				{
					assert false;
					System.out.println("Shouldn\'t get here");
				}
			}
			
			for (Road r : b.roads())
			{
				getView().placeRoad(r.location(), master.getCurrentModel().getPlayer(r.owner()).color());
			}
			
			for (Building s : b.settlements())
			{
				getView().placeSettlement(s.location(), master.getCurrentModel().getPlayer(s.owner()).color());
			}
			
			for (Building c : b.cities())
			{
				getView().placeCity(c.location(), master.getCurrentModel().getPlayer(c.owner()).color());
			}
			for (Port p : b.ports())
			{
				getView().addPort(new EdgeLocation(p.location(),p.direction()), p.resource());
			}
			
			getView().placeRobber(game.robber().location());
		}
		/*
		Random rand = new Random();

		for (int x = 0; x <= 3; ++x) 
		{
			
			int maxY = 3 - x;
			for (int y = -3; y <= maxY; ++y) 
			{
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
				//b.addHex(new Hex(new HexLocation(1,2), hexType, new TokenValue(y+5)));

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
			
			if (x != 0) 
			{
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) 
				{
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
		*/
		
		/*
		TokenValue t = new TokenValue(-1);
		b.addHex(new Hex(new HexLocation(1,2), HexType.WATER,t));
		b.addHex(new Hex(new HexLocation(-1,-2), HexType.WATER,t));
		b.addHex(new Hex(new HexLocation(2,1), HexType.WATER,t));
		b.addHex(new Hex(new HexLocation(-2,-1), HexType.WATER,t));
		//</temp>
		*/
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) 
	{
		Index playerIndex = master.getPlayerIndex();
		return master.canPlaceRoad(playerIndex, edgeLoc);
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) 
	{
		Index playerIndex = master.getPlayerIndex();
		return master.canPlaceSettlement(playerIndex, vertLoc);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) 
	{	
		Index playerIndex = master.getPlayerIndex();
		return master.canPlaceCity(playerIndex, vertLoc);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) 
	{
		return master.canPlaceRobber(hexLoc);
	}

	public void placeRoad(EdgeLocation edgeLoc) 
	{
		
		//Status status = master.getCurrentModel().turnTracker().status();
		//String type = status.toString();
		//boolean free = (status == Status.FIRSTROUND || status == Status.SECONDROUND);
		try
		{
			Index playerIndex = master.getPlayerIndex();		
			master.buildRoad(playerIndex, edgeLoc, state.isPlayingFree());
			getView().placeRoad(edgeLoc, CatanColor.ORANGE);			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void placeSettlement(VertexLocation vertLoc) 
	{
		//Status status = master.getCurrentModel().turnTracker().status();
		//String type = status.toString();
		//boolean free = (status == Status.FIRSTROUND || status == Status.SECONDROUND);
		
		try 
		{
			Index playerIndex = master.getPlayerIndex();
			master.buildSettlement(playerIndex, vertLoc, state.isPlayingFree());
			CatanColor color = master.getCurrentModel().getPlayer(playerIndex).color();
			getView().placeSettlement(vertLoc, color);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void placeCity(VertexLocation vertLoc) 
	{
		//Status status = master.getCurrentModel().turnTracker().status();
		//String type = status.toString();
		//boolean free = (status == Status.FIRSTROUND || status == Status.SECONDROUND);
		try
		{
			Index playerIndex = master.getPlayerIndex();
			master.buildCity(playerIndex, vertLoc, state.isPlayingFree());
			getView().placeCity(vertLoc, master.getCurrentModel().getPlayer(playerIndex).color());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void placeRobber(HexLocation hexLoc) 
	{
		getView().placeRobber(hexLoc);
		
		getRobView().setRobberLocation(hexLoc);
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) 
	{
		try 
		{
			getView().startDrop(pieceType, master.getPlayer().color(), true);//, state.isCancelAllowed());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void cancelMove() 
	{
		// We somehow need to close the modal...
	}
	
	public void playSoldierCard() 
	{	
		// Why do we need this, if it is already on the DevCardController?		
	}
	
	public void playRoadBuildingCard() 
	{	
		// Why do we need this, if it is already on the DevCardController?
	}
	
	public void robPlayer(RobPlayerInfo victim) 
	{
		try 
		{
			Index victimIndex = new Index(victim.getPlayerIndex());
			master.robPlayer(master.getPlayerIndex(), victimIndex, getRobView().getRobberLocation());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		initFromModel();
		System.out.println("UPDATING MAPCONTROLLER");
	}
	
}

