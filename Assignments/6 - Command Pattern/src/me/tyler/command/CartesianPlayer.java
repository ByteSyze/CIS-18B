package me.tyler.command;

import java.awt.Point;

/**
 * A player that can only move in Cartesian coordinates. Imagine that..
 * */
public class CartesianPlayer 
{
	public enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}
	
	private Point position;
	
	public CartesianPlayer()
	{
		position = new Point();
	}
	
	/**
	 * Moves the player in the given direction by the specified amount of spaces.
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
	
	public String toString()
	{
		return "Player location: ("+position.x+", "+position.y+")";
	}

}
