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
	private int version = 0;


	
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
		return this.version;
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
			//if (!compareVersions()) // THE COMPARING ACTUALLY TAKES PLACE AND IS
	    								// RESOLVED ON THE SERVER SIDE
	    	Game gameModel = getGameModel(version);
			if(gameModel != null)
	    	{
				version = gameModel.version();
				updateGUI(gameModel);
			}
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
		 Timer timer = new Timer();
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
    
	/**
	 * returns a String of JSON from the Server if the game state has changed
	 * 
	 * @return a String of JSON or null if the game state hasn't changed
	 */
	public Game getGameModel(int version)
	{
		ClientModel cModel = null;
		Game game = null;
		String gameModel = mMasterManager.getGameModel(version);
		
		if(gameModel != null)
		{
			cModel = ClientModel.fromJSON(gameModel);
			game = cModel.getGameObject();
		}
		return game;
	}
	
	/**
     * updateGUI calls to update clients game to the game/s latest version
     *
     */
	
	public void updateGUI(Game g) 
	{
		mMasterManager.updateModel(g);
	}
	
}
