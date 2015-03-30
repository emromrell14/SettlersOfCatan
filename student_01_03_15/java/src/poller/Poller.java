package poller;

import java.util.Timer;
import java.util.TimerTask;

import models.Game;
import facade.MasterManager;

/** Poller class
*
* @author Team 2
*/

public class Poller implements Runnable
{
	private MasterManager mMasterManager;
	private final int mSecondsBetweenPolls = 2;
	private int mVersion = 0;
	private Timer timer;


	
    /**
     * Creates the Poller object
     *
     * @return a new Poller object
     */
	public Poller()
	{
		mMasterManager = MasterManager.getInstance();
	}
	
	public int getVersion()
	{
		Game game = mMasterManager.getCurrentModel();
//		System.out.println("POLLER getversion() -- " + game.version() + " " + game.id());
		return (game == null) ? -1 : mVersion;//game.version();
	}
	
	/**
	 * Class to extend TimerTask
	 * The run method checks if the model has changed and if so updates the client
	 */
	class PollTimer extends TimerTask 
	{
	    @Override
		public void run()
	    {
			// THE COMPARING ACTUALLY TAKES PLACE AND IS RESOLVED ON THE SERVER SIDE
//			version = 
//	    	int initialV = -1;
//	    	if (mMasterManager.getCurrentModel() != null)
//	    	{
//	    		initialV = (mMasterManager.hasJoinedGame) ? mMasterManager.getCurrentModel().version() : -1;
//	    	}
	    	
//	    	System.out.println("Poller:" + getVersion());
	    	
	    	int version = getVersion();
	    	// if they havent joined a game then switch version back to -1
	    	version = (mMasterManager.hasJoinedGame) ? version : -1;
//			mMasterManager.getGameModel(version);
	    	mVersion = mMasterManager.pollerGetGameModel(version);
//			System.out.println("Poller version: " + mVersion );
			
			
//			int finalV = -1;
//	    	if (mMasterManager.getCurrentModel() != null)
//	    	{
//	    		finalV = mMasterManager.getCurrentModel().version();
//	    	}
	    	// I think this code is useless, if mMasterManager.getGameModel(getVersion()); comes back != null it will update
	    	// the model and all observers automatically
//	    	if (initialV != finalV)
//	    	{
//	    		MasterManager.getInstance().getModelManager().setModelChanged();
//				MasterManager.getInstance().getModelManager().notifyObservers();
//	    	}

	    }
	 }
	
	/**
     * Run function to start a TimerTask every n seconds to check for model updates
     *
     */
	@Override
	public void run() 
	{
	
		 // And From your main() method or any other method
		 timer = new Timer();
		 timer.schedule(new PollTimer(), 0, mSecondsBetweenPolls * 1000);
		 //System.out.println("Starting Timer");
	}
	
	/**
     * compareVersions to compare clients current version with possible updated game version
     *
     * @return boolean to tell if clients version is the same as the game or not
     */
	public boolean compareVersions() 
	{
		return true; // this is just a temporary default
	}
	
	public void interrupt()
	{
		if(timer != null)
			timer.cancel();
	}
}
