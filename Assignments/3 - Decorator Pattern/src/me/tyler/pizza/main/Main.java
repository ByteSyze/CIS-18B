package me.tyler.pizza.main;

import java.util.Scanner;

import me.tyler.pizza.CarryOutPizza;
import me.tyler.pizza.DeliveryPizza;
import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;
import me.tyler.pizza.options.condiments.*;
import me.tyler.pizza.options.sizes.*;
import me.tyler.pizza.options.crusts.*;

public class Main 
{	
	/**Possible states in the command interface.*/
	public enum State
	{
		SELECT_DELIVERY,
		SELECT_SIZE,
		SELECT_CRUST,
		SELECT_CONDIMENTS,
		PRINT_RECEIPT
	}
	
	public static FoodFactory defaultSizeFactory = new SizeFactory();
	public static FoodFactory defaultCrustFactory = new CrustFactory();
	public static FoodFactory defaultCondimentFactory = new CondimentFactory();
	
	private static final Scanner INPUT_SCANNER = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception
	{	
		State state = State.SELECT_DELIVERY;
		
		char userSelection;
		FoodItem d = null;
		
		System.out.println("Welcome to the only Pizzeria in the world where you can order from a command UX. The future is now~\n");
		
		while(true)
		{
			if(state == State.SELECT_DELIVERY)
			{
				state = State.SELECT_SIZE;
				
				System.out.println("Please select a delivery option: ");
				System.out.println("a. Delivery");
				System.out.println("b. Carry-out");
				
				userSelection = getNextSelection();
				
				if(userSelection == 'a')
					d = new DeliveryPizza();
				else if(userSelection == 'b')
					d = new CarryOutPizza();
				else
				{
					System.out.println("Invalid option.");
					state = State.SELECT_DELIVERY;
				}
			}
			else if(state == State.SELECT_SIZE)
			{
				state = State.SELECT_CRUST;
				
				System.out.println("Please select a pizza size: ");
				
				FoodFactory.printDynamicFoodItems(SizeFactory.class);
				
				try
				{
					userSelection = getNextSelection();
					d = processUserSelection(userSelection, defaultSizeFactory, d);
				}
				catch(Exception e)
				{
					System.out.println("Invalid Option.");
					state = State.SELECT_SIZE;
				}
			}
			else if(state == State.SELECT_CRUST)
			{
				state = State.SELECT_CONDIMENTS;
				
				System.out.println("Please select a pizza crust: ");
				
				FoodFactory.printDynamicFoodItems(CrustFactory.class);

				try
				{
					userSelection = getNextSelection();
					d = processUserSelection(userSelection, defaultCrustFactory, d);
				}
				catch(Exception e)
				{
					System.out.println("Invalid Option.");
					state = State.SELECT_CRUST;
				}
			}
			else if(state == State.SELECT_CONDIMENTS)
			{	
				System.out.println("Please select your pizza condiments ('X' to finish): ");
				
				FoodFactory.printDynamicFoodItems(CondimentFactory.class);
				
				userSelection = getNextSelection();
				
				if(userSelection == 'x')
				{
					state = State.PRINT_RECEIPT;
				}
				else
				{
					try
					{
						d = processUserSelection(userSelection, defaultCondimentFactory, d);
					}
					catch(Exception e)
					{
						System.out.println("Invalid Option.");
						state = State.SELECT_CONDIMENTS;
					}
				}
			}
			else if(state == State.PRINT_RECEIPT)
			{
				System.out.println(d.generateReceipt());
				System.out.println("------------------");
				System.out.println("Order Total: $" + d.calculateCost());
				
				break;
			}
		}
		
		INPUT_SCANNER.close();
	}
	
	/** Returns the next character from user input in lower case. This method will block until a character is available.*/
	private static char getNextSelection()
	{
		while(!INPUT_SCANNER.hasNext());
		
		return Character.toLowerCase(INPUT_SCANNER.next().charAt(0));
	}
	
	public static FoodItem processUserSelection(char userSelection, FoodFactory factory, FoodItem currentFood) throws Exception
	{
		char startOption = 'a';
		int classIndex = userSelection - startOption; //The index of the chosen option class inside a specified factory.
		
		if(classIndex >= 0 && classIndex < factory.getClass().getDeclaredClasses().length)
		{
			return factory.wrapFood(currentFood, FoodFactory.getDynamicFoodClassName(factory.getClass(), classIndex));
		}
		else
		{
			throw new RuntimeException("Invalid Option.");
		}
	}
}
