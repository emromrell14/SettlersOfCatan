package client.map;

import shared.locations.HexLocation;
import client.base.*;
import client.data.*;

/**
 * Interface for the rob view, which lets the user select a player to rob
 */
public interface IRobView extends IOverlayView
{
	
	void setPlayers(RobPlayerInfo[] candidateVictims);
	HexLocation getRobberLocation();
	void setRobberLocation(HexLocation robberLocation);
}

