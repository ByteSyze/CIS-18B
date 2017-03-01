package me.tyler.pizza.options.crusts;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class StuffedCrust extends PizzaOption
{
	public StuffedCrust(FoodItem foodItem) 
	{
		super(foodItem);
		
		surcharge = 4f;
		description = "Stuffed Crst";
	}

}
