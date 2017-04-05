package tic.tac.toe.command;

/**
 * An interface for treating actions as objects.
 * */
public interface Command
{
	/**
	 * Encapsulates some behavior specified in a subclass of this interface.
	 * */
	public void execute();
}
