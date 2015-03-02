package client.map;

import java.util.*;

import models.Board;
import models.Building;
import models.Game;
import models.Hex;
import models.Index;
import models.Player;
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
import client.resources.ResourceBarElement;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {
	
	private IRobView robView;
	private MasterManager master;
	private IState state;
	public static boolean InitializedHexes = false;
	private static boolean buildingSettlement = false;
	private boolean FirstRoundDone = false;
	private boolean SecondRoundDone = false;
	private boolean soldierRob = false;
	private HexLocation robberLocation = null;
	private boolean roadBuilding = false;
	private EdgeLocation road1 = null;
	private EdgeLocation road2 = null;
	
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
			robberLocation = game.robber().location();
			Board b = game.board();
			
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
			
			ArrayList<Hex> hexListCopy = new ArrayList<Hex>(b.hexes());

			if (!InitializedHexes)
			{
				initHexesFromModel(hexListCopy);
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

	private void initHexesFromModel(ArrayList<Hex> hexListCopy)
	{		
	
		for (Hex h : hexListCopy)
		{
			getView().addHex(h.location(),h.resource());
			TokenValue num = h.number();
			if (num != null && num.value() != 0)
			{
				getView().addNumber(h.location(),num.value());
			}
			
		}
		MapController.InitializedHexes = true;
		return;
		
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
		try
		{
			Index playerIndex = master.getPlayerIndex();		
			if(roadBuilding)
			{
				if(road1 == null)
				{
					road1 = edgeLoc;
					master.getPlayer().addRoad(new Road(playerIndex,road1));
				}
				else
				{
					road2 = edgeLoc;
					master.playRoadBuilding(playerIndex, road1, road2);
					road1 = null;
					road2 = null;
					roadBuilding = false;
				}
			}
			else
			{
				master.buildRoad(playerIndex, edgeLoc, state.isPlayingFree());
			}
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
		robberLocation = hexLoc;
		getView().placeRobber(hexLoc);
		getRobView().setRobberLocation(hexLoc);
		ArrayList<Player> v = (ArrayList<Player>) master.getRobbingVictims(hexLoc);
		RobPlayerInfo[] victims = new RobPlayerInfo[v.size()];
		for(int i = 0; i < v.size(); ++i)
		{
			RobPlayerInfo r = new RobPlayerInfo();
			r.setNumCards(v.get(i).resources().getTotal());
			r.setColor(v.get(i).color().toString());
			r.setName(v.get(i).name());
			r.setPlayerIndex(v.get(i).playerIndex().value());
			victims[i] = r;
		}		
		getRobView().setPlayers(victims);
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
		soldierRob = true;
		state = new RobbingState();
		getView().startDrop(PieceType.ROBBER, master.getPlayer().color(), false);
	}
	
	public void playRoadBuildingCard() 
	{	
		roadBuilding = true;
		state = new PlayingState();
		startMove(PieceType.ROAD, true, false);			
		startMove(PieceType.ROAD, true, false);			
	}
	
	public void robPlayer(RobPlayerInfo victim) 
	{
		try 
		{
			Index victimIndex = new Index(victim.getPlayerIndex());
			
			if(soldierRob)
			{
				soldierRob = false;
				master.playSoldier(master.getPlayerIndex(), victimIndex, robberLocation);
			}
			else
			{
				master.robPlayer(master.getPlayerIndex(), victimIndex, getRobView().getRobberLocation());
			}
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
					if (master.getCurrentModel().turnTracker().currentTurn().value() == master.getPlayerIndex().value())
					{
						if(!getView().isModalShowing())
						{
							getView().startDrop(PieceType.ROBBER, master.getPlayer().color(), false);
						}
					}
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
			
			// THIS IS FOR ROUNDS 1 AND 2------------------
			if (state.isPlayingFree())
			{
				Player p = master.getPlayer();
				if (master.getCurrentModel().turnTracker().currentTurn().value() == p.playerIndex().value())
				{
					int roadsBuilt = p.roads().size();
					int settlementsBuilt = p.settlements().size();
					
					if (roadsBuilt == 1 && !FirstRoundDone)
					{
						//NOW allow them to place their second settlement (to avoid double fires)
						MapController.buildingSettlement = false;
						FirstRoundDone = true;
						master.finishTurn(p.playerIndex());
					}
					else if (roadsBuilt == 2 && !SecondRoundDone)
					{
						SecondRoundDone = true;
						master.finishTurn(p.playerIndex());
					}
					else if ((roadsBuilt == 0 && settlementsBuilt == 0) || (roadsBuilt == 1 && settlementsBuilt == 1))
					{
						//Only let them build a settlement if they're not already doing it! (to avoid double fires)
						if(!MapController.buildingSettlement)
						{
							MapController.buildingSettlement = true;
							buildSettlementSetup();
						}
					}
					else if ((roadsBuilt == 0 && settlementsBuilt == 1) || (roadsBuilt == 1 && settlementsBuilt == 2))
					{
						buildRoadSetup();
					}
				}
			}
		}
	}

	private void buildRoadSetup() 
	{
		if (state.canAffordRoad())
		{
			startMove(PieceType.ROAD, state.isPlayingFree(), state.isDisconnectedPlayingAllowed());			
		}
	}

	private void buildSettlementSetup() 
	{
		if (state.canAffordRoad())
		{
			startMove(PieceType.SETTLEMENT, state.isPlayingFree(), state.isDisconnectedPlayingAllowed());			
		}		
	}

	@Override
	public IState getState() 
	{
		return state;
	}
	
}

