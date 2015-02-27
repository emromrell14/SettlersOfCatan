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
import states.*;
import client.base.*;
import client.data.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {
	
	private IRobView robView;
	private MasterManager master;
	private IState state;
	private boolean InitializedHexes;
	
	public MapController(IMapView view, IRobView robView) 
	{
		
		super(view);
		
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
		state = new SetupState();
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
		
		if (game != null)
		{
			
			Board b = game.board();
			
			if (!InitializedHexes)
			{
				initHexesFromModel(b);
			}
			// Add in the water tiles
			
			
			
			
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
		
	}

	private void initHexesFromModel(Board b)
	{
		
		for (int x = 0; x <= 3; ++x) 
		{	
			int maxY = 3 - x;
			for (int y = -3; y <= maxY; ++y) 
			{
				
				if (Math.abs(y)==3 || Math.abs(x)==3)
				{
					HexLocation hexLoc = new HexLocation(x, y);
					HexType hexType;
					hexType = HexType.WATER;
					b.addHex(new Hex(hexLoc, hexType, new TokenValue(0)));
				}
			}
			
			if (x != 0) 
			{
	
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) 
				{
					if (Math.abs(y)==3 || Math.abs(x)==3)
					{
						HexType hexType;
						hexType = HexType.WATER;
						HexLocation hexLoc = new HexLocation(-x, y);
						b.addHex(new Hex(hexLoc, hexType, new TokenValue(0)));
					}
				}
			}
		}
		
		b.addHex(new Hex(new HexLocation(1,2), HexType.WATER,new TokenValue(0)));
		b.addHex(new Hex(new HexLocation(-1,-2), HexType.WATER,new TokenValue(0)));
		b.addHex(new Hex(new HexLocation(2,1), HexType.WATER,new TokenValue(0)));
		b.addHex(new Hex(new HexLocation(-2,-1), HexType.WATER,new TokenValue(0)));	
	
		InitializedHexes = true;
		
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
				System.out.println("Should only get here when token value is 0");
			}
		}
		
	}
	public boolean canPlaceRoad(EdgeLocation edgeLoc) 
	{
		return state.canPlaceRoad(edgeLoc);
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) 
	{
		return state.canPlaceSettlement(vertLoc);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) 
	{	
		return state.canPlaceCity(vertLoc);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) 
	{
		return state.canPlaceRobber(hexLoc);
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
			CatanColor color = master.getCurrentModel().getPlayer(playerIndex).color();
			getView().placeRoad(edgeLoc, color);			
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
			getView().startDrop(pieceType, master.getPlayer().color(), state.isCancelAllowed());
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
			if(master.hasJoinedGame)
			{
				initFromModel();
				Status status = master.getCurrentModel().turnTracker().status();
				switch(status)
				{
				case ROBBING:
					state = new RobbingState();
					break;
				case PLAYING:
					state = new PlayingState();
					break;
				case DISCARDING:
					state = new DiscardingState();
					break;
				case ROLLING:
					state = new RollingState();
					break;
				case FIRSTROUND:
					state = new SetupState();
					break;
				case SECONDROUND:
					state = new SetupState();
					break;
				default:
					System.out.println("MapController update() should never get here.");
			}
		}
	}

	@Override
	public IState getState() {
		return state;
	}
	
}

