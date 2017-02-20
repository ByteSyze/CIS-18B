package me.tyler.ng;

import java.util.Scanner;

import me.tyler.ng.observer.SimpleObservable;

/**
 * 	Sends out new editions of the ever-so-popular 
 *  "National Geomorphic" Magazine to all subscribers!
 * */
public class NGMailingList extends SimpleObservable<String>
{
	public enum State
	{
		SELECTING, ADDING, REMOVING, SENDING, HELP;
	}
	
	public static void main(String[] args)
	{
		NGMailingList ng = new NGMailingList();
		
		Scanner scanner = new Scanner(System.in); //Scan the system input stream.
		State state = State.HELP; //Current state in the command UI. Start by listing commands.
		String input; //User input.
		
		System.out.println("Welcome to the National Geomorphic Magazine mailing list!");
		
		while(true)
		{
			if(state == State.HELP)
			{
				System.out.println("A) ADD NEW SUBSCRIBER");
				System.out.println("B) REMOVE EXISTING SUBSCRIBER");
				System.out.println("C) SEND MESSAGE TO ALL SUBSCRIBERS");
				System.out.println("X) EXIT THE NATGEOM MAILING LIST");
				
				state = State.SELECTING;
			}
			if(state == State.SELECTING)
			{
				input = scanner.nextLine();
				
				switch(input)
				{
					case "A" :
					case "a" :
						state = State.ADDING;
						break;
					case "B" :
					case "b" :
						state = State.REMOVING;
						break;
					case "C" :
					case "c" :
						state = State.SENDING;
						break;
					case "X" :
					case "x" :
						//Let the program die naturally.
						scanner.close();
						return;
					default:
						System.err.println("Invalid command.");
				}
			}
			else if(state == State.ADDING)
			{
				System.out.println("Please enter a unique street address: ");
				input = scanner.nextLine();
				
				NGSubscriber newSubscriber = new NGSubscriber(input);
				
				try 
				{
					ng.addObserver(newSubscriber);
					System.out.println(input +" has been successfully added to the mailing list.");
					
					state = State.SELECTING;
				} 
				catch (Exception e) 
				{
					System.err.println("That address is already subscribed to National Geomorphic.");
					
					state = State.SELECTING;
				}
			}
			else if(state == State.REMOVING)
			{
				System.out.println("Please enter the address to remove: ");
				input = scanner.nextLine();
				
				NGSubscriber newSubscriber = new NGSubscriber(input);
				
				try 
				{
					ng.removeObserver(newSubscriber);
					System.out.println(input +" has been successfully removed from the mailing list.");
					
					state = State.SELECTING;
				} 
				catch (Exception e) 
				{
					System.err.println("That address is not in our mailing list.");
					
					state = State.SELECTING;
				}
			}
			else if(state == State.SENDING)
			{
				System.out.println("Please enter a message to send to subscribers: ");
				ng.notifyObservers(scanner.nextLine());
				
				state = State.SELECTING;
			}
		}
	}
	
	public NGMailingList(){}

}
