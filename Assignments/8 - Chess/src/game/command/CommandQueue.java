package game.command;

import java.util.ArrayList;
import java.util.List;

public class CommandQueue<C extends Command>
{
	private List<C> commands;
	private List<C> commandHistory;
	
	public CommandQueue()
	{
		commands = new ArrayList<C>();
		commandHistory = new ArrayList<C>();
	}
	
	public void addCommand(C command)
	{
		commands.add(command);
	}
	
	public void executeCommands()
	{
		for(C command : commands)
		{
			command.execute();
			
			commandHistory.add(command);
			commands.remove(command);
		}
	}
}
