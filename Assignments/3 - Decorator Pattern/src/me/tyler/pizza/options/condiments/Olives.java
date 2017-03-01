package me.tyler.pizza.options.condiments;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class Olives extends PizzaOption
{
	public Olives(FoodItem foodItem) 
	{
		super(foodItem);
		
		surcharge = .2f;
		description = "Olives";
	}
}
