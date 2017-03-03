package me.tyler.pizza.options.sizes;

import me.tyler.pizza.FoodItem;
import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.PizzaOption;

public class SizeFactory extends FoodFactory
{	
	@Override
	public FoodItem wrapFood(FoodItem food, String size) throws Exception
	{
		switch(size.toLowerCase())
		{
			case "sm":
				return new Small(food);
			case "md":
				return new Medium(food);
			case "lg":
				return new Large(food);
			case "xl":
				return new ExtraLarge(food);
			default:
				throw new RuntimeException("Unknown Size: " + size);
		}
	} 
	
	class Small extends PizzaOption
	{
		public Small(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 10f;
			description = "Small";
		}
	}
	
	class Medium extends PizzaOption
	{
		public Medium(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 14f;
			description = "Medium";
		}
	}
	
	class Large extends PizzaOption
	{
		public Large(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 18f;
			description = "Large";
		}
	}
	
	class ExtraLarge extends PizzaOption
	{
		public ExtraLarge(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 22f;
			description = "Extra Large";
		}
	}

}
