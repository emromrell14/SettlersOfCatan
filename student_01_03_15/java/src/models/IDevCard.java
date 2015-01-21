package models;

import shared.definitions.DevCardType;

public interface IDevCard
{
	
	boolean getmPlayed();
	DevCardType getmType();
	void execute();
}
