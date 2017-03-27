package me.tyler.command;

/**
 * A type of Command that can be reversed.
 * */
public interface ReversibleCommand extends Command
{
	/**
	 * Reverses the effects of {@link Command#execute execute()}.
	 * */
	public void undo();
}
