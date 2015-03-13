package commands;

public interface ICommand
{
	/**
	 * @pre executing this command is a valid operation
	 * @post execute the command and update the game accordingly
	 */
	public void execute();
}
