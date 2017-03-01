package me.tyler.pizza.options.sizes;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class Medium extends PizzaOption
{
	public Medium(FoodItem foodItem)
	{
		super(foodItem);
		
		surcharge = 14f;
		description = "Medium";
	}
}
