package models;

import classes.Hex;
import classes.Port;

/** Board class that contains a list of the hexes and a list of the harbors (ports)
*
* @author Team 2
*/
public class Board 
{
	/** A list of all the hexes on the grid */
	private Hex[] mHexes; //A list of all the hexes on the grid - it's only land tiles.
	
	/** A list of all the ports (harbors) on the grid */
	private Port[] mPorts;
	
	/** Board Constructor
	 * 
	 * @return a new Board object
	 */
	public Board()
	{
		
	}
	
	/** Getter for hexes
	 * 
	 * @return a list of all the hexes on the grid
	 */
	public Hex[] getHexes()
	{
		return mHexes;
	}
	
	/** Getter for ports
	 * 
	 * @return a list of all the ports on the grid
	 */
	public Port[] getPorts()
	{
		return mPorts;
	}
	
}
