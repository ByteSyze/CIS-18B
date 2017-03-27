package me.tyler.command;

import java.util.ArrayDeque;

/**
 * A Simple command FIFO that supports reversing of all executed commands.
 * */
public class ReversibleCommandQueue 
{
	/** Commands waiting to be executed. This deque is treated as a FIFO.*/
	private ArrayDeque<ReversibleCommand> unexecutedCommands;
	
	/** Commands that have been executed. This deque is treated as a LIFO.*/
	private ArrayDeque<ReversibleCommand> executedCommands;
	
	public ReversibleCommandQueue()
	{
		unexecutedCommands = new ArrayDeque<ReversibleCommand>();
		executedCommands = new ArrayDeque<ReversibleCommand>();
	}
	
	/**
	 * Adds {@code command} to the end of the command queue.
	 * */
	public void addCommand(ReversibleCommand command)
	{
		unexecutedCommands.addLast(command);
	}
	
	/**
	 * Executes the command at the head of the command queue.
	 * */
	public void executeNextCommand()
	{
		if(!unexecutedCommands.isEmpty())
		{
			ReversibleCommand nextCommand = unexecutedCommands.removeFirst();
			
			nextCommand.execute();
			
			executedCommands.addLast(nextCommand);
		}
	}
	
	/**
	 * Reverses the command at the tail of the executed command deque.
	 * */
	public void undoLastCommand()
	{
		if(!executedCommands.isEmpty())
			executedCommands.removeLast().undo();
	}
	
	/**
	 * @return true if the executedCommands deque is not empty.
	 * */
	public boolean hasUndoableCommands()
	{
		return !executedCommands.isEmpty();
	}
}
