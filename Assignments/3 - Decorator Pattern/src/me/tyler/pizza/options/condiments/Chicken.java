package me.tyler.pizza.options.condiments;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class Chicken extends PizzaOption
{

	public Chicken(FoodItem foodItem)
	{
		super(foodItem);
		
		surcharge = 1.5f;
		description = "Chicken";
	}

}
