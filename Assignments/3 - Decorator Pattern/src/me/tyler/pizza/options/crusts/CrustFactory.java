package me.tyler.pizza.options.crusts;

import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class CrustFactory extends FoodFactory
{
	@Override
	public FoodItem wrapFood(FoodItem food, String crust) throws Exception
	{
		switch(crust.toLowerCase())
		{
			case "thin":
				return new ThinCrust(food);
			case "thick":
				return new ThickCrust(food);
			case "stuffed":
				return new StuffedCrust(food);
			default:
				throw new RuntimeException("Unknown Crust: " + crust);
		}
	}
	
	class ThinCrust extends PizzaOption
	{
		public ThinCrust(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = 0f;
			description = "Thin Crst";
		}
	}
	
	class ThickCrust extends PizzaOption
	{
	 	public ThickCrust(FoodItem foodItem) 
	 	{
	 		super(foodItem);
	 		
	 		surcharge = 0f;
	 		description = "Thick Crst";
	 	}
	}
	
	class StuffedCrust extends PizzaOption
	{
		public StuffedCrust(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = 4f;
			description = "Stuffed Crst";
		}
	}
}
