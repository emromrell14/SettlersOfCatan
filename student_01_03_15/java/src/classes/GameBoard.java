package classes;

import java.util.List;


public class GameBoard 
{
	//Attributes
	private List<HarborPiece> mHarborPieces;
	private List<SeaFramePiece> mSeaPieces;
	private List<TerrainHex> mTerrainPieces;
	
	//Properties
	public List<HarborPiece> harborPieces() 
	{
		return mHarborPieces;
	}
	public List<SeaFramePiece> seaPieces() 
	{
		return mSeaPieces;
	}
	public List<TerrainHex> terrainPieces() 
	{
		return mTerrainPieces;
	}
	//Functions
	
}
