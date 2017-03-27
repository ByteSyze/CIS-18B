package me.tyler.command;

/**
 * A command for moving a CartesianPlayer.
 * */
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

	/**
	 * When invoked, calls {@link CartesianPlayer#move move()} on the {@link CartesianPlayer}
	 * passed through the constructor, using the given direction and spaces as well.
	 * */
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
