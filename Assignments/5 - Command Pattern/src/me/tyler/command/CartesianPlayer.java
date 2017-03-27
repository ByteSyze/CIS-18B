package me.tyler.command;

import java.awt.Point;

/**
 * A player that can only move in Cartesian coordinates.
 * */
public class CartesianPlayer 
{
	public enum Direction
	{
		UP, DOWN, LEFT, RIGHT;
		
		/**
		 * Returns the first Direction that starts with {@code c}.
		 * 
		 * @throws IllegalArgumentException if no Direction starts with {@code c}.
		 * */
		public static Direction valueOf(char c) throws IllegalArgumentException
		{
			for(Direction d : Direction.values())
			{
				if(d.toString().charAt(0) == c)
				{
					return d;
				}
			}
			
			throw new IllegalArgumentException();
		}
	}
	
	/**The position of the player.*/
	private Point position;
	
	public CartesianPlayer()
	{
		position = new Point();
	}
	
	/**
	 * Moves the player in the given direction by the specified number of spaces.
	 * */
	public void move(Direction direction, int spaces)
	{
		switch(direction)
		{
			case UP:
				position.y += spaces;
				break;
			case DOWN:
				position.y -= spaces;
				break;
			case LEFT:
				position.x -= spaces;
				break;
			case RIGHT:
				position.x += spaces;
				break;
		}
	}
	
	@Override
	public String toString()
	{
		return "Player location: ("+position.x+", "+position.y+")";
	}

}
