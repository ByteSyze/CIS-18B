package me.tyler.pizza.options.sizes;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class ExtraLarge extends PizzaOption
{
	public ExtraLarge(FoodItem foodItem)
	{
		super(foodItem);
		
		surcharge = 22f;
		description = "Extra Large";
	}
}
