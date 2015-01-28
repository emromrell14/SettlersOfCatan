package facade;

public class MovesManager extends MasterManager
{
	/**
	 * Creates a MovesManager facade that connects the client to the proxy
	 * 
	 * @return New MovesManager object
	 */
	public MovesManager()
	{
		
	}
	/**
	 * Sends a chat message
	 * 
	 * @return JSON String with the client model
	 */
	public String sendChatMessage() 
	{
		return null;
	}
	/**
	 * Used to roll a unmber at the beginning of your turn
	 * 
	 * @return JSON String with the client model
	 */
	public String rollDice()
	{
		return null;
	}
	/**
	 * Moves the robber, selecting the new robber position and player to rob
	 * 
	 * @return JSON String with the client model
	 */
	public String robPlayer() 
	{
		return null;
	}
	/**
	 * Used to finish your turn
	 * 
	 * @return JSON String with the client model
	 */
	public String finishTurn() 
	{
		return null;
	}
	/**
	 * Used to buy a development card
	 * 
	 * @return JSON String with the client model
	 */
	public String buyDevCard() 
	{
		return null;
	}
	/**
	 * Plays a "Year of Plenty" card from your hand to gain the two specified resources
	 * 
	 * @return JSON String with the client model
	 */
	public String playYearOfPlenty()
	{
		return null;
	}
	/**
	 * Plays a "Road Building" card from your hand to build two roads at the specified locations
	 * 
	 * @return JSON String with the client model
	 */
	public String playRoadBuilding() 
	{
		return null;
	}
	/**
	 * Plays a "Soldier" from your hand, selecting the new robber position and player to rob
	 * 
	 * @return JSON String with the client model
	 */
	public String playSoldier()
	{
		return null;
	}
	/**
	 * Plays a "Monopoly" card from your hand to monopolize the specified resource
	 * 
	 * @return JSON String with the client model
	 */
	public String playMonopoly() 
	{
		return null;
	}
	/**
	 * Plays a "Monument" card from your hand to give you a victory point
	 * 
	 * @return JSON String with the client model
	 */
	public String playMonument() 
	{
		return null;
	}
	/**
	 * Builds a road at the specified location. (Set 'free' to true during initial setup)
	 * 
	 * @return JSON String with the client model
	 */
	public String buildRoad() 
	{
		return null;
	}
	/**
	 * Builds a settlement at the specified location. (Set 'free' to true during initial setup)
	 * 
	 * @return JSON String with the client model
	 */
	public String buildSettlement() 
	{
		return null;
	}
	/**
	 * Builds a city at the specified location
	 * 
	 * @return JSON String with the client model
	 */
	public String buildCity() 
	{
		return null;
	}
	/**
	 * Offers a domestic trade to another player
	 * 
	 * @return JSON String with the client model
	 */
	public String offerTrade() 
	{
		return null;
	}
	/**
	 * Used to accept or reject a trade offered to you
	 * 
	 * @return JSON String with the client model
	 */
	public String acceptTrade() 
	{
		return null;
	}
	/**
	 * Used to execute a maritime trade
	 * 
	 * @return JSON String with the client model
	 */
	public String executeMaritimeTrade()
	{
		return null;
	}
	/**
	 * Discards the specified resource cards
	 * 
	 * @return JSON String with the client model
	 */
	public String discardCards() 
	{
		return null;
	}
}
