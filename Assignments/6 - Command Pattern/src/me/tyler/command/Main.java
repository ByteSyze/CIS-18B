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
		
		char userInput;
		
		System.out.println(player);
		
		while(gameRunning)
		{
			if(commandQueue.hasUndoableCommands())
			{
				System.out.println("(U)p, (D)own, (L)eft, (R)ight, or Go (B)ack: ");
			}
			else
			{
				System.out.println("(U)p, (D)own, (L)eft, or (R)ight: ");
			}
			
			userInput = scanner.nextLine().toUpperCase().charAt(0);
			
			if(commandQueue.hasUndoableCommands() && userInput == 'B')
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
