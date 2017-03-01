package me.tyler.pizza.main;

import me.tyler.pizza.DeliveryPizza;
import me.tyler.pizza.FoodItem;
import me.tyler.pizza.options.condiments.*;
import me.tyler.pizza.options.sizes.*;
import me.tyler.pizza.options.crusts.*;

public class Main 
{
	
	public static void main(String[] args)
	{
		FoodItem d = new DeliveryPizza();
		d = new Large(d);
		d = new StuffedCrust(d);
		d = new Pepperoni(d);
		
		System.out.println(d.generateReceipt());
		System.out.println("------------------");
		System.out.println("Order Total: $" + d.calculateCost());
	}

}
