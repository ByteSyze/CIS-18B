package me.tyler.pizza.options.sizes;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class Large extends PizzaOption
{
	public Large(FoodItem foodItem)
	{
		super(foodItem);
		
		surcharge = 18f;
		description = "Large";
	}
}
