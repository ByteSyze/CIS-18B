package me.tyler.command;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		ReversibleCommandQueue commandQueue = new ReversibleCommandQueue();
		CartesianPlayer player = new CartesianPlayer();
		
		boolean gameRunning = true;
		String userInput;
		
		System.out.println(player);
		
		while(gameRunning)
		{
			if(commandQueue.hasUndoableCommands())
			{
				System.out.println("Move up, down, left, right, OR undo: ");
			}
			else
			{
				System.out.println("Move up, down, left, or right: ");
			}
			
			userInput = scanner.nextLine().toUpperCase();
			
			if(commandQueue.hasUndoableCommands() && userInput.equals("UNDO"))
			{
				commandQueue.undoLastCommand();
			}
			else
			{
				try
				{
					commandQueue.addCommand(new MoveCommand(player, CartesianPlayer.Direction.valueOf(userInput), 1));
					
					commandQueue.executeNextCommand();
				} 
				catch(IllegalArgumentException e)
				{
					System.out.println("Invalid option. Please choose again.");
				}
			}
			
			System.out.println(player);
		}
	}
}
