package me.tyler.pizza.options.condiments;

import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class CondimentFactory extends FoodFactory
{
	@DynamicOption(allowDynamic=true)
	protected class Cheese extends PizzaOption
	{
		public Cheese(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 0f;
			description = "Cheese";
		}
	}
	
	@DynamicOption(allowDynamic=true)
	protected class Chicken extends PizzaOption
	{
		public Chicken(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 1.5f;
			description = "Chicken";
		}
	}
	
	@DynamicOption(allowDynamic=true)
	protected class Olives extends PizzaOption
	{
		public Olives(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = .2f;
			description = "Olives";
		}
	}
	
	@DynamicOption(allowDynamic=true)
	protected class Pepperoni extends PizzaOption
	{
		public Pepperoni(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = .2f;
			description = "Pepperoni";
		}
	}
	
	@DynamicOption(allowDynamic=true)
	protected class Peppers extends PizzaOption
	{
		public Peppers(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = .2f;
			description = "Peppers";
		}
	}
	
	@DynamicOption(allowDynamic=true)
	protected class Sausage extends PizzaOption 
	{
		public Sausage(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = .5f;
			description = "Sausage";
		}
	}
}
