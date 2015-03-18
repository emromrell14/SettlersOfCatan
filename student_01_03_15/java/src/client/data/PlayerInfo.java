package client.data;

import shared.definitions.*;

/**
 * Used to pass player information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li>
 * <li>Name: Player's name (non-empty string)</li>
 * <li>Color: Player's color (cannot be null)</li>
 * </ul>
 * 
 */
public class PlayerInfo
{
	
	private int id;
	private int playerIndex;
	private String name;
	private CatanColor colorCatan;
	private String color;
	
	public PlayerInfo()
	{
		setId(-1);
		setPlayerIndex(-1);
		setName("");
		setColorCatan(null);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getPlayerIndex()
	{
		return playerIndex;
	}
	
	public void setPlayerIndex(int playerIndex)
	{
		this.playerIndex = playerIndex;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public CatanColor getColorCatan()
	{
		return colorCatan;
	}
	
	public void setColorCatan(CatanColor color)
	{
		this.colorCatan = color;
	}
	public String getColor()
	{
		return color;
	}
	
	public void setColor(String color)
	{
		this.color = color;
		if(color != null)
			this.colorCatan = CatanColor.valueOf(color.toUpperCase());
		//////System.out.println("PLAYER info: " + this.colorCatan);
	}

	@Override
	public int hashCode()
	{
		return 31 * this.id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final PlayerInfo other = (PlayerInfo) obj;
		
		return this.id == other.id;
	}
}

