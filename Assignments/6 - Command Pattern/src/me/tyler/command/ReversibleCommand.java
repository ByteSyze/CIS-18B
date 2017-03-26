package me.tyler.command;

public interface ReversibleCommand extends Command
{
	public void undo();
}
