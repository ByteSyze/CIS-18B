package me.tyler.pizza.options.sizes;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class Small extends PizzaOption
{
	public Small(FoodItem foodItem)
	{
		super(foodItem);
		
		surcharge = 10f;
		description = "Small";
	}
}
