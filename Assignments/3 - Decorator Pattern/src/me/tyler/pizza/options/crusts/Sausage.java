package me.tyler.pizza.options.crusts;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class Sausage extends PizzaOption 
{

	public Sausage(FoodItem foodItem)
	{
		super(foodItem);
		
		surcharge = .5f;
		description = "Sausage";
	}

}
