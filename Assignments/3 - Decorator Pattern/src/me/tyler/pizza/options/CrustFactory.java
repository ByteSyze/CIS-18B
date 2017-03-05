package me.tyler.pizza.options;

import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class CrustFactory extends FoodFactory
{
	@NestedType(allowDynamic=true)
	protected class ThinCrust extends PizzaOption
	{
		public ThinCrust(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = 0f;
			description = "Thin Crst";
		}
	}

	@NestedType(allowDynamic=true)
	protected class ThickCrust extends PizzaOption
	{
	 	public ThickCrust(FoodItem foodItem) 
	 	{
	 		super(foodItem);
	 		
	 		surcharge = 0f;
	 		description = "Thick Crst";
	 	}
	}

	@NestedType(allowDynamic=true)
	protected class StuffedCrust extends PizzaOption
	{
		public StuffedCrust(FoodItem foodItem) throws Exception 
		{
			super(foodItem);
			
			//Kludgey workaround for restricting stuffed crust on small pizzas.
			//Only works because our interface asks for size immediately before asking for crust options.
			if(foodItem.getDescription() == "Small")
				throw new Exception("Stuffed crust is not allowed on small pizzas.");
			
			surcharge = 4f;
			description = "Stuffed Crst";
		}
	}
}
