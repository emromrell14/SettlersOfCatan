package poller;

import java.util.Timer;
import java.util.TimerTask;

import JSONmodels.ClientModel;
import models.Game;
import facade.IMasterManager;
import facade.MasterManager;

/** Poller class
*
* @author Team 2
*/

public class Poller implements Runnable
{
	private IMasterManager mMasterManager;
	private final int mSecondsBetweenPolls = 2;
//	private int version = 0;
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
		return mMasterManager.getCurrentModel().version();
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
			mMasterManager.getGameModel(getVersion());
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
		 System.out.println("Starting Timer");
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
