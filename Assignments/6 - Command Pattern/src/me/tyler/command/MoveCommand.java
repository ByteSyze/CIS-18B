package me.tyler.command;

public class MoveCommand implements ReversibleCommand
{
	private CartesianPlayer player;
	private CartesianPlayer.Direction direction;
	
	private int spaces;
	
	public MoveCommand(CartesianPlayer player, CartesianPlayer.Direction direction, int spaces)
	{
		this.player = player;
		this.direction = direction;
		
		this.spaces = spaces;
	}
	
	@Override
	public void execute() 
	{
		player.move(direction, spaces);
	}
	
	@Override
	public void undo() 
	{
		player.move(direction, -spaces);
	}
}
