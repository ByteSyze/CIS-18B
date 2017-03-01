package me.tyler.pizza.options.condiments;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class Cheese extends PizzaOption
{
	public Cheese(FoodItem foodItem)
	{
		super(foodItem);
		
		surcharge = 0f;
		description = "Cheese";
	}
}
