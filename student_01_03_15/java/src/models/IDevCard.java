package models;

import shared.definitions.DevCardType;

public interface IDevCard
{
	/** 
	 * Checks whether development card has been played
	 * @pre card is not null
	 * @return true if card has been played, false otherwise.
	 */
	boolean hasBeenPlayed();
	/** 
	 * Gets development card type
	 * @pre devcard is not null
	 * @post none
	 * @return one of the following development card type enum values: SOLDIER, YEAR_OF_PLENTY, MONOPOLY, ROAD_BUILD, MONUMENT
	 */
	DevCardType type();
	/** 
	 * Initiates playing of this card
	 * @pre card is not new, card has not been played 
	 * @post card is played
	 * @return one of the following development card type enum values: SOLDIER, YEAR_OF_PLENTY, MONOPOLY, ROAD_BUILD, MONUMENT
	 */
	void execute();
}
