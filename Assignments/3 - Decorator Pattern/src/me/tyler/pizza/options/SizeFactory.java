package me.tyler.pizza.options;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.FoodFactory;

public class SizeFactory extends FoodFactory
{	
	@NestedType(allowDynamic=true)
	protected class Small extends PizzaOption
	{
		public Small(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 10f;
			description = "Small";
		}
	}

	@NestedType(allowDynamic=true)
	protected class Medium extends PizzaOption
	{
		public Medium(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 14f;
			description = "Medium";
		}
	}

	@NestedType(allowDynamic=true)
	protected class Large extends PizzaOption
	{
		public Large(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 18f;
			description = "Large";
		}
	}

	@NestedType(allowDynamic=true)
	protected class ExtraLarge extends PizzaOption
	{
		public ExtraLarge(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 22f;
			description = "Extra Large";
		}
	}
}
