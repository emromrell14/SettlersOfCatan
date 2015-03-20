package commands;

import models.IGame;
import models.Index;

public class SendChatCommand implements ICommand
{
	private IGame game;
	private Index playerIndex;
	private String message;
	
	public SendChatCommand(IGame game, Index playerIndex, String message)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.message = message;
	}

	@Override
	public void execute()
	{
		game.sendChat(playerIndex, message);
	}
}
