package me.tyler.pizza.options.crusts;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class Alfredo extends PizzaOption 
{

	public Alfredo(FoodItem foodItem)
	{
		super(foodItem);
		
		surcharge = 1f;
		description = "Alfredo";
	}

}
