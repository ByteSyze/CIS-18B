package me.tyler.pizza.options.condiments;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class Pepperoni extends PizzaOption
{
	public Pepperoni(FoodItem foodItem) 
	{
		super(foodItem);
		
		surcharge = .2f;
		description = "Pepperoni";
	}
}
