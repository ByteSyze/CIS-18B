package me.tyler.pizza.options.crusts;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class ThinCrust extends PizzaOption
{
	public ThinCrust(FoodItem foodItem) 
	{
		super(foodItem);
		
		surcharge = 0f;
		description = "Thin Crst";
	}
}
