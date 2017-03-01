package me.tyler.pizza.options.crusts;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class ThickCrust extends PizzaOption
{
	public ThickCrust(FoodItem foodItem) 
	{
		super(foodItem);
		
		surcharge = 0f;
		description = "Thick Crst";
	}

}
