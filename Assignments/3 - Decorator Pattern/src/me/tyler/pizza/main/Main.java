package me.tyler.pizza.main;

import java.util.Scanner;

import me.tyler.pizza.CarryOutPizza;
import me.tyler.pizza.DeliveryPizza;
import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;
import me.tyler.pizza.FoodFactory.DynamicOption;
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
					System.out.println("Invalid option. Please select 'A' or 'B'");
					state = State.SELECT_DELIVERY;
				}
			}
			else if(state == State.SELECT_SIZE)
			{
				state = State.SELECT_CRUST;
				
				System.out.println("Please select a pizza size: ");
				System.out.println("a. Small");
				System.out.println("b. Medium");
				System.out.println("c. Large");
				System.out.println("d. Extra Large");
				
				userSelection = getNextSelection();
				
				if(userSelection == 'a')
					d = defaultSizeFactory.wrapFood(d, "sm");
				else if(userSelection == 'b')
					d = defaultSizeFactory.wrapFood(d, "md");
				else if(userSelection == 'c')
					d = defaultSizeFactory.wrapFood(d, "lg");
				else if(userSelection == 'd')
					d = defaultSizeFactory.wrapFood(d, "xl");
				else
				{
					System.out.println("Invalid option. Please select 'A', 'B', 'C' or 'D'");
					state = State.SELECT_SIZE;
				}
			}
			else if(state == State.SELECT_CRUST)
			{
				state = State.SELECT_CONDIMENTS;
				
				System.out.println("Please select a pizza crust: ");
				System.out.println("a. Thin Crust");
				System.out.println("b. Thick Crust");
				System.out.println("c. Stuffed Crust");
				
				userSelection = getNextSelection();
				
				if(userSelection == 'a')
					defaultCrustFactory.wrapFood(d, "thin");
				else if(userSelection == 'b')
					defaultCrustFactory.wrapFood(d, "thick");
				else if(userSelection == 'c')
					defaultCrustFactory.wrapFood(d, "stuffed");
				else
				{
					System.out.println("Invalid option. Please select 'A', 'B' or 'C'");
					state = State.SELECT_CRUST;
				}
			}
			else if(state == State.SELECT_CONDIMENTS)
			{	
				System.out.println("Please select your pizza condiments ('X' to finish): ");
				char startOption = 'a';
				int optionIndex = 0;
				int classIndex = 0;
				
				for(Class<?> option : CondimentFactory.class.getDeclaredClasses())
				{
					if(option.isAnnotationPresent(DynamicOption.class))
					{
						if(option.getAnnotation(DynamicOption.class).allowDynamic())
						{
							System.out.println((char)(startOption + optionIndex) + ". " + option.getSimpleName());
							optionIndex++;
						}
					}
				}
				
				userSelection = getNextSelection();
				
				classIndex = userSelection - startOption;
				
				if(userSelection == 'x')
				{
					state = State.PRINT_RECEIPT;
				}
				else if(classIndex < CondimentFactory.class.getDeclaredClasses().length)
				{
					String option = defaultCondimentFactory.getClass().getDeclaredClasses()[classIndex].getSimpleName();
					d = defaultCondimentFactory.wrapFood(d, option);
				}
				else
				{
					System.out.println("Invalid option.");
				}
			}
			else if(state == State.PRINT_RECEIPT)
			{
				System.out.println(d.generateReceipt());
				System.out.println("------------------");
				System.out.println("Order Total: $" + d.calculateCost());
				
				break;
			}
			//d = defaultSizeFactory.wrapFood(d, "lg");
			//d = defaultCrustFactory.wrapFood(d, "stuffed");
			//d = defaultCondimentFactory.wrapFood(d, "pepperoni");
		}
		
		INPUT_SCANNER.close();
	}
	
	/**Loosely interprets and returns the next user input.*/
	private static char getNextSelection()
	{
		return Character.toLowerCase(INPUT_SCANNER.nextLine().charAt(0));
	}

}
