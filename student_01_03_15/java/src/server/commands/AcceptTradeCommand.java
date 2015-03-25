package server.commands;

import models.IGame;
import models.Index;

public class AcceptTradeCommand implements ICommand
{
	private IGame game;
	private Index playerIndex;
	private boolean willAccept;
	
	public AcceptTradeCommand(IGame game, Index playerIndex, boolean willAccept)
	{
		this.game = game;
		this.playerIndex = playerIndex;
		this.willAccept = willAccept;
	}

	@Override
	public void execute()
	{
		String accepted = (willAccept)? "accepted the trade":"refused the trade";
		String playerName = game.getPlayer(playerIndex).name();
		String message = playerName + " " + accepted;
		this.game.addActionToLog(playerIndex, message);
		game.acceptTrade(playerIndex, willAccept);
	}
}
