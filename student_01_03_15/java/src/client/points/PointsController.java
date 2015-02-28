package client.points;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, Observer 
{

	private IGameFinishedView finishedView;
	private IMasterManager master;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView)
	{
		
		super(view);
		
		setFinishedView(finishedView);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
		initFromModel();
	}
	
	public IPointsView getPointsView() 
	{
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView()
	{
		return finishedView;
	}
	public void setFinishedView(IGameFinishedView finishedView) 
	{
		this.finishedView = finishedView;
	}

	private void initFromModel() 
	{
		int playerID = master.getPlayerID();
		getPointsView().setPoints(master.getPlayerPoints(playerID));
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		initFromModel();
	}
	
}

