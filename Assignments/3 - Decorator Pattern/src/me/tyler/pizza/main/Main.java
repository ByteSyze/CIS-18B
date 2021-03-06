package me.tyler.pizza.main;

import java.util.Scanner;

import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;
import me.tyler.pizza.Pizza;
import me.tyler.pizza.options.*;

public class Main 
{	
	/**Possible states in the command interface.*/
	public enum State
	{
		PRINT_CONDIMENTS,
		PRINT_RECEIPT,
		SELECT_DELIVERY,
		SELECT_CONDIMENTS,
		SELECT_CRUST,
		SELECT_SIZE
	}
	
	public static FoodFactory defaultServiceFactory = new ServiceFactory();
	public static FoodFactory defaultSizeFactory = new SizeFactory();
	public static FoodFactory defaultCrustFactory = new CrustFactory();
	public static FoodFactory defaultCondimentFactory = new CondimentFactory();
	
	private static final Scanner INPUT_SCANNER = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception
	{	
		State state = State.SELECT_DELIVERY;
		
		char userSelection;
		FoodItem pizza = new Pizza();
		
		System.out.println("Welcome to the only Pizzeria in the world where you can order from a command UX. The future is now~\n");
		
		while(true)
		{
			if(state == State.SELECT_DELIVERY)
			{
				state = State.SELECT_SIZE;
				
				System.out.println("Please select a delivery option: ");
				
				FoodFactory.printDynamicFoodItems(ServiceFactory.class);
				
				try
				{
					userSelection = getNextSelection();
					pizza = processUserSelection(userSelection, pizza, defaultServiceFactory);
				}
				catch(Exception e)
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
					pizza = processUserSelection(userSelection, pizza, defaultSizeFactory);
				}
				catch(Exception e)
				{
					System.out.println("Invalid Option.");
					state = State.SELECT_SIZE;
				}
			}
			else if(state == State.SELECT_CRUST)
			{
				state = State.PRINT_CONDIMENTS;
				
				System.out.println("Please select a pizza crust: ");
				
				FoodFactory.printDynamicFoodItems(CrustFactory.class);

				try
				{
					userSelection = getNextSelection();
					pizza = processUserSelection(userSelection, pizza, defaultCrustFactory);
				}
				catch(Exception e)
				{
					System.out.println("Invalid Option.");
					state = State.SELECT_CRUST;
				}
			}
			else if(state == State.PRINT_CONDIMENTS)
			{
				state = State.SELECT_CONDIMENTS;
				System.out.println("Please select your pizza condiments ('X' to finish): ");
				
				FoodFactory.printDynamicFoodItems(CondimentFactory.class);
			}
			else if(state == State.SELECT_CONDIMENTS)
			{	
				userSelection = getNextSelection();
				
				if(userSelection == 'x')
				{
					state = State.PRINT_RECEIPT;
				}
				else
				{
					try
					{
						pizza = processUserSelection(userSelection, pizza, defaultCondimentFactory);
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
				System.out.println(pizza.generateReceipt());
				System.out.println("------------------");
				System.out.println("Order Total: $" + pizza.calculateCost());
				
				break;
			}
		}
		INPUT_SCANNER.close();
	}
	
	/**
	 * Returns the next character from user input in lower case. This method will block until a character is available.
	 * */
	private static char getNextSelection()
	{
		while(!INPUT_SCANNER.hasNext());
		
		return Character.toLowerCase(INPUT_SCANNER.next().charAt(0));
	}
	
	/**
	 * Wraps the specified FoodItem in another FoodItem that is retrieved from the specified factory.
	 * 
	 * @param	userSelection	a single character selection  corresponding to the options printed by {@link FoodFactory#printDynamicFoodItems()}.
	 * @param	currentFood		the current food to wrap
	 * @param	factory			the FoodFactory to retrieve the dynamic FoodItem from
	 * 
	 * @return	the new FoodItem enveloping currentFood
	 * 
	 * @throws	Exception	if the index from userSelection is not in the range [0, factory.getClass().getDeclaredClasses().length)
	 * */
	public static FoodItem processUserSelection(char userSelection, FoodItem currentFood, FoodFactory factory) throws Exception
	{
		char startOption = 'a';
		int classIndex = userSelection - startOption; //The index of the user-chosen option relative to the start option.
		
		if(classIndex >= 0 && classIndex < factory.getClass().getDeclaredClasses().length)
		{
			return factory.createFoodItem(currentFood, FoodFactory.getDynamicFoodClassName(factory.getClass(), classIndex));
		}
		else
		{
			throw new RuntimeException("Invalid Option.");
		}
	}
}
